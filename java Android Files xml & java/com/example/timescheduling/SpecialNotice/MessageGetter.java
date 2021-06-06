package com.example.timescheduling.SpecialNotice;
// this class is used as a model class to store the time table data
public class MessageGetter {

    private String message,date;

    public MessageGetter(String message, String date) {
        this.message = message;
        this.date = date;

    }

    public MessageGetter(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }


}
