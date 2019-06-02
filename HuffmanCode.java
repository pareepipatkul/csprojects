// Pow Areepipatkul (Pasit)
// 03/11/19
// CSE143 BQ
// TA: Sejin Kim
// Assignment #8: HuffmanCode.java
//
// Represents a "huffman code" which is used to compress data. Can be used to
// compress and decompress text from a given file. To compress from a valid file, 
// the computer will write out the huffman codes to a file in the
// standard format. To decompress, the computer reads in a code file in 
// the standard format and produces the text to a new file.

import java.util.*;
import java.io.*;

public class HuffmanCode {

	private HuffmanNode overallRoot;
	public static final int EMPTY_CHAR = 256; // default ASCII value for an empty char
	
	// Constructs a new HuffmanCode with the given array of frequencies for the
	// corresponding characters.
	public HuffmanCode(int[] frequencies) {
		Queue<HuffmanNode> charChain = new PriorityQueue<HuffmanNode>();
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] > 0) {
				charChain.add(new HuffmanNode(i, frequencies[i]));
			}
		}
		overallRoot = combineNodes(charChain);
	}
	
	// Returns a single HuffmanNode tree given a chain of HuffmanNodes, combining
	// them until one is left.
	private HuffmanNode combineNodes(Queue<HuffmanNode> charChain) {
		HuffmanNode firstRoot = charChain.peek();
		while (charChain.size() > 1) {
			HuffmanNode smallestNode = charChain.remove();
			HuffmanNode nextSmallest = charChain.remove();
			int newFrequency = smallestNode.frequency + nextSmallest.frequency;
			firstRoot = new HuffmanNode(EMPTY_CHAR, newFrequency, smallestNode, nextSmallest);
			charChain.add(firstRoot);
		}
		return firstRoot;
	}
	
	// Constructs and initializes a new HuffmanCode from the given input. The input
	// represents a previously constructed code. Assumes the given Scanner exists 
	// and contains data in the standard format.
	public HuffmanCode(Scanner input) {
		while (input.hasNextLine()) {
			int asciiValue = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			overallRoot = reconstructTree(overallRoot, asciiValue, code);
		}
	}
	
	// Returns a single, built-up HuffmanNode tree given a HuffmanNode, an ASCII 
	// integer value, and a code.
	private HuffmanNode reconstructTree(HuffmanNode root, int asciiValue, String code) {
		if (code.isEmpty()) {
			return new HuffmanNode(asciiValue, 0);
		} else if (root == null) {
			root = new HuffmanNode(EMPTY_CHAR, 0);
		}
		if (code.charAt(0) == '0') {
			root.left = reconstructTree(root.left, asciiValue, code.substring(1));
		} else {
			root.right = reconstructTree(root.right, asciiValue, code.substring(1));
		}
		return root;
	}
	
	// Stores the current huffman codes to the given output in the standard format.
	public void save(PrintStream output) {
		save(overallRoot, output, "");
	}
	
	// Prints the ASCII value for the associated character and its huffman code to
	// the given output stream in the standard format.
	private void save(HuffmanNode root, PrintStream output, String code) {
		if (root != null) {
			if (isLeafNode(root)) {
				output.println(root.character);
				output.println(code);
			} 
			save(root.left, output, code + "0");
			save(root.right, output, code + "1");
		}
	}
	
	// Reads and translates the bits from the given input stream and writes its
	// corresponding characters to the given output. Stops reading when the given
	// BitInputStream is empty. Assumes the given input contains a legal encoding 
	// of characters.
	public void translate(BitInputStream input, PrintStream output) {
		HuffmanNode root = overallRoot;
		while (isLeafNode(root) || input.hasNextBit()) {
			if (isLeafNode(root)) {
				output.write(root.character);
				root = overallRoot;
			} else {
				int currentBit = input.nextBit();
				if (currentBit == 0) {
					root = root.left;
				} else {
					root = root.right;
				}
			}
		}
	}
	
	// Returns true if given node is a leaf node, false if not.
	private boolean isLeafNode(HuffmanNode root) {
		return (root.left == null && root.right == null);
	}
	
	// Represents a "huffman node" object, a single node represents a character 
	// and its frequency.
	private static class HuffmanNode implements Comparable<HuffmanNode> {
		public int character;
		public int frequency;
		public HuffmanNode left;
		public HuffmanNode right;
		
		// Constructs a node with the given ASCII integer for a character, 
		// and given a frequency value.
		public HuffmanNode(int character, int frequency) {
			this(character, frequency, null, null);
		}
		
		// Constructs a node with the given an ASCII value for a character, 
		// a frequency, and links to other HuffmanNodes.
		public HuffmanNode(int character, int frequency, HuffmanNode left, 
				             HuffmanNode right) {
			this.character = character;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}
		
		// Returns a number < 0 if this HuffmanNode comes before the given other
		// Returns a number > 0 if this HuffmanNode comes after the given other
		// Returns 0 if this HuffmanNode and the given other are the same
		// HuffmanNodes are ordered by its frequency (lowest frequency comes first);
		public int compareTo(HuffmanNode other) {
			return this.frequency - other.frequency;
		}
	}
}
