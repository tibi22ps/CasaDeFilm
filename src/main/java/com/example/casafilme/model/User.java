package com.example.casafilme.model;

public class User {
    public enum Tip {
        Angajat,
        Manager,
        Administrator
    }

    public User(String username, String password, Tip tip) {
        this.username=username;
        this.password=password;
        this.tip=tip;
    }

    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }


    private String username;
    private String password;
    private Tip tip;

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Tip getTip() {
        return tip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
