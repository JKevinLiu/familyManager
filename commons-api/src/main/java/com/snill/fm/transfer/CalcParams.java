package com.snill.fm.transfer;

import java.io.Serializable;
import java.util.List;

public class CalcParams implements Serializable{
    private int userId;
    private List<Integer> yearMonthList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getYearMonthList() {
        return yearMonthList;
    }

    public void setYearMonthList(List<Integer> yearMonthList) {
        this.yearMonthList = yearMonthList;
    }
}