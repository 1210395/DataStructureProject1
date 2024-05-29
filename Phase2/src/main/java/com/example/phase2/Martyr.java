package com.example.phase2;

import java.time.LocalDate;

public class Martyr implements Comparable<Martyr> {
	// Private fields
	private String name;
	private LocalDate event;
	private int age;
	private String location;
	private String district;
	private char gender;

	// Constructors
	public Martyr(int age) {
		this.age = age;
	}

	public Martyr(String name, LocalDate event, int age, String location, String district, char gender) {
		this.district = district;
		this.gender = gender;
		this.location = location;
		this.age = age;
		this.event = event;
		this.name = name;
	}

	// String representation of Martyr object
	@Override
	public String toString() {
		return (this.name + "," + this.event + "," + this.age + "," + this.location + "," + this.district + " " + this.gender);
	}

	// Getter and setter methods for district
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	// Getter and setter methods for gender
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	// Getter and setter methods for location
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// Getter and setter methods for event
	public LocalDate getEvent() {
		return event;
	}

	public void setEvent(LocalDate event) {
		this.event = event;
	}

	// Getter and setter methods for age
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Getter and setter methods for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Comparison method based on age
	@Override
	public int compareTo(Martyr other) {
		return this.age - other.age;
	}
}
