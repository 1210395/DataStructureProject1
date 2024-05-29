package com.example.phase2;

import java.util.Objects;

public class District implements Comparable<District> {
    // Private fields
    private String name;
    private BST locationTree;
    private int count;

    // Constructor
    public District(String name) {
        this.name = name;
        this.locationTree = new BST();
        this.count = 0;
    }

    // Getter for count
    public int getCount() {
        return count;
    }
    // Setter for count
    public void setCount(int count) {
        this.count = count;
    }

    // Getter for locationTree
    public BST getLocationTree() {
        return locationTree;
    }

    // Setter for locationTree
    public void setLocationTree(BST locationTree) {
        this.locationTree = locationTree;
    }
    // Method to increment count
    public void incrementCount() {
        count++;
    }


    // Getter for name
    public String getName() {
        return name;
    }
    // Equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        District district = (District) obj;
        return Objects.equals(name, district.name);
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
    // Comparison method for sorting
    @Override
    public int compareTo(District other) {
        return this.name.trim().compareToIgnoreCase(other.name.trim());
    }




    // String representation of District
    @Override
    public String toString() {
        return this.name;
    }
}
