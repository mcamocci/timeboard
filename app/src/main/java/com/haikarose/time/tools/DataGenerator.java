package com.haikarose.time.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 10/27/16.
 */

public class DataGenerator {


    public static void setCourse(Context context, String course){

        SharedPreferences sharedPreferences=context.getSharedPreferences("COURSES",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.putString("COURSE",course);
        editor.commit();

    }

    public static String getCourse(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("COURSES",0);
        String content=sharedPreferences.getString("COURSE","bcs2");
        return content;
    }

    public static void setDayOfWeek(Context context,int day){

        SharedPreferences sharedPreferences=context.getSharedPreferences("COURSES",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.putString("DAY",Integer.toString(day));
        editor.commit();

    }

    public static String getDayOfWeek(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("COURSES",0);
        String content=sharedPreferences.getString("DAY","2");
        return content;
    }

}
