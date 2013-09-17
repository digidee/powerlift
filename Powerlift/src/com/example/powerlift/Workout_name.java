package com.example.powerlift;

public class Workout_name {

	private long id;
	private String name;


	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name;
	}

}
