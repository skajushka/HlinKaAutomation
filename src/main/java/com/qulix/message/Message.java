package com.qulix.message;

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

    public String getText() {
        return text;
    }
}
