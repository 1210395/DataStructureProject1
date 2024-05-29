package com.example.phase2;

import java.util.Objects;

public class Location implements Comparable<Location> {
    // Private fields
    private String placeName;
    private BST dateList;
    private int counter;

    // Constructor
    public Location(String placeName) {
        this.placeName = placeName;
        this.dateList = new BST();
        this.counter = 0;
    }

    // Method to increment counter
    public void increaseCounter() {
        counter++;
    }

    // Getter for counter
    public int getCounter() {
        return counter;
    }

    // Setter for counter
    public void setCounter(int counter) {
        this.counter = counter;
    }

    // Getter for dateList
    public BST getDateList() {
        return dateList;
    }

    // Setter for dateList
    public void setDateList(BST dateList) {
        this.dateList = dateList;
    }

    // Getter for placeName
    public String getPlaceName() {
        return placeName;
    }

    // Setter for placeName
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return Objects.equals(placeName, location.placeName);
    }

    // Comparison method for sorting
    @Override
    public int compareTo(Location other) {
        return this.placeName.compareToIgnoreCase(other.placeName);
    }

    // String representation of Location object
    @Override
    public String toString() {
        return this.placeName;
    }
}
