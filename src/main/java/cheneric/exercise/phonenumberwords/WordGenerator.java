package cheneric.exercise.phonenumberwords;

/**
 * Generator of all word possibilities for a given phone number.
 */
public class WordGenerator {
	private final Digit[] digits;
	private final int maxWords;
	private int wordCount;

	public WordGenerator(String phoneNumber) {
		digits = parseDigits(phoneNumber);
		maxWords = calculateMaxWords(digits);
	}

	/**
	 * Returns the next word, or <code>null</code> if the last word 
	 * has been returned.
	 * 
	 * @return the next word, or <code>null</code> if the last word 
	 * has been returned.
	 */
	public String getNextWord() {
		String nextWord = null;
		if (wordCount < maxWords) {
			final StringBuilder wordBuilder = new StringBuilder();
			// the periodicity with which previous digits will loop
			int previousDigitsPeriod = 1;
			for (final Digit digit : digits) {
				final char[] letters = digit.getLetters();
				final int numberOfLetters = letters.length;
				final char letter = letters[(wordCount / previousDigitsPeriod) % numberOfLetters];
				wordBuilder.append(letter);
				previousDigitsPeriod *= numberOfLetters;
			}
			nextWord = wordBuilder.toString();
			wordCount++;
		}
		return nextWord;
	}

	/**
	 * Parses the digits of the input <code>phoneNumber</code>.
	 * 
	 * @param phoneNumber the phone number to parse.
	 * @return the {@link Digit}s of the input <code>phoneNumber</code>.
	 */
	Digit[] parseDigits(String phoneNumber) {
		Digit[] digits = null;
		if (phoneNumber != null) {
			final int numberOfDigits = phoneNumber.length();
			digits = new Digit[numberOfDigits];
			for (int count = 0; count < numberOfDigits; count++) {
				final char digitChar = phoneNumber.charAt(count);
				digits[count] = Digit.valueOf(digitChar);
			}
		}
		return digits;
	}

	/**
	 * Calculates the maximum number of possible words for the input digits.
	 * 
	 * @param digits the digits to calculate the maximum number of possible 
	 * words for.
	 * @return the maximum number of possible words.
	 */
	int calculateMaxWords(Digit... digits) {
		int count;
		if (digits == null || digits.length == 0) {
			count = 0;
		}
		else {
			count = 1;
			for (final Digit digit : digits) {
				count *= digit.getNumberOfLetters();
			}
		}
		return count;
	}
}
