package cheneric.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Calculates change using coins.</p>
 *
 * <p>To run:</p>
 * <code>
 *   ./gradlew run -q -PmainClass=cheneric.exercise.CoinChange
 * </code>
 */
public class CoinChange {
	public static List<Integer> minCoins(int amount, int... coinValues) {
		if (amount >= 0) {
			final List<List<Integer>> minCoins = new ArrayList<List<Integer>>(amount + 1);
			minCoins.add(new ArrayList<>());
			// initialize
			for (int count = 0; count < amount; count++) {
				minCoins.add(null);
			}
			for (int amountCount = 0; amountCount <= amount; amountCount++) {
				for (final int coinValue : coinValues) {
					if (coinValue <= amountCount) {
						final int subValue = amountCount - coinValue;
						final List<Integer> subMinCoins = minCoins.get(subValue);
						if (subMinCoins != null) {
							final List<Integer> currentMinCoins = minCoins.get(amountCount);
							if (currentMinCoins != null && subMinCoins.size() + 1 >= currentMinCoins.size()) {
								continue;
							}
							final List<Integer> newMinCoins = new ArrayList<>(subMinCoins);
							newMinCoins.add(coinValue);
							minCoins.set(amountCount, newMinCoins);
						}
					}
				}
			}
			return minCoins.get(amount);
		}
		else {
			return null;
		}
	}

	public static void main(String... args) {
		System.out.println(minCoins(25, 4, 3));
	}
}
