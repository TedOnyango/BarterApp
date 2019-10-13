package com.ted.barterapp;

import java.io.Serializable;

public class BarterItem implements Serializable {
    private String id;
    private String title;
    private String description;
    private String estimatedValue;
    private String preferredItmes;
    private String imageUrl;

    public BarterItem() {}


    public BarterItem(String title, String description, String estimatedValue, String preferredItmes, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedValue = estimatedValue;
        this.preferredItmes = preferredItmes;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(String estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getPreferredItmes() {
        return preferredItmes;
    }

    public void setPreferredItmes(String preferredItmes) {
        this.preferredItmes = preferredItmes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

