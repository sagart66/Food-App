package com.example.sagar.foodapp.Model;

public class User {
    private String Name;
    private String Password;
    public User(String m,String p){
        Name=m;
        Password=p;
    }
    User(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
