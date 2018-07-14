package com.snill.fm.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.snill.fm.bean.PayMonth;
import com.snill.fm.mapper.PayMonthMapper;
import com.snill.fm.service.PayMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.List;

@Service(interfaceClass = PayMonthService.class)
public class PayMonthServiceImpl implements PayMonthService {

    @Autowired
    private PayMonthMapper payMonthMapper;

    @Override
    public PayMonth getPayMonthById(int id) {
        return payMonthMapper.getPayMonthById(id);
    }

    @Override
    public List<PayMonth> getPayMonthByUserId(int userId) {
        return payMonthMapper.getPayMonthByUserId(userId);
    }

    @Override
    public PayMonth getPayMonthByUserIdAndYearMonth(int userId, int yearMonth) {
        List<PayMonth> payMonthList = payMonthMapper.getPayMonthByUserIdAndYearMonth(userId, yearMonth, yearMonth);
        if(payMonthList.size() > 1){
            //数据异常
        }
        return payMonthList.get(0);
    }

    @Override
    public List<PayMonth> getUserLastHalfYearPayMonth(int userId) {
        Calendar calendar = Calendar.getInstance();
        String endYearMonth = calendar.get(Calendar.YEAR) + "" + ((calendar.get(Calendar.MONTH)+1) < 10? "0"+(calendar.get(Calendar.MONTH)+1):(calendar.get(Calendar.MONTH)+1));

        calendar.set(Calendar.MONTH, -6);
        String startYearMonth = calendar.get(Calendar.YEAR) + "" + ((calendar.get(Calendar.MONTH)+1) < 10? "0"+(calendar.get(Calendar.MONTH)+1):(calendar.get(Calendar.MONTH)+1));

        List<PayMonth> payMonthList = payMonthMapper.getPayMonthByUserIdAndYearMonth(userId, Integer.parseInt(startYearMonth), Integer.parseInt(endYearMonth));
        return payMonthList;
    }

    @Override
    public void save(PayMonth payMonth) {
        Assert.isNull(payMonth, "数据为空！");
        if(payMonth.getId() == 0){
            payMonthMapper.add(payMonth);
        }else{
            payMonthMapper.update(payMonth);
        }
    }

    @Override
    public void delete(int id) {
        payMonthMapper.delete(id);
    }
}