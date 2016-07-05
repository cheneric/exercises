package cheneric.exercise.phonenumberwords;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * {@link WordChainFinder} unit tests.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(WordChainFinder.WordChainSearch.class)
public class WordChainFinderTest {
	@Mock
	Dictionary mockDictionary;

	WordChainFinder testWordChainFinder;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		PowerMockito.whenNew(Dictionary.class).withNoArguments().thenReturn(mockDictionary);
		testWordChainFinder = new WordChainFinder();
	}

	/**
	 * Tests {@link WordChainFinder#findWordChains(String)} for the 
	 * number "34".
	 */
	@Test
	public void testFindWordChains34() {
		when(mockDictionary.isWord(eq("dg"))).thenReturn(true);
		when(mockDictionary.isWord(eq("f"))).thenReturn(true);
		when(mockDictionary.isWord(eq("i"))).thenReturn(true);
		assertEquals(
			new HashSet<String>(Arrays.asList("dg", "fi")), 
			testWordChainFinder.findWordChains("34"));
	}

	/**
	 * Tests {@link WordChainFinder#findWordChains(String)} for the 
	 * number "349".
	 */
	@Test
	public void testFindWordChains349() {
		when(mockDictionary.isWord(eq("dg"))).thenReturn(true);
		when(mockDictionary.isWord(eq("f"))).thenReturn(true);
		when(mockDictionary.isWord(eq("fi"))).thenReturn(true);
		when(mockDictionary.isWord(eq("g"))).thenReturn(true);
		when(mockDictionary.isWord(eq("h"))).thenReturn(true);
		when(mockDictionary.isWord(eq("ix"))).thenReturn(true);
		when(mockDictionary.isWord(eq("iy"))).thenReturn(true);
		when(mockDictionary.isWord(eq("t"))).thenReturn(true);
		when(mockDictionary.isWord(eq("w"))).thenReturn(true);
		when(mockDictionary.isWord(eq("z"))).thenReturn(true);
		assertEquals(new HashSet<String>(Arrays.asList(
			"dgw",
			"dgz",
			"fgw",
			"fgz",
			"fhw",
			"fhz",
			"fiw",
			"fix",
			"fiy",
			"fiz")), 
			testWordChainFinder.findWordChains("349"));
	}

}