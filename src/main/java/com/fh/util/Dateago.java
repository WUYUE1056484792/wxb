package com.fh.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dateago {

    public static String currentDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -0);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static String oneAgoDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static String twoAgoDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static String threeAgoDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static String fourAgoDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -4);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static String fiveAgoDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static String sixAgoDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static void main(String[] args) {
        currentDay();
        oneAgoDay();
        twoAgoDay();
        threeAgoDay();
        fourAgoDay();
        fiveAgoDay();
        sixAgoDay();

    }
}
