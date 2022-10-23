package com.dropdatabase.naszesasiedztwo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginData {
    public LoginData() {
    }

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginData(JSONObject json) {
        username = json.optString("username");
        password = json.optString("password");
    }

    public JSONObject toJsonObject() {
        JSONObject o = new JSONObject();
        try {
            o.put("username", username);
            o.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
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

    private String username;
    private String password;

}
