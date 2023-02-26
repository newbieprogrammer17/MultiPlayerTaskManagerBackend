package com.b902.watersupply;

public class UsernamePassword {

    private String username;
    private String password;


    public UsernamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsernamePassword() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
