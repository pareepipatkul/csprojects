// Pow Areepipatkul (Pasit)
// 3/12/2018
// CSE142 A
// TA: Jack Skalitzky (AQ)
// Assignment #9: Husky.java
//
// Brief method comments are still present below describing my husky's behavior.

import java.awt.*;
import java.util.*;

public class Husky extends Critter { // inherits the behavior of a Critter
	
	private Random rand;
	private int moves;
	private int direction;
	private int amount;
	private int asciiTracker;
	private char[] ascii;
	private String currentString;
	private int colorTracker;


	// Constructs a Husky 
	public Husky() {
		rand = new Random();


		colorTracker = -2;
		ascii = new char[26];
		
		for (int i = 0; i < ascii.length; i++) {
			ascii[i] = (char)(i+65);
		}
		asciiTracker = -1;
	}

	// Returns whether or not the husky eats
	public boolean eat() {
		return true;
	}

	// Returns the husky's type of Attack, will always beat out the default Ant,
	// Bird, Hippo, and Vulture, otherwise it is randomized
	//
	// String opponent - The critter opponent the husky will fight
	public Attack fight(String opponent) {
		String[] hippos = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

		if (opponent.equals("%")) {
			return Attack.ROAR;
		} else if (opponent.equals("^") || opponent.equals(">") || opponent.equals("<") || 
					  opponent.equals("V")) {
			return Attack.SCRATCH;
		} else if (opponent.equals("0")) {
			return Attack.SCRATCH;
		} 
		
		for (int i = 0; i < hippos.length; i++) {
			if (opponent.equals(hippos[i])) {
				return Attack.ROAR;
			}
		}
		
		int rps = rand.nextInt(3);
		if (rps == 0) {
			return Attack.POUNCE;
		} else if (rps == 1) {
			return Attack.SCRATCH;
		} else {
			return Attack.ROAR;
		}
		
	}

	// Returns the color of the husky (rainbow woo!)
	public Color getColor() {
		colorTracker++;
		if (colorTracker > 6) {
			colorTracker = 0;
		}
		if (colorTracker == 0) {
			return Color.RED;
		} else if (colorTracker == 1) {
			return Color.ORANGE;
		} else if (colorTracker == 2) {
			return Color.YELLOW;
		} else if (colorTracker == 3) {
			return Color.GREEN;
		} else if (colorTracker == 4) {
			return Color.BLUE;
		} else if (colorTracker == 5) {
			return Color.MAGENTA;
		} else {
			return Color.PINK;
		}
	}

	// Returns the direction of the husky's movement, starts off by moving in a random 
	// direction 5 steps at a time, while prioritizing not mating and moving into nearby enemy, 
	// then after, prioritizes moving into food sources & then each other to mate (will be pushed 
	// away from each other after mating to prevent being stuck), shuffles sides once in a while, 
	// else moves 2 steps at a time in a random direction otherwise
	public Direction getMove() {
		String[] hippos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		
		if (moves == 0 || moves % 5 == 0) {
			direction = rand.nextInt(4);
		}
		
		moves++;
		
		
		
		
		if (moves < 60) {
			for (int i = 0; i < ascii.length; i++) {
				if (getNeighbor(Direction.NORTH).equals("" + ascii[i])) {
					return Direction.SOUTH;
				} else if (getNeighbor(Direction.SOUTH).equals("" + ascii[i])) {
					return Direction.NORTH;
				} else if (getNeighbor(Direction.EAST).equals("" + ascii[i])) {
					return Direction.WEST;
				} else if (getNeighbor(Direction.WEST).equals("" + ascii[i])) {
					return Direction.EAST;
				}
			}
		}
		
		if (moves < 25) {
			
			for (int i = 0; i < hippos.length; i++) {
				if (getNeighbor(Direction.NORTH).equals(hippos[i])) {
					return Direction.NORTH;
				} else if (getNeighbor(Direction.SOUTH).equals(hippos[i])) {
					return Direction.SOUTH;
				} else if (getNeighbor(Direction.EAST).equals(hippos[i])) {
					return Direction.EAST;
				} else if (getNeighbor(Direction.WEST).equals(hippos[i])) {
					return Direction.WEST;
				}
			}
			
			
			
			if (getNeighbor(Direction.NORTH).equals("^") || getNeighbor(Direction.NORTH).equals(">") || 
				 getNeighbor(Direction.NORTH).equals("<") || getNeighbor(Direction.NORTH).equals("V") ||
				 getNeighbor(Direction.NORTH).equals("%")) {
				return Direction.NORTH;
			} else if (getNeighbor(Direction.SOUTH).equals("^") || getNeighbor(Direction.SOUTH).equals(">") || 
					 getNeighbor(Direction.SOUTH).equals("<") || getNeighbor(Direction.SOUTH).equals("V") ||
					 getNeighbor(Direction.SOUTH).equals("%")) {
				return Direction.SOUTH;
			} else if (getNeighbor(Direction.EAST).equals("^") || getNeighbor(Direction.EAST).equals(">") || 
					 getNeighbor(Direction.EAST).equals("<") || getNeighbor(Direction.EAST).equals("V") ||
					 getNeighbor(Direction.EAST).equals("%")) {
				return Direction.EAST;
			} else if (getNeighbor(Direction.WEST).equals("^") || getNeighbor(Direction.WEST).equals(">") || 
					 getNeighbor(Direction.WEST).equals("<") || getNeighbor(Direction.WEST).equals("V") ||
					 getNeighbor(Direction.WEST).equals("%")) {
				return Direction.WEST;
			}
			
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

		
		if (moves > 30) {
			for (int i = 0; i < hippos.length; i++) {
				if (getNeighbor(Direction.NORTH).equals(hippos[i])) {
					return Direction.NORTH;
				} else if (getNeighbor(Direction.SOUTH).equals(hippos[i])) {
					return Direction.SOUTH;
				} else if (getNeighbor(Direction.EAST).equals(hippos[i])) {
					return Direction.EAST;
				} else if (getNeighbor(Direction.WEST).equals(hippos[i])) {
					return Direction.WEST;
				}
			}
			
			if (getNeighbor(Direction.NORTH).equals("^") || getNeighbor(Direction.NORTH).equals(">") || 
				 getNeighbor(Direction.NORTH).equals("<") || getNeighbor(Direction.NORTH).equals("V") ||
				 getNeighbor(Direction.NORTH).equals("%")) {
				return Direction.NORTH;
			} else if (getNeighbor(Direction.SOUTH).equals("^") || getNeighbor(Direction.SOUTH).equals(">") || 
					 getNeighbor(Direction.SOUTH).equals("<") || getNeighbor(Direction.SOUTH).equals("V") ||
					 getNeighbor(Direction.SOUTH).equals("%")) {
				return Direction.SOUTH;
			} else if (getNeighbor(Direction.EAST).equals("^") || getNeighbor(Direction.EAST).equals(">") || 
					 getNeighbor(Direction.EAST).equals("<") || getNeighbor(Direction.EAST).equals("V") ||
					 getNeighbor(Direction.EAST).equals("%")) {
				return Direction.EAST;
			} else if (getNeighbor(Direction.WEST).equals("^") || getNeighbor(Direction.WEST).equals(">") || 
					 getNeighbor(Direction.WEST).equals("<") || getNeighbor(Direction.WEST).equals("V") ||
					 getNeighbor(Direction.WEST).equals("%")) {
				return Direction.WEST;
			}
			
			if (getNeighbor(Direction.NORTH).equals(".")) {
				return Direction.NORTH;
			} else if (getNeighbor(Direction.SOUTH).equals(".")) {
				return Direction.SOUTH;
			} else if (getNeighbor(Direction.EAST).equals(".")) {
				return Direction.EAST;
			} else if (getNeighbor(Direction.WEST).equals(".")) {
				return Direction.WEST;
			}
			
			
			for (int i = 0; i < ascii.length; i++) {
				if (moves % 10 != 0) {
					if (getNeighbor(Direction.NORTH).equals("" + ascii[i])) {
						return Direction.NORTH;
					} else if (getNeighbor(Direction.SOUTH).equals("" + ascii[i])) {
						return Direction.SOUTH;
					} else if (getNeighbor(Direction.EAST).equals("" + ascii[i])) {
						return Direction.EAST;
					} else if (getNeighbor(Direction.WEST).equals("" + ascii[i])) {
						return Direction.WEST;
					}
				} else {
					if (getNeighbor(Direction.NORTH).equals("" + ascii[i])) {
						return Direction.SOUTH;
					} else if (getNeighbor(Direction.SOUTH).equals("" + ascii[i])) {
						return Direction.NORTH;
					} else if (getNeighbor(Direction.EAST).equals("" + ascii[i])) {
						return Direction.WEST;
					} else if (getNeighbor(Direction.WEST).equals("" + ascii[i])) {
						return Direction.EAST;
					}
				}
				
			}
			
			// tries to circulate quadrants every once in a while based on corners of quadrant
			if (moves > 200 && moves % 3 == 0) {
				if (getX() < 15 && getY() < 13) {
					return Direction.EAST;
				} else if (getX() > 45 && getY() < 13) {
					return Direction.SOUTH;
				} else if (getX() > 45 && getY() > 39){
					return Direction.WEST;
				} else if (getX() < 15 && getY() > 39) {
					return Direction.NORTH;
				} 
			}
			
			if (moves % 2 == 0) {
				direction = rand.nextInt(4);
			}
				
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
		
		return Direction.EAST;

	}

	// Returns the String representing the husky, changes form after every move
	public String toString() {
		
		if (asciiTracker > ascii.length-1) {
			asciiTracker = -1;
		}
		asciiTracker++;
		if (asciiTracker < ascii.length) {
			currentString = "" + ascii[asciiTracker];
			return currentString;
		}
		return "!";
	}
}
