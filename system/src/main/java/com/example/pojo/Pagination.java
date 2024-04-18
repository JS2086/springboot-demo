package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    /**
     * 当前页码
     */
    private Long pageNo;

    /**
     * 每页记录数
     */
    private Long pageSize;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据
     */
    private List<?> list;
}
