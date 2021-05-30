/**
 * 
 */
package com.chezaster.objects;

import java.util.ArrayList;

/**
 * @author crono
 *
 */
public class Planet {
	
	// Private member variables
	private String name;
	private String founder;
	private byte numMoons;    // No planet is going to exceed 128 moons
	private ArrayList<Moon> moons = new ArrayList<>();
	
	public Planet(String name, String founder, ArrayList<Moon> moons) {
		this.name = name;
		this.founder = founder;
		this.moons = moons;
		if (moons != null) {
			this.numMoons = (byte) moons.size();
		}
		else {
			this.numMoons = 0;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFounder() {
		return founder;
	}
	public byte getNumMoons() {
		return numMoons;
	}

	public ArrayList<Moon> getMoons() {
		return moons;
	}
	
	public Moon getMoonByIndex(short index) {
		Moon newMoon = null;
		if (index >= 0 && index < this.numMoons) {
			newMoon = this.moons.get(index);
		}
		return newMoon;
	}
	
	
	
	
}
