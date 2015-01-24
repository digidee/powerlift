package com.example.powerlift;

public class ExerciseNameChild {

	private long id, wid;
	private int sets, reps;
	private String name, date;
	private double weight, increment;
	private boolean accomplished;


	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}
	
	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}
	
	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public double getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
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
