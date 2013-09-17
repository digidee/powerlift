package com.example.powerlift;

public class ExerciseName {

	private long id, wid;
	private String name;
	private double weight;
	private boolean accomplished;


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

	
	public long getWID() {
		return wid;
	}

	public void setWID(long wid) {
		this.wid = wid;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean getAccomplished() {
		return accomplished;
	}

	public void setAccomplished(boolean accomplished) {
		this.accomplished = accomplished;
	}
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name;
	}

}
