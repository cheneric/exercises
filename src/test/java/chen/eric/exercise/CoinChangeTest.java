package chen.eric.exercise;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chen.eric.exercise.CoinChange.minCoins;

public class CoinChangeTest {
	@Test
	public void testMinCoins() {
		assertCoinsEquals(null, -1);
		assertCoinsEquals(null, -1, 1);
		assertCoinsEquals(null, -1, 1, 2, 3);
		assertCoinsEquals(Arrays.asList(), 0);
		assertCoinsEquals(Arrays.asList(), 0, 2);
		assertCoinsEquals(Arrays.asList(), 0, 1, 2, 3);
		assertCoinsEquals(null, 1, 2, 3, 4);
		assertCoinsEquals(Arrays.asList(1), 1, 1, 2, 3);
		assertCoinsEquals(Arrays.asList(2), 2, 1, 2, 3);
		assertCoinsEquals(Arrays.asList(2, 2, 2), 6, 1, 2);
		assertCoinsEquals(Arrays.asList(5, 6), 11, 9, 6, 5, 1);
		assertCoinsEquals(Arrays.asList(5, 6), 11, 6, 9, 1, 5);
		assertCoinsEquals(Arrays.asList(2, 3, 3, 3, 3, 3, 3), 20, 2, 1, 3);
	}

	private void assertCoinsEquals(List<Integer> expectedCoins, int totalValue, int... coinValues) {
		final List<Integer> minCoins = minCoins(totalValue, coinValues);
		if (minCoins != null) {
			Collections.sort(minCoins);
		}
		assertEquals(expectedCoins, minCoins);
	}
}
