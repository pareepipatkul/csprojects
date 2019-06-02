// Pow Areepipatkul (Pasit)
// 3/12/2018
// CSE142 A
// TA: Jack Skalitzky (AQ)
// Assignment #9: Hippo.java
//
// Constructs a hippo, inheriting the behavior of a Critter. Hippos are uniquely
// passed a hunger value. If hungry, a hippo will remain gray in color, always scratch
// as their attack, and eats. Gets full when amount of food eaten satisfies its hunger,
// then a hippo turns white, always pounces to fight, and doesn't eat anymore. Hippos always 
// move 5 steps at a time in a random direction, then chooses a new random direction. 
// They are drawn as the number representing its hunger value.

import java.awt.*;
import java.util.*;

public class Hippo extends Critter { // inherits the behavior of a Critter

	private int hunger;
	private int moves;
	private int direction;
	private Random rand;
	
	// Constructs a hippo with hunger value passed
	//
	// int hunger - The total maximum amount of food a hippo will eat
	public Hippo(int hunger) {
		this.hunger = hunger;
		rand = new Random(); 
	}
	
	// Returns the color of the hippo, returns gray if hippo is hungry,
	// otherwise returns white
	public Color getColor() {
		if (hunger > 0) {
			return Color.GRAY;
		}
		return Color.WHITE;
	}
	
	// Returns whether or not the hippo eats, hippo will only eat if it is hungry, 
	// and consumes a hunger point, else returns false
	public boolean eat() {
		if (hunger > 0) {
			hunger--;
			return true;
		}
		return super.eat();
	}
	
	// Returns the hippo's type of Attack, if hippo is hungry it will scratch, 
	// otherwise it will pounce
	//
	// String opponent - The critter opponent the hippo will fight
	public Attack fight(String opponent) {
		if (hunger > 0) {
			return Attack.SCRATCH;
		}
		return Attack.POUNCE;
	}
	
	// Returns the direction of the hippo's movement, always moves 5 steps at a time in a
	// random direction, then chooses a new random direction and repeats
	public Direction getMove() {
		if (moves > 4 || moves == 0) {
			direction = rand.nextInt(4);
			moves = 0;
		}
		moves++;

		if (direction == 0) {
			return Direction.NORTH;
		} else if (direction == 1) {
			return Direction.SOUTH;
		} else if (direction == 2) {
			return Direction.EAST;
		} else {
			return Direction.WEST;
		}
	}
	
	// Returns the String representing the hippo, varies depending on its hunger
	public String toString() {
		return "" + hunger;
	}
}
