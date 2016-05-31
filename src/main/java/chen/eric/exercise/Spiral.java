package chen.eric.exercise;

import java.io.PrintStream;

public class Spiral {
	static final char EMPTY = '.';
	static final char FILLED = '*';

	public static void drawBuffer(int dimen) {
		drawBuffer(dimen, System.out);
	}

	static void drawBuffer(int dimen, PrintStream out) {
		final int DOWN = 0;
		final int RIGHT = 1;
		final int UP = 2;
		final int LEFT = 3;
		final int NUMBER_OF_DIRECTIONS = 4;
		final boolean[][] grid = new boolean[dimen][dimen];

		int x,y;
		x = y = dimen / 2;
		int stepSize = 1;
		int direction = DOWN;

		try {
			grid[y][x] = true;
			while (true) {
				for (int stepCount = 0; stepCount < stepSize; stepCount++) {
					switch (direction) {
						case DOWN:
							y += 1;
							break;
						case RIGHT:
							x += 1;
							break;
						case UP:
							y -= 1;
							break;
						case LEFT:
							x -= 1;
							break;
					}
					grid[y][x] = true;
				}
				stepSize += 1;
				direction = (direction + 1) % NUMBER_OF_DIRECTIONS;
			}
		}
		catch (ArrayIndexOutOfBoundsException exception) {}

		for (int j = 0; j < dimen; j++) {
			for (int i =0; i < dimen; i++) {
				out.print(grid[j][i] ? FILLED : EMPTY);
			}
			out.println();
		}
	}

	public static void drawDirect(int dimen) {
		drawDirect(dimen, System.out);
	}

	static void drawDirect(int dimen, PrintStream out) {
		final int center = dimen / 2;
		for (int y = 0; y < dimen; y++) {
			for (int x = 0; x < dimen; x++) {
				char cell = EMPTY;
				final boolean isRight = x > center;
				final boolean isBelow = y > center;
				final int dx = Math.abs(x - center);
				final int dy = Math.abs(y - center);
				// vertical line
				if (dx % 2 == 0) {
					// right of center
					if (isRight) {
						if (isBelow) {
							if (dy < dx) {
								cell = FILLED;
							}
						}
						else {
							if (dy <= dx) {
								cell = FILLED;
							}
						}
					}
					// left of center
					else {
						if (dy <= dx) {
							cell = FILLED;
						}
					}
				}

				// horizontal line
				// below center, odd
				if (dy % 2 == 1) {
					if (isBelow) {
						if (isRight) {
							if (dx <= dy + 1) {
								cell = FILLED;
							}
						}
						else {
							if (dx <= dy - 1) {
								cell = FILLED;
							}
						}
					}
				}
				// above center, even
				else {
					if (!isBelow) {
						if (dx <= dy) {
							cell = FILLED;
						}
					}
				}
				out.print(cell);
			}
			out.println();
		}
	}

	public static void main(String... args) {
		drawBuffer(10);
		System.out.print("\n");
		drawDirect(10);
	}
}
