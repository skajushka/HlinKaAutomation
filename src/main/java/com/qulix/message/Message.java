package com.qulix.message;

public class Message {

    private String headline;
    private String text;
    private String userName;

    public Message(String headline, String text, String userName) {
        this.headline = headline;
        this.text = text;
        this.userName = userName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }

    public String toString() {
        String result = headline + " " + text + " " + userName;
        return result;
    } // to delete!

}
