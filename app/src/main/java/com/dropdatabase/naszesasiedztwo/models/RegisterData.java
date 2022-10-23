package com.dropdatabase.naszesasiedztwo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterData {
    private String name;
    private String lastName;
    private String description;
    private String username;
    private String password;

    public RegisterData() {
    }

    public RegisterData(JSONObject json) {
        username = json.optString("username");
        name = json.optString("name");
        lastName = json.optString("lastName");
        description = json.optString("description");
        password = json.optString("password");
    }


    public RegisterData(String name, String lastName, String description, String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.description = description;
        this.username = username;
        this.password = password;
    }

    public JSONObject toJsonObject() {
        JSONObject o = new JSONObject();
        try {
            o.put("name", name);
            o.put("lastName", lastName);
            o.put("description", description);
            o.put("username", username);
            o.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
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
