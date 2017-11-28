package com.example.fragment.theoryandpractice.datePractice;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by xiaolong on 2017/11/3.
 * email：xinxiaolong123@foxmail.com
 */

public class DifferenceDay {

    public static void main(String[] args) {
        Calendar nowCalendar = Calendar.getInstance();

        nowCalendar.get(Calendar.DATE);
        Calendar singeDay = Calendar.getInstance();
        singeDay.set(2017, 10, 3);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(nowCalendar.getTime());
        String single = format.format(singeDay.getTime());


        System.out.println(now + "     " + single);
        System.out.println(getDifferenceDayNum(nowCalendar, singeDay));

    }


    public static int getDifferenceDayNum(Calendar nowCalendar, Calendar targetCalendar) {
        int day1 = nowCalendar.get(Calendar.DAY_OF_YEAR);
        int day2 = targetCalendar.get(Calendar.DAY_OF_YEAR);

        int year1 = nowCalendar.get(Calendar.YEAR);
        int year2 = targetCalendar.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }


    private static void printDate(Calendar calendar) {
        System.out.println("DAY_OF_MONTH=" +
                calendar.get(Calendar.DAY_OF_MONTH)
                + "  YEAR=" +
                calendar.get(Calendar.YEAR)
                + "    DATE=" +
                calendar.get(Calendar.DATE)
                + "    DAY_OF_YEAR=" +
                calendar.get(Calendar.DAY_OF_YEAR)
                + "    DAY_OF_MONTH=" +
                calendar.get(Calendar.DAY_OF_MONTH)
                + "    DAY_OF_WEEK=" +
                calendar.get(Calendar.DAY_OF_WEEK)
                + "    DAY_OF_WEEK_IN_MONTH=" +
                calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
    }

}
