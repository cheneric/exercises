package chen.eric.exercise;

import fj.F;

import static org.junit.Assert.*;
import org.junit.Test;

public class FibonacciTest {
	@Test
	public void testFibConstSpace() {
		testFib(Fibonacci::fibConstSpace);
	}

	@Test
	public void testFibNSpace() {
		testFib(Fibonacci::fibNSpace);
	}

	private void testFib(F<Integer,Integer> fib) {
		try {
			fib.f(-1);
			fail("fib(-1) failed to throw IllegalArgumentException");
		}
		catch (IllegalArgumentException exception) {
			// expected
		}
		assertEquals(1, (int)fib.f(0));
		assertEquals(1, (int)fib.f(1));
		assertEquals(2, (int)fib.f(2));
		assertEquals(3, (int)fib.f(3));
		assertEquals(5, (int)fib.f(4));
		assertEquals(8, (int)fib.f(5));
		assertEquals(13, (int)fib.f(6));
		assertEquals(21, (int)fib.f(7));
		assertEquals(34, (int)fib.f(8));
		assertEquals(55, (int)fib.f(9));
		assertEquals(89, (int)fib.f(10));
	}
}
