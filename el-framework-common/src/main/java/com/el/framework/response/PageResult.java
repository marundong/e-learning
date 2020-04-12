package com.el.framework.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 分页结果数据
 * @param <T>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements BaseResult{

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
