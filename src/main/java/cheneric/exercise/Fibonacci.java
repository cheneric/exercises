package cheneric.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Generates Fibonacci numbers.</p>
 *
 * <p>To run:</p>
 * <code>
 *   ./gradlew run -q -PmainClass=cheneric.exercise.Fibonacci -Pargs=&lt;n for n-th Fibonacci number>
 * </code>
 */
public class Fibonacci {
	public static int fibConstSpace(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("invalid n (" + n + ")");
		}
		else if (n <= 1) {
			return 1;
		}
		int n0 = 1;
		int n1 = 1;
		for (int i = 2; i <= n; i++) {
			int n2 = n0 + n1;
			n0 = n1;
			n1 = n2;
		}
		return n1;
	}

	private static final List<Integer> fibs = new ArrayList<>();
	static {
		fibs.add(1);
		fibs.add(1);
	}

	public static int fibNSpace(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("invalid n (" + n + ")");
		}
		else if (n < fibs.size()) {
			return fibs.get(n);
		}
		for (int count = fibs.size(); count <= n; count++) {
			fibs.add(fibs.get(count - 2) + fibs.get(count - 1));
		}
		return fibs.get(n);
	}

	public static void main(String... args) {
		int n = 5;
		if (args.length > 0) {
			try {
				n = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException exception) {
				System.err.println("Invalid integer: " + args[0]);
				System.exit(1);
			}
		}
		System.out.println("Input n: " + n);
		System.out.println("Constant space: " + fibConstSpace(n));
		System.out.println("n space: " + fibNSpace(n));
	}
}
