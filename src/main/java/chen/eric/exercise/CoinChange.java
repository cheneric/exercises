package chen.eric.exercise;

import java.util.ArrayList;
import java.util.List;

public class CoinChange {
	public static List<Integer> minCoins(int amount, int... coinValues) {
		if (amount >= 0) {
			final List<List<Integer>> minCoins = new ArrayList<List<Integer>>();
			minCoins.add(new ArrayList<>());
			for (int amountCount = 0; amountCount <= amount; amountCount++) {
				for (final int coinValue : coinValues) {
					if (coinValue <= amountCount) {
						final int subValue = amountCount - coinValue;
						if (subValue < minCoins.size()) {
							final List<Integer> subMinCoins = minCoins.get(subValue);
							if (subMinCoins != null) {
								if (amountCount < minCoins.size()) {
									final List<Integer> currentMinCoins = minCoins.get(amountCount);
									if (currentMinCoins != null && subMinCoins.size() + 1 >= currentMinCoins.size()) {
										continue;
									}
								}
								final List<Integer> newMinCoins = new ArrayList<>(subMinCoins);
								newMinCoins.add(coinValue);
								minCoins.add(amountCount, newMinCoins);
							}
						}
					}
				}
			}
			return amount < minCoins.size() ? minCoins.get(amount) : null;
		}
		else {
			return null;
		}
	}
}
