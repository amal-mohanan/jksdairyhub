package com.jksdairyhub.jksdairyhub.App.Models;

public class User {
    private int Id;
    private String Name,username,password;

    public User() {
    }

    public User(int id, String name, String username, String password) {
        Id = id;
        Name = name;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
