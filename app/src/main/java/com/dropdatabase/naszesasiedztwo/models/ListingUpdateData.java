package com.dropdatabase.naszesasiedztwo.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class ListingUpdateData implements Serializable {

    private String title;
    private String description;
    private String coordinatesX;
    private String coordinatesY;
    private int region;
    private int contractorId;

    public JSONObject toJSONObject() {
        JSONObject o = new JSONObject();
        try {
            if(title != null) o.put("title", title);
            if(description != null) o.put("description", description);
            if(coordinatesX != null) o.put("coordinatesX", coordinatesX);
            if(coordinatesY != null) o.put("coordinatesY", coordinatesY);
            o.put("region",region);
            o.put("contractorId", contractorId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
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

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public ListingUpdateData() {
    }

    public ListingUpdateData(Listing targetListing, User targetUser) {
        this.title = targetListing.getTitle();
        this.description = targetListing.getDescription();
        this.coordinatesX = targetListing.getCoordinatesX();
        this.coordinatesY = targetListing.getCoordinatesY();
        this.region = targetListing.getRegionId();
        this.contractorId = targetUser.getId();
    }




    public ListingUpdateData(String title, String description, String coordinatesX, String coordinatesY, int region, int contractorId) {
        this.title = title;
        this.description = description;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.region = region;
        this.contractorId = contractorId;
    }
}