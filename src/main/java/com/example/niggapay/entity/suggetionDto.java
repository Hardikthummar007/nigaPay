package com.example.niggapay.entity;

 public class suggetionDto {

    private String name;
    private String mapboxId;
    private String fullAddress;
    private String placeFormatted;
    private String featureType;

    private Integer distance;

    private String country;
    private String region;
    private String place;


    public suggetionDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapboxId() {
        return mapboxId;
    }

    public void setMapboxId(String mapboxId) {
        this.mapboxId = mapboxId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPlaceFormatted() {
        return placeFormatted;
    }

    public void setPlaceFormatted(String placeFormatted) {
        this.placeFormatted = placeFormatted;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    // getters / setters
}
