package com.haikarose.time.pojos;


/**
 * Created by root on 10/30/16.
 */

public class CourseItem {


    private Long id;

    private String abbreviation;

    private String longForm;

    private String college;

    public CourseItem(){}


    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getLongForm() {
        return longForm;
    }

    public void setLongForm(String longForm) {
        this.longForm = longForm;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }


}
