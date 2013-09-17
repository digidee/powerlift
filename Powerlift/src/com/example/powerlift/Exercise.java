package com.example.powerlift;

public class Exercise {

	private long id, wid, nid;
	private float weight;
	private String date, name;
	private boolean accomplished;

	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public long getWID() {
		return wid;
	}

	public void setWID(long wid) {
		this.wid = wid;
	}
	
	public long getNID() {
		return nid;
	}

	public void setNID(long nid) {
		this.nid = nid;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float f) {
		this.weight = f;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return name + " " + weight;
	}

}
