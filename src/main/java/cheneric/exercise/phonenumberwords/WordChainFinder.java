package cheneric.exercise.phonenumberwords;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Finds all complete and contiguous word chains formed by a 
 * picking one letter per digit from the letters associated with 
 * each digit on a telephone keypad - i.e, a letter for each 
 * digit must be selected, and all letters must be part of a valid 
 * word.
 */
public class WordChainFinder {
	/**
	 * Finds all complete and contiguous word chain combinations 
	 * formed by the letters associated with the input 
	 * <code>phoneNumber</code> on a telephone keypad.
	 * 
	 * @param phoneNumber the phone number to convert to valid 
	 * word chains.
	 * @return all complete and contiguous word chain combinations 
	 * formed by the input <code>phoneNumber</code>.
	 */
	public Set<String> findWordChains(String phoneNumber) {
		// keep solutions in insertion order for readability
		final Set<String> validWordChains = new LinkedHashSet<String>();
		final WordGenerator wordGenerator = new WordGenerator(phoneNumber);
		String word;
		while ((word = wordGenerator.getNextWord()) != null) {
			if (isValidWordChain(word)) {
				validWordChains.add(word);
			}
		}
		return validWordChains;
	}

	/**
	 * Indicates whether the input letters form a complete and 
	 * contiguous chain of valid words.
	 * 
	 * @param word the letter sequence to test.
	 * @return <code>true</code> if the input letters form a 
	 * complete and contiguous chain of valid words. 
	 */
	boolean isValidWordChain(String word) {
		final Deque<WordChainSearch> wordChainSearches = new ArrayDeque<WordChainSearch>();
		wordChainSearches.addFirst(new WordChainSearch(word));
		for (WordChainSearch wordChainSearch = wordChainSearches.pollFirst();
			wordChainSearch != null;
			wordChainSearch = wordChainSearches.pollFirst())
		{
			if (wordChainSearch.isValidAndComplete()) {
				return true;
			}
			else {
				for (final WordChainSearch nextWordChainSearch : wordChainSearch.getNextWordChainSearches()) {
					wordChainSearches.addFirst(nextWordChainSearch);
				}
			}
		}
		return false;
	}

	public static void main(String... args) {
		try {
			final String phoneNumber = args[0];
			try {
				System.out.println("Phone number: " + phoneNumber);
				final WordChainFinder wordChainFinder = new WordChainFinder();
				final Set<String> wordChains = wordChainFinder.findWordChains(phoneNumber);
				if (wordChains.isEmpty()) {
					System.out.println("No word chains in: " + phoneNumber);
				}
				else {
					for (final String wordChain : wordChains) {
						System.out.println(wordChain);
					}
				}
			}
			catch (IllegalArgumentException exception) {
				System.err.println("Invalid phone number: " + phoneNumber);
			}
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			System.err.println("First argument must be a phone number.");
		}
	}

	/**
	 * A portion of a letter sequence to test for validity as a word.
	 */
	static class WordChainSearch {
		final String word;
		final int startIndex;
		final int endIndex;

		Dictionary dictionary = new Dictionary();

		/**
		 * Constructor for initial word chain search.
		 * 
		 * @param word the word to search for a complete chain of words from 
		 * start to end.
		 */
		WordChainSearch(String word) {
			this(word, 0, 0);
		}

		/**
		 * Constructor for intermediate word chain searches.
		 * 
		 * @param word the word to search for a complete chain of words from 
		 * start to end.
		 * @param startIndex the start index to validate as a word.
		 * @param endIndex the end index to validate as a word.
		 */
		WordChainSearch(String word, int startIndex, int endIndex) {
			this.word = word;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		boolean isValidAndComplete() {
			return isValid() && isComplete();
		}

		boolean isValid() {
			return startIndex == endIndex
				|| dictionary.isWord(word.substring(startIndex, endIndex));
		}

		boolean isComplete() {
			return endIndex == word.length();
		}

		Set<WordChainSearch> getNextWordChainSearches() {
			// keep next searches in insertion order for ease of debugging
			final Set<WordChainSearch> nextWordChainSearches = new LinkedHashSet<WordChainSearch>();
			if (isValid() && !isComplete()) {
				final int wordLength = word.length();
				for (int count = endIndex + 1; count <= wordLength; count++) {
					nextWordChainSearches.add(new WordChainSearch(word, endIndex, count));
				}
			}
			return nextWordChainSearches;
		}

		@Override
		public boolean equals(Object object) {
			return object instanceof WordChainSearch && equals((WordChainSearch)object);
		}

		public boolean equals(WordChainSearch wordChainSearch) {
			return Objects.equals(word, wordChainSearch.word)
				&& startIndex == wordChainSearch.startIndex
				&& endIndex == wordChainSearch.endIndex;
		}

		@Override
		public int hashCode() {
			return word.hashCode() 
				^ (startIndex << 7)
				^ (endIndex << 19);
		}

		@Override
		public String toString() {
			return "{word: " + word + ", startIndex: " + startIndex + ", endIndex: " + endIndex + "}";
		}
	}
}
