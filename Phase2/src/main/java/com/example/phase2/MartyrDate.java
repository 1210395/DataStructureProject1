package com.example.phase2;

import java.time.LocalDate;
import java.util.Objects;

public class MartyrDate implements Comparable<MartyrDate> {
    // Private fields
    private SingleLinkedList martyrsList;
    private LocalDate eventDate;
    private Node maximumDateNode;
    private double ageSum;

    // Constructor
    public MartyrDate(LocalDate date) {
        this.eventDate = date;
        martyrsList = new SingleLinkedList();
    }

    // Method to add age to the sum
    public void addToAgeSum(int age) {
        ageSum += age;
    }

    // Getter for ageSum
    public double getAgeSum() {
        return ageSum;
    }

    // Setter for ageSum
    public void setAgeSum(double ageSum) {
        this.ageSum = ageSum;
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MartyrDate that = (MartyrDate) obj;
        return eventDate.equals(that.getEventDate());
    }

    // String representation of MartyrDate object
    @Override
    public String toString() {
        return eventDate + "  :" + martyrsList.size();
    }

    // Getter for maximumDateNode
    public Node getMaximumDateNode() {
        return maximumDateNode;
    }

    // Setter for maximumDateNode
    public void setMaximumDateNode(Node maximumDateNode) {
        this.maximumDateNode = maximumDateNode;
    }

    // Getter for martyrsList
    public SingleLinkedList getMartyrsList() {
        return martyrsList;
    }

    // Setter for martyrsList
    public void setMartyrsList(SingleLinkedList martyrsList) {
        this.martyrsList = martyrsList;
    }

    // Getter for eventDate
    public LocalDate getEventDate() {
        return eventDate;
    }

    // Setter for eventDate
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    // Comparison method for sorting based on eventDate
    @Override
    public int compareTo(MartyrDate other) {
        return this.eventDate.compareTo(other.getEventDate());
    }

    // Comparison method for sorting based on martyrs count
    public int compareByMartyrsCount(MartyrDate other) {
        return this.getMartyrsList().size() - other.getMartyrsList().size();
    }
}
