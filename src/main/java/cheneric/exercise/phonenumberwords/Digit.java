package cheneric.exercise.phonenumberwords;

import org.apache.commons.lang3.StringUtils;

/**
 * Keypad digit and associated letters.
 */
public enum Digit {
	zero(0, new char[] {}),
	one(1, new char[] {}),
	two(2, new char[] {'a', 'b', 'c'}),
	three(3, new char[] {'d', 'e', 'f'}),
	four(4, new char[] {'g', 'h', 'i'}),
	five(5, new char[] {'j', 'k', 'l'}),
	six(6, new char[] {'m', 'n', 'o'}),
	seven(7, new char[] {'p', 'q', 'r', 's'}),
	eight(8, new char[] {'t', 'u', 'v'}),
	nine(9, new char[] {'w', 'x', 'y', 'z'});

	private final int number;
	private final char[] letters;

	public static  Digit valueOf(char digitChar) {
		Digit digit = null;
		try {
			digit = Digit.values()[digitChar - '0'];
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException("Invalid digit: " + digitChar);
		}
		return digit;
	}

	private Digit(int number, char[] letters) {
		this.number = number;
		this.letters = letters;
	}

	public char[] getLetters() {
		return letters;
	}

	public int getNumberOfLetters() {
		return letters.length;
	}

	@Override
	public String toString() {
		return "{" + number + ": " + StringUtils.join(letters, ',') + "}";
	}
}
