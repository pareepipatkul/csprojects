// Pow Areepipatkul (Pasit)
// 3/12/2018
// CSE142 A
// TA: Jack Skalitzky (AQ)
// Assignment #9: Bird.java
//
// Constructs a bird, inheriting the behavior of a Critter. Birds are represented by
// blue arrowheads, and never eats. Birds will always roar if the opponent is an ant,
// otherwise it will pounce. Moves in a clockwise square pattern, 3 steps at a time.

import java.awt.*;

public class Bird extends Critter { // inherits the behavior of a Critter
	
	private int moves;
	private Direction direction;
	
	// Constructs a bird, sets initial direction to north
	public Bird() {
		direction = Direction.NORTH;
	}
	
	// Returns the color of the bird
	public Color getColor() {
		return Color.BLUE;
	}
	
	// Returns the bird's type of Attack, roars if the opponent is an ant, 
	// otherwise pounces
	//
	// String opponent - The critter opponent the bird will fight
	public Attack fight(String opponent) {
		if (opponent.equals("%")) {
			return Attack.ROAR;
		}
		return Attack.POUNCE;
	}
	
	// Returns the direction of the bird's movement, first goes north 3 times, 
	// then east 3x, then south 3x, then west 3x, then keeps repeating
	public Direction getMove() {
		moves++;
		if (moves > 12) { // resets the pattern
			moves = 1;
		}
		
		if (moves < 4) { // moves 1 - 3
			direction = Direction.NORTH;
		} else if (moves < 7) { // moves 4 - 6
			direction = Direction.EAST;
		} else if (moves < 10) { // moves 7 - 9
			direction = Direction.SOUTH;
		} else { // moves 10 - 12
			direction = Direction.WEST;
		}
		return direction;
	}
	
	// Returns the String representing the bird, so that the tip of the pointing
	// arrow matches the direction the bird is traveling
	public String toString() {
		if (direction == Direction.NORTH) {
			return "^";
		} else if (direction == Direction.EAST) {
			return ">";
		} else if (direction == Direction.SOUTH) {
			return "V";
		} else { // direction == Direction.WEST
			return "<";
		}
	}
}
