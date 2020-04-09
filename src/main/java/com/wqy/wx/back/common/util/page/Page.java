package com.wqy.wx.back.common.util.page;

import lombok.Data;

import java.util.List;

/**
 * package : com.demo.page
 *
 * @author licm
 * date : 2019/10/4
 * created : 9:49
 */
@Data
public class Page<T> {
    /**
     * 总页量
     */
    public Long totalPage;
    /**
     * 总条数
     */
    public Long totalRow;
    /**
     * 页码
     */
    public Long pageIndex = 1L;
    /**
     * 页面展示条数
     */
    public Long pageSize = 10L;
    /**
     * 数据
     */
    public List<T> data;

    public void setTotalRow(Long totalRow) {
        this.totalRow = totalRow;
        this.calPageCount();
    }

    public void calPageCount() {
        this.totalPage = Long.valueOf((int) Math.ceil((this.totalRow * 1.0) / this.pageSize));
    }
}
