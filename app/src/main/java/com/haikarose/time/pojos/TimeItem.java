package com.haikarose.time.pojos;

import android.os.Bundle;

public class TimeItem {


    public static final String TYPE_TUT="TUT";
    public static final String TYPE_LES="LES";


    private Long id;

    private String time;
    private String name;

    private String code;


    private String type;

    private String group;
    private String venue;

    private String course;
    private String instructor;

    private String day;




    public TimeItem(){

    }

    public TimeItem(String time,String code,String name,String type,String group,String course,String instructor,String day,String venue){
        this.code=code;
        this.time=time;
        this.name=name;
        this.type=type;
        this.group=group;
        this.course=course;
        this.instructor=instructor;
        this.day=day;
        this.venue=venue;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDay(String day){
        this.day=day;
    }



    public String getGroup() {
        return group;
    }

    public String getVenue() {
        return venue;
    }

    public String getCourse() {
        return course;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getType(){
        return this.type;
    }

    public String getDay(){ return this.day;}



    public static TimeItem fromBundle(Bundle bundle){

        TimeItem item=new TimeItem();
        item.setVenue(bundle.getString("Venue"));
        item.setInstructor(bundle.getString("Instructor"));
        item.setCode(bundle.getString("Code"));
        item.setName(bundle.getString("Subject"));
        item.setTime(bundle.getString("Time"));
        item.setType(bundle.getString("Type"));

        return item;
    }

    public static Bundle toBundle(TimeItem item){

        Bundle bundle=new Bundle();

        bundle.putString("Venue",item.getVenue());
        bundle.putString("Instructor",item.getInstructor());
        bundle.putString("Code",item.getCode());
        bundle.putString("Subject",item.getName());
        bundle.putString("Time",item.getTime());
        bundle.putString("Type",item.getType());

        return bundle;
    }


}
