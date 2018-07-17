package com.snill.fm.utils;

import com.snill.fm.bean.PayMonth;
import com.snill.fm.bean.dto.PayMonthInfo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class BeanTransforUtil {
    public static PayMonthInfo transferPayMonth(List<PayMonth> payMonthList) {
        PayMonthInfo payMonthInfo = new PayMonthInfo();

        Collections.sort(payMonthList,(a, b) ->{
            return a.getYearMonth() > b.getYearMonth() ? 1 : -1;
        });

        PayMonth max = payMonthList.stream().max((a, b) ->{
            return  a.getTotalPrice() > b.getTotalPrice() ? 1 : -1;
        }).get();

        PayMonth min = payMonthList.stream().min((a, b) ->{
            return  a.getTotalPrice() > b.getTotalPrice() ? 1 : -1;
        }).get();

        payMonthList.forEach(payMonth -> {
            payMonthInfo.getxAxis().add(payMonth.getYearMonth()+"");
            long totalPrice = payMonth.getTotalPrice();
            double price = new BigDecimal(totalPrice).divide(new BigDecimal(100)).doubleValue();
            payMonthInfo.getStarRate().add(price);

            if(payMonth == max){
                payMonthInfo.getRectColor().add("RED");
            }else if(payMonth == min){
                payMonthInfo.getRectColor().add("GREEN");
            }else{
                payMonthInfo.getRectColor().add("#b5cb85");
            }
        });

        payMonthInfo.setMaxValue(max.getTotalPrice()/100);

        return payMonthInfo;
    }
}
