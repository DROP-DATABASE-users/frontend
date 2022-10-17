package com.dropdatabase.naszesasiedztwo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Region {
    public Region() {
    }

    public Region(int id) {
        this.id = id;
    }
    public Region(JSONObject jsonData) {
        try {
            this.id = jsonData.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
