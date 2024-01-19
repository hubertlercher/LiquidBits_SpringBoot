package com.example.liquidbits_springboot.model;

public class Email {

    //region Properties
    private String to;
    private String subject;
    private String text;
    //endregion Properties


    //region Properties
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    //endregion Properties
}
