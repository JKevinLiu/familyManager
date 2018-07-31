package com.snill.fm.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static List<Integer> getLastSIXMonth(){
        List<Integer> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH);

        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -5);

        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH);

        if(startMonth > endMonth){ //跨年
            for(int i = startMonth; i <= 12; i++){
                String tmpYearMonth = startYear+"";
                if(startMonth < 10){
                    tmpYearMonth += "0";
                }
                tmpYearMonth += startMonth;
                list.add(Integer.parseInt(tmpYearMonth));
            }

            for(int i = 1; i <= endMonth; i++){
                String tmpYearMonth = endYear+"0"+i;
                list.add(Integer.parseInt(tmpYearMonth));
            }

        }else{
            for(int i = startMonth; i <= endMonth; i++){
                String tmpYearMonth = startYear+"";
                if(startMonth < 10){
                    tmpYearMonth += "0";
                }
                tmpYearMonth += i;
                list.add(Integer.parseInt(tmpYearMonth));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = DateUtils.getLastSIXMonth();
        System.out.println();
    }
}
