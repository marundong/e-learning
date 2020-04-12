//package com.el.framework.exception;
//
//import com.el.framework.code.CommonCode;
//import com.el.framework.code.ResultCode;
//import com.el.framework.response.ResponseResult;
//import com.google.common.collect.ImmutableMap;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.validation.ConstraintViolationException;
//import java.util.List;
//
///**
// * 统一异常捕获类
// *
// **/
//@RestControllerAdvice//控制器增强
//public class ExceptionCatch {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
//
//    //定义map，配置异常类型所对应的错误代码
//    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
//    //定义map的builder对象，去构建ImmutableMap
//    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();
//
//    //捕获CustomException此类异常
//    @ExceptionHandler(CustomException.class)
//    public ResponseResult customException(CustomException customException) {
//        //记录日志
//        LOGGER.error("catch exception:{}", customException.getMessage());
//        ResultCode resultCode = customException.getResultCode();
//        return new ResponseResult(resultCode);
//    }
//
//
//
//    //捕获ConstraintViolationException异常，定义的参数校验不通过
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseResult exception(MethodArgumentNotValidException exception) {
//        //记录日志
//        LOGGER.error("catch validate exception:", exception);
//        ResponseResult result = new ResponseResult(CommonCode.EL_INVALID_PARAM);
//
//        // 针对验证错误的参数进行处理，返回对应信息
//        if(!StringUtils.isEmpty(exception.getMessage())){
//            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
//            StringBuilder sb = new StringBuilder();
//            for (ObjectError objectError : allErrors) {
//                String message = objectError.getDefaultMessage();
//                sb.append("[").append(message).append("]");
//                if(objectError instanceof FieldError){
//                    FieldError fieldError = (FieldError) objectError;
//                    sb.append("(error value: '")
//                            .append(fieldError.getRejectedValue())
//                            .append("') ");
//                }
//            }
//            result.setMessage(sb.toString());
//        }
//
//
//        return result;
//    }
//
//    //捕获Exception此类异常
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseResult exception(Exception exception) {
//        //记录日志
//        LOGGER.error("catch exception:{}", exception);
//        /*StackTraceElement[] stackTrace = exception.getStackTrace();
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            LOGGER.error(stackTraceElement.toString());
//        }*/
//        if (EXCEPTIONS == null) {
//            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
//        }
//        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
//        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
//        if (resultCode != null) {
//            return new ResponseResult(resultCode);
//        } else {
//            //返回99999异常
//            return new ResponseResult(CommonCode.SERVER_ERROR);
//        }
//    }
//
//    static {
//        //定义异常类型所对应的错误代码
//        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
//    }
//}
