package com.el.framework.interceptor;

import com.el.framework.code.CommonCode;
import com.el.framework.code.ResultCode;
import com.el.framework.exception.CustomException;
import com.el.framework.response.BaseResult;
import com.el.framework.response.ResponseResult;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ElResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> convertClass) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(convertClass);
    }

    @Override
    public Object beforeBodyWrite(Object value, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(value instanceof BaseResult){
            return new ResponseResult<>(value);
        }
        return value;
    }

    //捕获CustomException此类异常
    @ExceptionHandler(CustomException.class)
    public ResponseResult<ResultCode> customException(CustomException customException) {
        //记录日志
        log.error("catch exception:{}", customException.getResultCode(), customException);
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult<>(resultCode);
    }

    //捕获ConstraintViolationException异常，定义的参数校验不通过
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<ResultCode> exception(MethodArgumentNotValidException exception) {
        //记录日志
        log.error("catch exception: {}", exception.getLocalizedMessage(), exception.getCause());
        ResponseResult<ResultCode> result = new ResponseResult<>(CommonCode.EL_INVALID_PARAM);

        // 针对验证错误的参数进行处理，返回对应信息
        if (!StringUtils.isEmpty(exception.getMessage())) {
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
            StringBuilder sb = new StringBuilder();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                sb.append("[").append(message).append("]");
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    sb.append("(error value: '")
                            .append(fieldError.getRejectedValue())
                            .append("') ");
                }
            }
            result.setMessage(sb.toString());
        }


        return result;
    }

    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<ResultCode> exception(Exception exception) {
        //记录日志
        log.error("catch exception: {} ", exception.getLocalizedMessage(), exception);
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult<>(resultCode);
        } else {
            //返回99999异常
            return new ResponseResult<>(CommonCode.SERVER_ERROR);
        }
    }

    static {
        //定义异常类型所对应的错误代码
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
//        builder.put(AccessDeniedException.class, CommonCode.UNAUTHORISE);
    }
}
