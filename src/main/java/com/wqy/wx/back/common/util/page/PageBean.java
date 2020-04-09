package com.wqy.wx.back.common.util.page;

import lombok.Data;

import java.util.HashMap;

@Data
public class PageBean {
    /**
     * 总页量
     */
    public Integer totalPage;
    private int totalRow;//总的条数

    private int pageIndex;//当前的页数

    private int pageSize;//每页的条数

    private int pageLimit;//下一页的条件 等于pageNo*pageSize,开始条数

    private String storename;
    private String starttime;
    private String closetime;

    private String selectInfo;

    private int state;

    private static HashMap<String, String> infoHashMap;//模糊查询配置参数

    static {
        infoHashMap = new HashMap<String, String>();
    }

    //无参构造方法
    public PageBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    //全参构造方法
    public PageBean(int totalRow, int pageIndex, int pageSize) {
        this.totalRow = totalRow;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    //属性的set，get 方法
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    //获取总页数
    public int getTotalPage() {
        if (totalRow % pageSize == 0) {
            return totalRow / pageSize;
        } else {
            return totalRow / pageSize + 1;
        }
    }

    //多写一个判断下一页的方法
    public boolean isNext() {
        return pageIndex + 1 < getTotalPage();
    }

    //上一页的方法
    public boolean isPrevious() {
        return pageIndex > 0;
    }

    public String getInfoHashMap(String key) {
        return infoHashMap.get(key);
    }

    public void setInfoHashMap(String key, String value) {
        PageBean.infoHashMap.put(key, value);
    }

    public String getSelectInfo() {
        return selectInfo;
    }

    public void setSelectInfo(String selectInfo) {
        this.selectInfo = selectInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }
}

