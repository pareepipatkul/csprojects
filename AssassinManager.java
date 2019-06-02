// Pow Areepipatkul (Pasit)
// 01/29/19
// CSE143 BQ
// TA: Sejin Kim
// Assignment #3: AssassinManager.java
//
// Manages a game of assassin, where a list of players each has a target that 
// they are trying to assassinate. A circular chain of targets is established
// in the kill ring, where each player is stalking the next player in the 
// list. If a player is killed, they will be moved to the front of the 
// graveyard. When only one player remains in the kill ring, they are crowned
// the winner.

import java.util.*;

public class AssassinManager {
	
	private AssassinNode killRing;
	private AssassinNode graveyard;
	
	// Pre: List must not be empty (otherwise throws IllegalArgumentException)
	//
	// Post: Constructs the game manager, adding the names from given list
	// into a linked kill ring
	public AssassinManager(List<String> names) {
		if (names == null || names.size() == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = names.size() - 1; i >= 0; i--) {
			killRing = new AssassinNode(names.get(i), killRing);
		}
	}
	
	// Prints the names and targets in the kill ring. If there is one person
	// in the ring, it will report that the person is stalking themselves.
	public void printKillRing() {
		AssassinNode curr = killRing;
		while (curr != null) {
			if (curr.next != null) {
				System.out.println("    " + curr.name + " is stalking " + curr.next.name);
			} else {
				System.out.println("    " + curr.name + " is stalking " + killRing.name);
			}
			curr = curr.next;
		}
	}
	
	// Prints the names and killers of the people in the graveyard, printing
	// the most recently killed name first and the next recently killed. 
	// Produces no output if graveyard is empty.
	public void printGraveyard() {
		AssassinNode curr = graveyard;
		while (curr != null) {
			System.out.println("    " + curr.name + " was killed by " + curr.killer);
			curr = curr.next;
		}
	}
	
	// Returns whether the given name is in the current kill ring. 
	// Ignores casing when comparing names.
	public boolean killRingContains(String name) {
		return listContains(killRing, name);
	}
	
	// Returns whether the given name is in the current graveyard.
	// Ignores casing when comparing names.
	public boolean graveyardContains(String name) {
		return listContains(graveyard, name);
	}
	
	// Returns whether the given list contains the given name.
	// Ignores casing.
	private boolean listContains(AssassinNode curr, String name) {
		while (curr != null) {
			if (curr.name.equalsIgnoreCase(name)) {
				return true;
			} 
			curr = curr.next;
		}
		return false;
	}
	
	// Returns true if the game is over (kill ring has one person remaining), 
	// otherwise returns false
	public boolean gameOver() {	
		return killRing.next == null;
	}
	
	// Returns the name of the winner. If the game is not over, returns null.
	public String winner() {
		if (gameOver()) {
			return killRing.name;
		}
		return null;
	}
	
	// Pre: Given name must be part of the current kill ring (otherwise 
	// throws IllegalArgumentException), and game must not be over
	// (otherwise throws IllegalStateException)
	//
	// Post: Kills the person with the given name, transferring them from
	// the kill ring to the graveyard.
	public void kill(String name) {
		if (!killRingContains(name)) {
			throw new IllegalArgumentException();
		} 
		if (gameOver()) {
			throw new IllegalStateException();
		}
		AssassinNode current = killRing;
		AssassinNode killTarget;
		// If person to be killed is at the front of the kill ring
		if (killRing.name.equalsIgnoreCase(name)) { 
			killTarget = killRing;      
			while (current.next != null) {
				current = current.next;
			}
			killTarget.killer = current.name;
			killRing = killTarget.next;
		} else {
			while (!current.next.name.equalsIgnoreCase(name)) {
				current = current.next;
			}
			killTarget = current.next;
			killTarget.killer = current.name;
			current.next = killTarget.next;
		}
		killTarget.next = graveyard;
		graveyard = killTarget;
	}  
}  
