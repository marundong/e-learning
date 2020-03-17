package com.el.framework.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分页结果数据
 * @param <T>
 */
@Data
@ToString
public class PageResult<T>{

    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 数据总数
     */
    private long total;

    /**
     * 当前页数量
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int pageNum;

}
