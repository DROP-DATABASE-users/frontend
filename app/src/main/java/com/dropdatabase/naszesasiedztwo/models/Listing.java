package com.dropdatabase.naszesasiedztwo.models;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Listing implements Serializable {
    private int id;
    private String title;
    private String description;
    private String coordinatesX;
    private String coordinatesY;
    private int regionId;
    private User author;
    private int authorId;
    @Nullable private User contractor;
    private int contractorId;

    public Listing() {}

    public Listing(int id,
                   String title,
                   String description,
                   String coordinatesX,
                   String coordinatesY,
                   int regionId,
                   User author,
                   @Nullable User contractor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.regionId = regionId;
        this.author = author;
        this.authorId = this.author.getId();

        this.contractor = contractor;
        assert this.contractor != null;
        this.contractorId = this.contractor.getId();
    }

    public Listing(JSONObject jsonData) {
        try {
            this.id = jsonData.getInt("id");
            this.title = jsonData.getString("title");
            this.description = jsonData.getString("description");
            this.coordinatesX = jsonData.getString("coordinatesX");
            this.coordinatesY = jsonData.getString("coordinatesY");
            this.regionId = jsonData.getInt("region");
            this.author = new User(jsonData.getJSONObject("author"));
            this.authorId = jsonData.getInt("authorId");
            JSONObject contractor = jsonData.optJSONObject("contractor");
            if (contractor != null) {
                this.contractor = new User(contractor);
            }
            this.contractorId = jsonData.getInt("contractorId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(String coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public String getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(String coordinatesY) {
        this.coordinatesY = coordinatesY;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Nullable
    public User getContractor() {
        return contractor;
    }

    public void setContractor(@Nullable User contractor) {
        this.contractor = contractor;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public JSONObject toAddJSONObject() {
        JSONObject o = new JSONObject();
        try {
            o.put("title", this.title);
            o.put("coordinatesX", this.coordinatesX);
            o.put("coordinatesY", this.coordinatesY);
            o.put("description", this.description);
            o.put("region", this.regionId);
            o.put("author", this.author);
            o.put("authorId", this.authorId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
    }
}

