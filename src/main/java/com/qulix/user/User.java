package com.qulix.user;

public class User {

    private String login;
    private String password;

    public User(String username, String password) {
        this.login = username;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
