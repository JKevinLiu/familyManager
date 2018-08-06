package com.snill.fm.service;

import com.snill.fm.bean.PayMonth;

import java.util.List;

public interface PayMonthService {
    public PayMonth getPayMonthById(int id);
    public List<PayMonth> getPayMonthByUserId(int userId);
    public PayMonth getPayMonthByUserIdAndYearMonth(int userId, int yearMonth);
    public List<PayMonth> getUserLastHalfYearPayMonth(int userId);
    public void save(PayMonth payMonth);
    public void delete(int id);
}