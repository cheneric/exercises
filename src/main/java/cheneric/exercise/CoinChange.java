package cheneric.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Calculates change using coins.</p>
 *
 * <p>To run from the project root:</p>
 * <code>
 *   ./gradlew run -q -PmainClass=cheneric.exercise.CoinChange
 * </code>
 */
public class CoinChange {
	/**
	 * Returns a collection of the smallest number of coins that totals the requested <code>amount</code>.
	 * 
	 * @param amount the desired value of the collection of coins.
	 * @param coinValues the available coin denominations.
	 * @return a collection of the smallest number of coins that totals the requested <code>amount</code>, 
	 * or <code>null</code> if no solution exists.
	 */
	public static List<Integer> minCoins(int amount, int... coinValues) {
		if (amount >= 0) {
			// cache of smallest number of coins that total each subAmount from 0 to the desired amount
			final List<List<Integer>> minCoins = new ArrayList<List<Integer>>(amount + 1);
			// no coins required to total 0
			minCoins.add(new ArrayList<>());
			// initially, no known way to get any of the other subAmounts
			for (int count = 0; count < amount; count++) {
				minCoins.add(null);
			}

			// increment through each subAmount
			for (int subAmount = 0; subAmount <= amount; subAmount++) {
				// for each coinValue
				for (final int coinValue : coinValues) {
					if (coinValue <= subAmount) {
						final int dependentAmount = subAmount - coinValue;
						final List<Integer> dependentMinCoins = minCoins.get(dependentAmount);

						// there is a coin combination totalling this subAmount iff we have 
						// previously found a dependentAmount totalling (subAmount - coinValue)
						if (dependentMinCoins != null) {
							final List<Integer> currentMinCoins = minCoins.get(subAmount);
							// if a previous combination of coins was found totaling this 
							// subAmount but the number of coins in dependentMinCoins plus 
							// this current coin would be greater than the number of coins 
							// in the previously found combination of coins, keep the 
							// previous combination of coins
							if (currentMinCoins != null && dependentMinCoins.size() + 1 >= currentMinCoins.size()) {
								continue;
							}
							// otherwise, replace the previous combination of coins with a 
							// smaller combination consisting of a copy of the collection 
							// totalling (subAmount - coinValue) plus the current coinValue
							final List<Integer> newMinCoins = new ArrayList<>(dependentMinCoins);
							newMinCoins.add(coinValue);
							minCoins.set(subAmount, newMinCoins);
						}
					}
				}
			}

			// return the solution
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
