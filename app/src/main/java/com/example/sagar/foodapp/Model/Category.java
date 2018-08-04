package com.example.sagar.foodapp.Model;

public class Category {
    private String Name;
    private String Image;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public Category() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
