package com.dropdatabase.naszesasiedztwo.models;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String description;

    public User() {
    }

    public User(int id, String name, String lastName, String description) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
