package com.snill.fm.bean.page;


import java.io.Serializable;


public class Page implements Serializable{

    private int pageSize;
    private int curCount;//当前页记录数
    private int curNo;//当前页码
    private int totalCount;//记录总数
    private int pageCount;//总页数

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getCurCount() {
        return curCount;
    }
    public void setCurCount(int curCount) {
        this.curCount = curCount;
    }
    public int getCurNo() {
        return curNo;
    }
    public void setCurNo(int curNo) {
        this.curNo = curNo;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }



}