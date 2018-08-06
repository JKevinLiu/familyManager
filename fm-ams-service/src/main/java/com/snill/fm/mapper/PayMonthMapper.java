package com.snill.fm.mapper;

import com.snill.fm.bean.PayMonth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayMonthMapper {
    public PayMonth getPayMonthById(int id);
    public List<PayMonth> getPayMonthByUserId(int userId);
    public List<PayMonth> getPayMonthByUserIdAndYearMonth(@Param("userId") int userId, @Param("startYearMonth")int startYearMonth, @Param("endYearMonth")int endYearMonth);
    public void add(PayMonth payMonth);
    public void update(PayMonth payMonth);
    public void delete(int id);
}
