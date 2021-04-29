package com.example.reto1apps;

public class Place {

    private String name;
    private String address;
    private String pathImage;
    private double latitude;
    private double length;
    private int points;

    public Place(String name, String address, String pathImage, double latitude, double length, int points) {
        this.name = name;
        this.address = address;
        this.pathImage = pathImage;
        this.latitude = latitude;
        this.length = length;
        this.points = points;
    }

    public Place(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
