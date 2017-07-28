package com.sncfc.analytic.util;

import com.sncfc.analytic.exception.ResultException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2017/6/1.
 */
public class DateUtil {
    public static List getYearsFromBegin(int beginYear){
        Calendar calendar = Calendar.getInstance();
        int endYear = calendar.getWeekYear()+1;
        List<Integer> years = new ArrayList<>();
        while (beginYear <= endYear){
            years.add(beginYear);
            beginYear++;
        }
        return years;
    }

    public static List getYearsFromBeginOn(int beginYear){
        Calendar calendar = Calendar.getInstance();
        int endYear = calendar.getWeekYear();
        List<Integer> years = new ArrayList<>();
        while (beginYear <= endYear){
            years.add(beginYear);
            beginYear++;
        }
        return years;
    }

    /**
     * 当前年季度距年初天数
     * @param yearNum 年份
     * @param quarterNum 季度
     * @return
     */
    public static int quarterDays(int yearNum, int quarterNum) {
        Calendar calendar = Calendar.getInstance();
        switch (quarterNum) {
            case 1:
                calendar.set(yearNum,2,31);
                break;
            case 2:
                calendar.set(yearNum,5,30);
                break;
            case 3:
                calendar.set(yearNum,8,30);
                break;
            case 4:
                calendar.set(yearNum,11,31);
                break;
            default:
                throw new ResultException("季度应该为[1,2,3,4]");
        }
        return calendar.get(Calendar.DAY_OF_YEAR) ;
    }
    /**
     *当前年昨天距当前年初天数
     * @param yearNum 年份
     * @return
     */
    public static int dayOfYear(int yearNum) {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, yearNum);
        calendar.add(calendar.DATE,-1);

        return calendar.get(Calendar.DAY_OF_YEAR) ;
    }
    /**
     *当前年
     * @return
     */
    public static int year() {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) ;
    }


    /**
     * 昨天格式yyyy-MM-dd
     * @return
     */
    public static String yesterday(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE,-1);
        return sf.format(calendar.getTime());
    }
    /**
     * 去年今日 格式yyyy-MM-dd
     * @return
     */
    public static String lastYearDate(String dateStr) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = sf.parse(dateStr);
        calendar.setTime(date);
        calendar.add(calendar.YEAR,-1);
        return sf.format(calendar.getTime());
    }
    /**
     * 季度
     * @return
     */
    public static int quarter() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) ;
        if(month <3){
            return 1;
        }else if(month<6){
            return 2;
        }else if(month<9){
            return 3;
        }else {
            return 4;
        }
    }



    public static void main(String[] args) {
        System.out.println(dayOfYear(2017));
    }

    /**
     * 计算日期格式yyyyMMdd间隔天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean moreThanThirtyOneDays(String beginDate, String endDate) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        double days = 0;
        try {
            Date begin = sf.parse(beginDate);
            Date date = sf.parse(endDate);
            days = (begin.getTime() -date.getTime()) /(60*60*24*1000);
        } catch (ParseException e) {
            throw new ResultException("日期格式不正确");
        }
        return days >= 31;
    }
}
