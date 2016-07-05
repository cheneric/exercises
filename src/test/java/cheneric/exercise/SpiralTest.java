package cheneric.exercise;

import static org.junit.Assert.*;
import static cheneric.exercise.Spiral.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class SpiralTest {
	private final String NEWLINE = System.lineSeparator();

//	*********
//	*.......*
//	*.*****.*
//	*.*...*.*
//	*.*.*.*.*
//	*.*.***.*
//	*.*.....*
//	*.*******
//	*........
	private final String EXPECTED9 =
		"" + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + NEWLINE
			+ FILLED + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + FILLED + NEWLINE
			+ FILLED + EMPTY + FILLED + FILLED + FILLED + FILLED + FILLED + EMPTY + FILLED + NEWLINE
			+ FILLED + EMPTY + FILLED + EMPTY + EMPTY + EMPTY + FILLED + EMPTY + FILLED + NEWLINE
			+ FILLED + EMPTY + FILLED + EMPTY + FILLED + EMPTY + FILLED + EMPTY + FILLED + NEWLINE
			+ FILLED + EMPTY + FILLED + EMPTY + FILLED + FILLED +FILLED + EMPTY + FILLED + NEWLINE
			+ FILLED + EMPTY + FILLED + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + FILLED + NEWLINE
			+ FILLED + EMPTY + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + NEWLINE
			+ FILLED + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + NEWLINE;

//	..........
//	.*********
//	.*.......*
//	.*.*****.*
//	.*.*...*.*
//	.*.*.*.*.*
//	.*.*.***.*
//	.*.*.....*
//	.*.*******
//	.*........
	private final String EXPECTED10 =
		"" + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + NEWLINE
			+ EMPTY + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + FILLED + FILLED + FILLED + FILLED + FILLED + EMPTY + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + FILLED + EMPTY + EMPTY + EMPTY + FILLED + EMPTY + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + FILLED + EMPTY + FILLED + EMPTY + FILLED + EMPTY + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + FILLED + EMPTY + FILLED + FILLED +FILLED + EMPTY + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + FILLED + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + FILLED + NEWLINE
			+ EMPTY + FILLED + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + EMPTY + NEWLINE;

	@Test
	public void testDrawBuffer() {
		testDraw(Spiral::drawBuffer);
	}

	@Test
	public void testDrawDirect() {
		testDraw(Spiral::drawDirect);
	}

	private void testDraw(DrawFunction drawFunction) {
		assertEquals(EXPECTED9, drawToString(drawFunction, 9));
		assertEquals(EXPECTED10, drawToString(drawFunction, 10));
	}

	private String drawToString(DrawFunction drawFunction, int dimen) {
		final ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream((dimen + 1) * dimen);
		final PrintStream printStream = new PrintStream(byteArrayOutputStream);
		try {
			drawFunction.draw(dimen, printStream);
		}
		finally {
			printStream.close();
		}
		return byteArrayOutputStream.toString();
	}

	private interface DrawFunction {
		void draw(int dimen, PrintStream out);
	}
}
