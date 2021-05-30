package com.chezaster.scripts;

import java.util.ArrayList;

import com.chezaster.objects.Moon;
import com.chezaster.objects.Planet;
import com.chezaster.objects.Star;
import com.chezaster.objects.StarClass;
import com.chezaster.objects.StarSystem;

public class PlanetGenScripts {
	

	
	
	 

	

	public static StarSystem createStarSystem() {
		// Generating the Far Far Away Star System
		
		ArrayList<Planet> planets = new ArrayList<>(5);
		final int NUM_PLANETS = 5;
		String founder = "crono1023";
		
		
		Planet currentPlanet = null;
		ArrayList<Moon> currentMoons = new ArrayList<>();
		for (int i = 0; i < NUM_PLANETS; ++i) {
			switch(i) {
			case 0:
				currentPlanet = new Planet("Old Ben", founder, null);
				break;
				
			case 1:
				currentPlanet = new Planet("Alderaan", founder, null);
				break;
				
			case 2:
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentMoons.add(new Moon("Mc Donalds", founder));
				currentPlanet = new Planet("Naboo", founder, currentMoons);
				break;
				
			case 3:
				currentPlanet = new Planet("Vader", founder, null);
				break;
				
			case 4:
				currentPlanet = new Planet("Inshi 92/J5", founder, null);
				break;
			}
			
			planets.add(currentPlanet);
		}
		
		Star star = new Star("Far Far Away", StarClass.YELLOW);
		StarSystem farFarAway = new StarSystem(star, planets);
		
		return farFarAway;	
	}
}
