package com.snill.fm.page;


import java.io.Serializable;


public class ReqPage implements Serializable{

    private int pageSize;
    private int curPage;

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }




}