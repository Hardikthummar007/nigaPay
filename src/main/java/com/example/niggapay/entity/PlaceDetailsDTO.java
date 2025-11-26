package com.example.niggapay.entity;
public class PlaceDetailsDTO {
    private double latitude;
    private double longitude;
    private String name;
    private String placeFormatted;
    private String fullAddress;
    private String humanReadableAddress;

    // Constructors
    public PlaceDetailsDTO() {}

    public PlaceDetailsDTO(double latitude, double longitude, String name, String placeFormatted, String fullAddress, String humanReadableAddress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.placeFormatted = placeFormatted;
        this.fullAddress = fullAddress;
        this.humanReadableAddress = humanReadableAddress;
    }

    // Getters & Setters
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHumanReadableAddress() {
        return humanReadableAddress;
    }

    public void setHumanReadableAddress(String humanReadableAddress) {
        this.humanReadableAddress = humanReadableAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceFormatted() {
        return placeFormatted;
    }

    public void setPlaceFormatted(String placeFormatted) {
        this.placeFormatted = placeFormatted;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}