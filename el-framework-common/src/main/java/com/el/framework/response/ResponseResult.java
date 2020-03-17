package com.el.framework.response;

import com.el.framework.code.CommonCode;
import com.el.framework.code.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 统一返回数据格式
 * @param <T>
 */

@Data
@ToString
@NoArgsConstructor
public class ResponseResult<T> implements BaseResponse{

    private static final long serialVersionUID = 8078068572402622337L;
    //操作是否成功
    boolean success = CommonCode.SUCCESS.success();

    //操作代码
    int code = CommonCode.SUCCESS.code();

    //提示信息
    String message =CommonCode.SUCCESS.message();

    // 返回数据
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    public ResponseResult(ResultCode resultCode) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }
    public ResponseResult(ResultCode resultCode,T data) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult<>(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL() {
        return new ResponseResult<>(CommonCode.FAIL);
    }

}
