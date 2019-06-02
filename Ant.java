// Pow Areepipatkul (Pasit)
// 3/12/2018
// CSE142 A
// TA: Jack Skalitzky (AQ)
// Assignment #9: Ant.java
//
// Constructs an ant, inheriting the behavior of a Critter. Ants are red in color,
// will always eat food, and always chooses to scratch an opponent in combat. They 
// are represented by the "%" sign. The ant moves in a zigzag pattern, either 
// alternating between south and east or north and east depending on value passed 
// during construction. 

import java.awt.*;

public class Ant extends Critter { // inherits the behavior of a Critter
	
	private boolean walkSouth;
	private boolean firstMove;
	
	// Constructs an ant with a boolean passed through
	//
	// boolean walkSouth - Determines whether or not the ant walks south
	public Ant(boolean walkSouth) {
		this.walkSouth = walkSouth;
	}
	
	// Returns the color of the ant
	public Color getColor() {
		return Color.RED;
	}
	
	// Returns whether or not the ant eats
	public boolean eat() {
		return true;
	}
	
	// Returns the ant's type of Attack 
	//
	// String opponent - The critter opponent the ant will fight
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}
	
	// Returns the direction of the ant's movement, if walkSouth is true, moves in an
	// alternating south and east, otherwise, alternates between north and east in a
	// zigzag pattern
	public Direction getMove() {
		firstMove = !firstMove; // alternates the moves, initially starting with true
		
		if (walkSouth && firstMove) {
			return Direction.SOUTH;
		} else if (!walkSouth && firstMove) {
			return Direction.NORTH;
		} else { // !firstMove
			return Direction.EAST;
		}

	}
	
	// Returns a String representing the ant.
	public String toString() {
		return "%";
	}
	
	
}
