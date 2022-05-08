package com.example.lostandfound.model;

public class Item {
    private int item_id,phone;
    private String name, description, location,date,post_type;

    public Item() {
    }

    public Item(int item_id, int phone, String post_type, String name, String description, String location, String date) {
        this.item_id = item_id;
        this.phone = phone;
        this.post_type = post_type;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

