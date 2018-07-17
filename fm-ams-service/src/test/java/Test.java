import com.snill.fm.bean.PayMonth;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH);

        String endYearMonth = endYear+"";
        if(endMonth < 10){
            endYearMonth += "0";
        }
        endYearMonth += endMonth;

        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -5);
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH);

        String startYearMonth = startYear+"";
        if(startMonth < 10){
            startYearMonth += "0";
        }
        startYearMonth += startMonth;


        System.out.println("startYearMonth : " + startYearMonth);
        System.out.println("endYearMonth : " + endYearMonth);
    }
}
