package com.el.framework.response;

import com.el.framework.code.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class PageResponseResult<T> extends ResponseResult<PageResult<T>> {

    private static final long serialVersionUID = -4756508050059588019L;
    PageResult<T> pageResult;

    public PageResponseResult(ResultCode resultCode, PageResult<T> pageResult){
        super(resultCode);
       this.pageResult = pageResult;
    }

}
