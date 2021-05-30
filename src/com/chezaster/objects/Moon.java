package com.chezaster.objects;

public class Moon extends Planet {
	
	public Moon(String name, String founder) {
		super(name, founder, null); // Moons don't have moons, setting moons ArrayList to null.
	}
}
