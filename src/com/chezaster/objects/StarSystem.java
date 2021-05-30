package com.chezaster.objects;

import java.util.ArrayList;

public class StarSystem {
	
	private Star star;
	private ArrayList<Planet> planets;
	private short numPlanets;
	private String name;
	
	public StarSystem(Star star, ArrayList<Planet> planets) {
		this.star = star;
		this.planets = planets;
		numPlanets = (short) planets.size();
		name = star.getName();
	}

	public String getName() {
		return this.name;
	}
	public short getNumPlanets() {
		return numPlanets;
	}

	public Star getStar() {
		return star;
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	public Planet getPlanetByIndex(int index) {
		Planet newPlanet = null;
		if (index >= 0 && index < this.planets.size()) {
			newPlanet = this.planets.get(index);
		}
		
		return newPlanet;
	}
	
	
	

}
