package cheneric.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Base-64 encoder/decoder.</p>
 *
 * <p>To run:</p>
 * <code>
 *   ./gradlew run -q -PmainClass=cheneric.exercise.Base64 -Pargs=&lt;test string>
 * </code>
 */
public class Base64 {
	private static final char NULL_CODE = '=';
	private static final char[] ENCODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	private static final Map<Character,Byte> DECODE = new HashMap<>();
	static {
		final int NUMBER_OF_CODES = ENCODE.length;
		for (int count = 0; count < NUMBER_OF_CODES; count++) {
			DECODE.put(ENCODE[count], (byte)count);
		}
	}

	public static byte[] decode(String inString) {
		byte[] outBytes = null;
		if (inString != null) {
			final int inStringLength = inString.length();
			if (inStringLength % 4 != 0) {
				throw new IllegalArgumentException("Invalid input string length " + inStringLength + " (" + inString + ")");
			}
			final int nullCodeFirstIndex = inString.indexOf(NULL_CODE);
			final int inStringTruncatedLength = nullCodeFirstIndex < 0 ? inStringLength : nullCodeFirstIndex;
			if (inStringLength - inStringTruncatedLength > 2
				|| (inStringLength - inStringTruncatedLength == 2
					&& !inString.endsWith(Character.toString(NULL_CODE))))
			{
				throw new IllegalArgumentException("Invalid character '" + NULL_CODE + "' at index " + nullCodeFirstIndex + " (" + inString + ")");
			}
			outBytes = new byte[(inStringTruncatedLength) * 3 / 4];
			final char[] inChars = inString.toCharArray();
			for (int inCount = 0, outCount = 0; inCount < inStringTruncatedLength; ) {
				final Byte outCode1 = DECODE.get(inChars[inCount++]);
				if (outCode1 == null) {
					throw new IllegalArgumentException("Invalid character '" + inChars[inCount - 1] + "' at index " + (inCount - 1) + " (" + inString + ")");
				}
				final Byte outCode2 = DECODE.get(inChars[inCount++]);
				if (outCode2 == null) {
					throw new IllegalArgumentException("Invalid character '" + inChars[inCount - 1] + "' at index " + (inCount - 1) + " (" + inString + ")");
				}
 				outBytes[outCount++] = (byte)(outCode1 << 2 | outCode2 >> 4);
				if (inCount < inStringTruncatedLength) {
					final Byte outCode3 = DECODE.get(inChars[inCount++]);
					if (outCode3 == null) {
						throw new IllegalArgumentException("Invalid character '" + inChars[inCount - 1] + "' at index " + (inCount - 1) + " (" + inString + ")");
					}
					outBytes[outCount++] = (byte)(outCode2 << 4 | outCode3 >> 2);
					if (inCount < inStringTruncatedLength) {
						final Byte outCode4 = DECODE.get(inChars[inCount++]);
						if (outCode4 == null) {
							throw new IllegalArgumentException("Invalid character '" + inChars[inCount - 1] + "' at index " + (inCount - 1) + " (" + inString + ")");
						}
						outBytes[outCount++] = (byte)(outCode3 << 6 | outCode4);
					}
				}
			}
		}
		return outBytes;
	}

	public static String encode(byte[] inBytes) {
		String outString = null;
		if (inBytes != null) {
			final int numberOfBytes = inBytes.length;
			final char[] outChars = new char[((numberOfBytes / 3) + (numberOfBytes % 3 == 0 ? 0 : 1)) * 4];
			for (int inIndex = 0, outIndex = 0; inIndex < numberOfBytes; inIndex += 3) {
				final byte byte1 = inBytes[inIndex];
				final boolean haveByte2 = inIndex + 1 < numberOfBytes;
				final byte byte2 = haveByte2 ? inBytes[inIndex + 1] : 0x00;
				outChars[outIndex++] = ENCODE[(byte1 & 0xFF) >> 2];
				outChars[outIndex++] = ENCODE[((byte1 & 0x03) << 4) | ((byte2 & 0xFF) >> 4)];
				if (haveByte2) {
					final boolean haveByte3 = inIndex + 2 < numberOfBytes;
					final byte byte3 = haveByte3 ? inBytes[inIndex + 2] : 0x00;
					outChars[outIndex++] = ENCODE[((byte2 & 0x0F) << 2) | ((byte3 & 0xFF) >> 6)];
					outChars[outIndex++] = haveByte3 ? ENCODE[byte3 & 0x3F] : NULL_CODE;
				}
				else {
					outChars[outIndex++] = NULL_CODE;
					outChars[outIndex++] = NULL_CODE;
				}
			}
			outString = new String(outChars);
		}
		return outString;
	}

	public static void main(String... args) {
		final String testString = (args.length > 0 ? args[0] : "test string");
		System.out.println("Test string: " + testString);
		final String encodedBase64 = encode(testString.getBytes());
		System.out.println("Base-64 encoded: " + encodedBase64);
		final byte[] decodedBase64 = decode(encodedBase64);
		System.out.println("Base-64 decoded: " + new String(decodedBase64));
	}
}
