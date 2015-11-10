package edu.tsu.ge.salesman.core.model;

public class City {
    private String name;
    private int x;
    private int y;

    public City() {
    }

    // Constructs a city at chosen x, y location
    public City(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Gets city's x coordinate
    public int getX() {
        return this.x;
    }

    // Gets city's y coordinate
    public int getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    // Gets the distance to given city
    public double distanceTo(City city) {
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }

    @Override
    public String toString() {
        return getName() + ": " + getX() + ", " + getY();
    }
}
