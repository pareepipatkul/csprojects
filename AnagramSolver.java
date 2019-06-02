// Pow Areepipatkul (Pasit)

// Find all possible words from a given dictionary that could be combined to 
// create an anagram with the given word/phrase. Then, these combination of 
// words are printed out.

import java.util.*;

public class AnagramSolver {
	
	private Map<String, LetterInventory> inventories;
	private List<String> dictionary;
	
	// Constructs an anagram solver given a dictionary, processes and stores
	// the dictionary and all of its letter inventories.
	public AnagramSolver(List<String> dictionary) {
		this.dictionary = dictionary;
		inventories = new HashMap<String, LetterInventory>();
		for (int i = 0; i < dictionary.size(); i++) {
			inventories.put(dictionary.get(i), new LetterInventory(dictionary.get(i)));
		}
	}
	
	// Pre: The given max value must be greater than or equal to 0
	// (otherwise throws IllegalArgumentException).
	//
	// Post: Prints all combination of words from the dictionary that are 
	// anagrams of the given text, that include at most the given max amount. 
	// Also prunes the dictionary to make it relevant for the given text. 
	// If given max is 0, can include an unlimited number of words for the 
	// anagram.
	public void print(String text, int max) {
		if (max < 0) {
			throw new IllegalArgumentException();
		}
		LetterInventory textInventory = new LetterInventory(text);
		List<String> prunedDictionary = makeShortDictionary(textInventory);
		print(textInventory, prunedDictionary, new Stack<String>(), max, max);
	}
	
	// Returns a short version of the dictionary including only relevant words
	// for the given inventory of letters.
	private List<String> makeShortDictionary(LetterInventory textInventory) {
		List<String> prunedDictionary = new ArrayList<>();
		for (String word : dictionary) {
			if (textInventory.subtract(inventories.get(word)) != null) {
				prunedDictionary.add(word);
			}
		}
		return prunedDictionary;
	}
	
	// Builds up an answer for all the combination of words from the given 
	// pruned dictionary that are anagrams of the given letter inventory and
	// prints them. If given max is 0, can include an unlimited number of words
	// for the anagram.
	private void print(LetterInventory textInventory, List<String> prunedDictionary,
						    Stack<String> wordList, int wordsLeft, int max) {
		if (textInventory.isEmpty() && (max == 0 || wordsLeft >= 0)) {
			System.out.println(wordList);
		} else {
			for (String word : prunedDictionary) {
				LetterInventory wordInventory = inventories.get(word);
				LetterInventory subtracted = textInventory.subtract(wordInventory);
				if (subtracted != null) {
					wordList.push(word);
					print(subtracted, prunedDictionary, wordList, wordsLeft - 1, max);
					wordList.pop();
				}
			}
		}
	}
}
