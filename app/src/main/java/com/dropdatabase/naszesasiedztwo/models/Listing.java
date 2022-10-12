package com.dropdatabase.naszesasiedztwo.models;

import android.os.Build;

import java.util.Optional;

public class Listing {
    private int id;
    private String title;
    private String description;
    private String coordinatesX;
    private String coordinatesY;
    private Region region;
    private User author;
    private int authorId;
    private User contractor;
    private int contractorId;

    public Listing(int id,
                   String title,
                   String description,
                   String coordinatesX,
                   String coordinatesY,
                   Region region,
                   User author,
                   User contractor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.region = region;
        this.author = author;
        this.authorId = this.author.getId();
        this.contractor = contractor;
        this.contractorId = this.contractor.getId();
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public User getContractor() {
        return contractor;
    }

    public void setContractor(User contractor) {
        this.contractor = contractor;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }
}

