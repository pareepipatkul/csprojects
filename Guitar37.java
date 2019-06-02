// Pow Areepipatkul (Pasit)
// 01/22/19
// CSE143 BQ
// TA: Sejin Kim
// Assignment #2: Guitar37.java
//
// A Guitar37 represents a guitar with 37 different strings with the 
// ability to play specific notes.

public class Guitar37 implements Guitar {
   
	private GuitarString[] strings;
	
	// Stores how much time has advanced forward
	private int ticCount;
	
	// A custom string used to map keys to the different guitar strings.
	public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   
	// Constructs a guitar with 37 different strings from 110Hz to 880Hz.
	public Guitar37() {
		strings = new GuitarString[KEYBOARD.length()];
		ticCount = 0;
		for (int i = 0; i < strings.length; i++) {
			strings[i] = new GuitarString(440.0 * Math.pow(2, (i-24.0)/12));
		}
	}
	
	// Plays a note given a pitch value. If note can't be played, it is
	// ignored.
	public void playNote(int pitch) {
		int index = pitch + 24;
		if (index > -1 && index < strings.length) {
			strings[index].pluck();
		}
	}
	
	// Returns whether the given note exists (true if exists, otherwise false).
   public boolean hasString(char key) {
   	return KEYBOARD.indexOf(key) != -1;
   }
   
   // Key given must be one of the 37 valid keys (otherwise throws 
   // IllegalArgumentException).
   //
   // Post: Plays a note given the character representing the key.
   public void pluck(char key) {
   	if (!hasString(key)) {
   		throw new IllegalArgumentException();
   	}
   	strings[KEYBOARD.indexOf(key)].pluck();
   }
  
   // Returns the sum of all strings samples of the guitar.
   public double sample() {
   	double sum = 0.0;
   	for (GuitarString index : strings) {
   		sum += index.sample();
   	}
   	return sum;
   }
   
   // Advances the time forward by one "tic" and increments its count
   public void tic() {
   	for (GuitarString index : strings) {
   		index.tic();
   	}
   	ticCount++;
   }
   
   // Returns the current time.
   public int time() {
   	return ticCount;
   }
}