package cheneric.exercise;

import static org.junit.Assert.*;
import org.junit.Test;

public class Base64Test {
	@Test
	public void testDecode() {
		assertNull(Base64.decode(null));
		assertArrayEquals(new byte[0], Base64.decode(""));
		assertArrayEquals("A".getBytes(), Base64.decode("QQ=="));
		assertArrayEquals("AB".getBytes(), Base64.decode("QUI="));
		assertArrayEquals("ABc".getBytes(), Base64.decode("QUJj"));
		assertArrayEquals("ABc0".getBytes(), Base64.decode("QUJjMA=="));
		assertArrayEquals("ABc0 ".getBytes(), Base64.decode("QUJjMCA="));
		assertArrayEquals("ABc0 =".getBytes(), Base64.decode("QUJjMCA9"));
		assertArrayEquals("ABc0 =/".getBytes(), Base64.decode("QUJjMCA9Lw=="));
		assertArrayEquals("ABc0 =/+".getBytes(), Base64.decode("QUJjMCA9Lys="));
		assertArrayEquals(new byte[] {(byte)0xA1}, Base64.decode("oQ=="));
		assertArrayEquals(new byte[] {(byte)0xEE, (byte)0xA1}, Base64.decode("7qE="));
		assertArrayEquals(new byte[] {(byte)0xFF, (byte)0xEE, (byte)0xA1}, Base64.decode("/+6h"));
		assertArrayEquals(new byte[] {(byte)0xD0, (byte)0xFF, (byte)0xEE, (byte)0xA1}, Base64.decode("0P/uoQ=="));
		try {
			Base64.decode("=");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("==");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("1");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("12");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("123");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("12345");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("123456");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("1234567");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("123456=");
			fail("Encoded string length not a multiple of 4");
		}
		catch (IllegalArgumentException exception) {}
		try {
			Base64.decode("123456=8");
			fail("Null character not at end of encoded string");
		}
		catch (IllegalArgumentException exception) {}
	}

	@Test
	public void testEncode() {
		assertNull(Base64.encode(null));
		assertEquals("", Base64.encode(new byte[0]));
		assertEquals("QQ==", Base64.encode("A".getBytes()));
		assertEquals("QUI=", Base64.encode("AB".getBytes()));
		assertEquals("QUJj", Base64.encode("ABc".getBytes()));
		assertEquals("QUJjMA==", Base64.encode("ABc0".getBytes()));
		assertEquals("QUJjMCA=", Base64.encode("ABc0 ".getBytes()));
		assertEquals("QUJjMCA9", Base64.encode("ABc0 =".getBytes()));
		assertEquals("QUJjMCA9Lw==", Base64.encode("ABc0 =/".getBytes()));
		assertEquals("QUJjMCA9Lys=", Base64.encode("ABc0 =/+".getBytes()));
		assertEquals("oQ==", Base64.encode(new byte[] {(byte)0xA1}));
		assertEquals("7qE=", Base64.encode(new byte[] {(byte)0xEE, (byte)0xA1}));
		assertEquals("/+6h", Base64.encode(new byte[] {(byte)0xFF, (byte)0xEE, (byte)0xA1}));
		assertEquals("0P/uoQ==", Base64.encode(new byte[] {(byte)0xD0, (byte)0xFF, (byte)0xEE, (byte)0xA1}));
	}
}
