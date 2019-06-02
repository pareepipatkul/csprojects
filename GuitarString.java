// Pow Areepipatkul (Pasit)
// 01/22/19
// CSE143 BQ
// TA: Sejin Kim
// Assignment #2: GuitarString.java
//
// A GuitarString simulates a vibrating guitar string of a given frequency,
// by tracking the ring buffer.

import java.util.*;

public class GuitarString {
	
	private Queue<Double> ringBuffer; 
	private int capacity;
	public static final double ENERGY_DECAY_FACTOR = 0.996;
	
	// Pre: frequency > 0, calculated capacity must be >= 2 
	// (otherwise throws IllegalArgumentException)
	//
	// Post: Constructs a guitar string given the frequency, representing a
	// guitar string at rest by creating a ring buffer with desired capacity.
	public GuitarString(double frequency) {
		capacity = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
		if (frequency <= 0 || capacity < 2) {
			throw new IllegalArgumentException();
		}
		ringBuffer = new LinkedList<Double>();
		for (int i = 0; i < capacity; i++) {
			ringBuffer.add(0.0);
		}
	}
	
	// Pre: init.length >= 2 (otherwise throws IllegalArgumentException)
	//
	// Post: Constructs a guitar string and sets the contents of the ring 
	// buffer equal to the values of given array.
	public GuitarString(double[] init) {
		if (init.length < 2) {
			throw new IllegalArgumentException();
		}
		ringBuffer = new LinkedList<Double>();
		for (double values : init) {
			ringBuffer.add(values);
		}
	}
	
	// Replace the elements of the ring buffer with random values between 
	// -0.5 (inclusive) and 0.5 (exclusive)
	public void pluck() {
		for (int i = 0; i < capacity; i++) {
			ringBuffer.add(Math.random() - 0.5);
			ringBuffer.remove();
		}
	}
	
	// Deletes the sample at the front of the ring buffer, then adds a value
	// representing the average of the first two samples multiplied by the 
	// energy decay factor to the end of the ring buffer.
	public void tic() {
		double front = ringBuffer.remove();
		double afterFront = ringBuffer.peek();
		double result = ((front + afterFront)/2) * ENERGY_DECAY_FACTOR;
		ringBuffer.add(result);
	}
	
	// Returns the value at the front of the ring buffer
	public double sample() {
		return ringBuffer.peek();
	}
}
