package com.sncfc.analytic.util;

import java.text.NumberFormat;

/**
 * Created by 123 on 2017/6/5.
 */
public class Format {
    /**
     * 数字格式化保留1位小数
     * @param num
     * @return
     */
    public static String maximumFractionDigitsOne(Double num){
        NumberFormat df= NumberFormat.getNumberInstance() ;
        df.setMaximumFractionDigits(1);
        return  df.format(num).replaceAll(",","");
    }

    /**
     * 增长率百分比，出错返回“-”
     * @param a
     * @param b
     * @return
     */
    public static String upRateStringNoValid(String a,String b){
       return upRateStringNoValid(a,b,"-");
    }
    /**
     * 增长率百分比，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String upRateStringNoValid(String a,String b,String defaultStr){
        try {
            if(b == null || "0".equals(b.trim())){
                return defaultStr;
            }
            Double result =  (Double.parseDouble(a)-Double.parseDouble(b)) * 100 / Double.parseDouble(b);
            return maximumFractionDigitsOne(result);
        }catch (Exception e){
            return defaultStr;
        }
    }
    /**
     * 增长率百分比，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String upRateStringNoValid(Object a,Object b,String defaultStr){
       if(a == null || b== null){
           return defaultStr;
       }
       return upRateStringNoValid(a.toString(),b.toString(),defaultStr);
    }
    /**
     * 除法，出错返回“-”
     * @param a
     * @param b
     * @return
     */
    public static String division(String a,String b){
        return division(a,b,"-");
    }
    /**
     * 除法百分比，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String division(String a,String b,String defaultStr){
        try {
            if(b == null || ( Double.parseDouble(b) == 0 )){
                return defaultStr;
            }
            Double result =  Double.parseDouble(a) * 100 / Double.parseDouble(b);
            return maximumFractionDigitsOne(result);
        }catch (Exception e){
            return defaultStr;
        }
    }
    /**
     * 除法百分比，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String division(Object a,Object b,String defaultStr){
        try {
            return division(a.toString(),b.toString(),defaultStr);
        }catch (Exception e){
            return defaultStr;
        }
    }
    /**
     * 除法，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String divisionOnly(Object a,Object b,String defaultStr){
       if(a==null || b==null){
           return defaultStr;
       }
       return divisionOnly(a.toString(),b.toString(),defaultStr);
    }
    /**
     * 除法，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String divisionOnly(String a,String b,String defaultStr){
        try {
            if(b == null || ( Double.parseDouble(b) == 0 )){
                return defaultStr;
            }
            Double result =  Double.parseDouble(a) / Double.parseDouble(b);
            return maximumFractionDigitsOne(result);
        }catch (Exception e){
            return defaultStr;
        }
    }
    /** 减法，出错返回自定义
     * @param a
     * @param b
     * @return
     * */
    public static String subtraction(String a,String b,String defaultStr){
        try {
            Double result =  Double.parseDouble(a) - Double.parseDouble(b);
            return maximumFractionDigitsOne(result);
        }catch (Exception e){
            return defaultStr;
        }
    }
    /**
     * 加，出错返回自定义
     * @param a
     * @param b
     * @return
     */
    public static String plus(Object a,Object b,String defaultStr){
        if(a == null || b== null){
            return defaultStr;
        }
        try {
            return maximumFractionDigitsOne(Double.parseDouble(a.toString())+Double.parseDouble(b.toString()));
        }catch (Exception e){
            return defaultStr;
        }
    }
    /**
     * 元转万元取2位小数
     * @return
     */
    public static String wan(Object money){
        try{
            return maximumFractionDigitsOne(Double.parseDouble(money.toString())/10000);
        }catch (Exception e){
            return "-";
        }
    }

    public static void main(String[] args) {
        System.out.println(division("29948792.99","1863176634.18","-"));
    }
}
