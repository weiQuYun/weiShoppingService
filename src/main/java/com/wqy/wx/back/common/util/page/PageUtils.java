package com.wqy.wx.back.common.util.page;

import com.github.pagehelper.PageInfo;

/**
 * package : com.demo.page
 *
 * @author licm
 * date : 2019/10/4
 * created : 9:54
 */
public class PageUtils {
    public <T> Page<T> getPage(Class<T> pojo, int pageIndex, int pageSize) {
        String tableName = pojo.getName();
        StringBuffer tatolSql = new StringBuffer();
        tatolSql.append("from ");
        return null;
    }

    public static <T> Page<T> toPageInfo(PageInfo<T> pageInfo) {
        Page<T> page = new Page<T>();
        page.setTotalRow(pageInfo.getTotal());
        page.setData(pageInfo.getList());
        page.setPageIndex(Long.valueOf(pageInfo.getPageNum()));
        page.setPageSize(Long.valueOf(pageInfo.getPageSize()));
        page.setTotalPage(Long.valueOf(pageInfo.getPages()));
        return page;
    }

    public static <T> Page<T> toPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page1) {
        Page<T> page = new Page<T>();
        page.setTotalRow(page1.getTotal());
        page.setData(page1.getRecords());
        page.setPageIndex(page1.getCurrent());
        page.setPageSize(page1.getSize());
        page.setTotalPage(page1.getPages());
        return page;
    }
}
