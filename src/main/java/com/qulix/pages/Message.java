package com.qulix.pages;

public class Message {

    private String headline;
    private String text;

    public Message(String headline, String text) {
        this.headline = headline;
        this.text = text;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
