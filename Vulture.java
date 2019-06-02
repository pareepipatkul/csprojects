// Pow Areepipatkul (Pasit)
// 3/12/2018
// CSE142 A
// TA: Jack Skalitzky (AQ)
// Assignment #9: Vulture.java
//
// Constructs a vulture, inheriting the behavior of a Bird. Represented by a black arrow
// head, vultures are initially hungry, then eats once and only eats when it is hungry. It
// can get hungry when it gets into a fight with another Critter. Otherwise, vultures have 
// identical movement and attack patterns to a bird (moves in a square and roars if  
// opponent is an ant, otherwise pounces).

import java.awt.*;

public class Vulture extends Bird { // inherits the behavior of a bird

	private boolean hungry;
	
	// Constructs a vulture, sets initial hunger to true
	public Vulture() {
		hungry = true;
	}
	
	// Returns the color of the vulture
	public Color getColor() {
		return Color.BLACK;
	}
	
	// Returns whether or not the vulture eats, eats if it is hungry, no longer hungry
	// after eating once, never eats unless hungry
	public boolean eat() {
		if (hungry) {
			hungry = false;
			return true;
		}
		return false;
	}
	
	// Returns the vulture's type of Attack, becomes hungry when fighting.
	// Identical attack behavior to a bird (roars if the opponent is an ant, 
	// otherwise pounces)
	//
	// String opponent - The critter opponent the vulture will fight
	public Attack fight(String opponent) {
		hungry = true;
		return super.fight(opponent);
	}
	
	
	
}
