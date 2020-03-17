package com.el.framework.request;

import lombok.Data;
import lombok.ToString;


/**
 * 分页请求参数封装
 */
@Data
@ToString
public class PageRequest implements BaseRequest {

    private static final long serialVersionUID = -1L;
    /**
     * 请求页数据量
     */
    int pageSize = 0;

    /**
     * 请求页码
     */
    int pageNum = 10;

}
