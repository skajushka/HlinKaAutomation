package com.qulix.message;

import java.util.Date;

public class Message {

    private static final String COMPUTERNAME = "COMPUTERNAME";

    private String headline;
    private String text;
    private String userName;

    public Message(String headline, String text, String userName) {
        this.headline = headline;
        this.text = text;
        this.userName = userName;
    }

    public Message (String headline, String text) {
        this.headline = headline;
        this.text = text;
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

    private static String generateUniqueString() {
        return new Date().getTime() + "_" + System.getenv(COMPUTERNAME);
    }

    public static Message createUniqueMessage() {
        String headline = generateUniqueString();
        String text = generateUniqueString();
        return new Message(headline, text);
    }

    public static Message createUniqueMessageWithUserName(String userName) {
        String headline = generateUniqueString();
        String text = generateUniqueString();
        return new Message(headline, text, userName);
    }

    public String toString() {
        return this.getHeadline() + this.getText();
    }

}
