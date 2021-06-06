package com.example.timescheduling.timetable;
// this class is used as a model class to store the time table data
public class TimeTableGetter {

    private String day,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;

    public TimeTableGetter(String day, String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8, String t9, String t10) {
        this.day = day;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
        this.t7 = t7;
        this.t8 = t8;
        this.t9 = t9;
        this.t10 = t10;
    }

    public String getDay() {
        return day;
    }

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getT3() {
        return t3;
    }

    public String getT4() {
        return t4;
    }

    public String getT5() {
        return t5;
    }

    public String getT6() {
        return t6;
    }

    public String getT7() {
        return t7;
    }

    public String getT8() {
        return t8;
    }

    public String getT9() {
        return t9;
    }

    public String getT10() {
        return t10;
    }
}
