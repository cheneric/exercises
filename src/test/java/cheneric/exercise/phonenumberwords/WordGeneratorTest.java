package cheneric.exercise.phonenumberwords;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link WordGenerator} unit tests.
 */
public class WordGeneratorTest {
	private WordGenerator testWordGenerator;

	@Before
	public void setUp() {
		testWordGenerator = new WordGenerator(null);
	}

	/**
	 * Tests {@link WordGenerator#getNextWord()}.
	 */
	@Test
	public void testGetNextWord() {
		WordGenerator wordGenerator = new WordGenerator("1");
		assertNull(wordGenerator.getNextWord());

		wordGenerator = new WordGenerator("2");
		assertEquals("a", wordGenerator.getNextWord());
		assertEquals("b", wordGenerator.getNextWord());
		assertEquals("c", wordGenerator.getNextWord());
		assertNull(wordGenerator.getNextWord());

		wordGenerator = new WordGenerator("7");
		assertEquals("p", wordGenerator.getNextWord());
		assertEquals("q", wordGenerator.getNextWord());
		assertEquals("r", wordGenerator.getNextWord());
		assertEquals("s", wordGenerator.getNextWord());
		assertNull(wordGenerator.getNextWord());

		wordGenerator = new WordGenerator("27");
		assertEquals("ap", wordGenerator.getNextWord());
		assertEquals("bp", wordGenerator.getNextWord());
		assertEquals("cp", wordGenerator.getNextWord());
		assertEquals("aq", wordGenerator.getNextWord());
		assertEquals("bq", wordGenerator.getNextWord());
		assertEquals("cq", wordGenerator.getNextWord());
		assertEquals("ar", wordGenerator.getNextWord());
		assertEquals("br", wordGenerator.getNextWord());
		assertEquals("cr", wordGenerator.getNextWord());
		assertEquals("as", wordGenerator.getNextWord());
		assertEquals("bs", wordGenerator.getNextWord());
		assertEquals("cs", wordGenerator.getNextWord());
		assertNull(wordGenerator.getNextWord());

		// "1" has no associated letters
		wordGenerator = new WordGenerator("271");
		assertNull(wordGenerator.getNextWord());

		wordGenerator = new WordGenerator("275");
		assertEquals("apj", wordGenerator.getNextWord());
		assertEquals("bpj", wordGenerator.getNextWord());
		assertEquals("cpj", wordGenerator.getNextWord());
		assertEquals("aqj", wordGenerator.getNextWord());
		assertEquals("bqj", wordGenerator.getNextWord());
		assertEquals("cqj", wordGenerator.getNextWord());
		assertEquals("arj", wordGenerator.getNextWord());
		assertEquals("brj", wordGenerator.getNextWord());
		assertEquals("crj", wordGenerator.getNextWord());
		assertEquals("asj", wordGenerator.getNextWord());
		assertEquals("bsj", wordGenerator.getNextWord());
		assertEquals("csj", wordGenerator.getNextWord());
		assertEquals("apk", wordGenerator.getNextWord());
		assertEquals("bpk", wordGenerator.getNextWord());
		assertEquals("cpk", wordGenerator.getNextWord());
		assertEquals("aqk", wordGenerator.getNextWord());
		assertEquals("bqk", wordGenerator.getNextWord());
		assertEquals("cqk", wordGenerator.getNextWord());
		assertEquals("ark", wordGenerator.getNextWord());
		assertEquals("brk", wordGenerator.getNextWord());
		assertEquals("crk", wordGenerator.getNextWord());
		assertEquals("ask", wordGenerator.getNextWord());
		assertEquals("bsk", wordGenerator.getNextWord());
		assertEquals("csk", wordGenerator.getNextWord());
		assertEquals("apl", wordGenerator.getNextWord());
		assertEquals("bpl", wordGenerator.getNextWord());
		assertEquals("cpl", wordGenerator.getNextWord());
		assertEquals("aql", wordGenerator.getNextWord());
		assertEquals("bql", wordGenerator.getNextWord());
		assertEquals("cql", wordGenerator.getNextWord());
		assertEquals("arl", wordGenerator.getNextWord());
		assertEquals("brl", wordGenerator.getNextWord());
		assertEquals("crl", wordGenerator.getNextWord());
		assertEquals("asl", wordGenerator.getNextWord());
		assertEquals("bsl", wordGenerator.getNextWord());
		assertEquals("csl", wordGenerator.getNextWord());
		assertNull(wordGenerator.getNextWord());

	}

	/**
	 * Tests {@link WordGenerator#parseDigits(String)}.
	 */
	@Test
	public void testParseDigits() {
		assertArrayEquals(new Digit[] {Digit.zero}, testWordGenerator.parseDigits("0"));
		assertArrayEquals(new Digit[] {Digit.one}, testWordGenerator.parseDigits("1"));
		assertArrayEquals(new Digit[] {Digit.two}, testWordGenerator.parseDigits("2"));
		assertArrayEquals(new Digit[] {Digit.three}, testWordGenerator.parseDigits("3"));
		assertArrayEquals(new Digit[] {Digit.four}, testWordGenerator.parseDigits("4"));
		assertArrayEquals(new Digit[] {Digit.five}, testWordGenerator.parseDigits("5"));
		assertArrayEquals(new Digit[] {Digit.six}, testWordGenerator.parseDigits("6"));
		assertArrayEquals(new Digit[] {Digit.seven}, testWordGenerator.parseDigits("7"));
		assertArrayEquals(new Digit[] {Digit.eight}, testWordGenerator.parseDigits("8"));
		assertArrayEquals(new Digit[] {Digit.nine}, testWordGenerator.parseDigits("9"));
		assertArrayEquals(
			new Digit[] {
				Digit.four,
				Digit.one,
				Digit.five,
				Digit.two,
				Digit.three,
				Digit.four,
				Digit.five,
				Digit.six,
				Digit.seven,
				Digit.eight,
			}, 
			testWordGenerator.parseDigits("4152345678"));
	}

	/**
	 * Tests {@link WordChainFinder#calculateMaxWords(String)}.
	 */
	@Test
	public void testCalculateMaxWords() {
		assertEquals(0, testWordGenerator.calculateMaxWords((Digit[])null));
		assertEquals(0, testWordGenerator.calculateMaxWords(new Digit[0]));
		assertEquals(0, testWordGenerator.calculateMaxWords(Digit.zero));
		assertEquals(0, testWordGenerator.calculateMaxWords(Digit.one));
		assertEquals(3, testWordGenerator.calculateMaxWords(Digit.two));
		assertEquals(4, testWordGenerator.calculateMaxWords(Digit.seven));
		assertEquals(3, testWordGenerator.calculateMaxWords(Digit.eight));
		assertEquals(4, testWordGenerator.calculateMaxWords(Digit.nine));
		assertEquals(0, 
			testWordGenerator.calculateMaxWords(
				Digit.one,
				Digit.two,
				Digit.three,
				Digit.four,
				Digit.five,
				Digit.six,
				Digit.seven));
		assertEquals(3*3*3*3*3*4*3, 
			testWordGenerator.calculateMaxWords(
				Digit.two,
				Digit.three,
				Digit.four,
				Digit.five,
				Digit.six,
				Digit.seven,
				Digit.eight));
	}
}
