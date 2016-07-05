package cheneric.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Connect4 {

	enum Token {
		black("B"),
		red("R");

		private final String symbol;

		private Token(String symbol) {
			this.symbol = symbol;
		}

		@Override
		public String toString() {
			return symbol;
		}
	}

	public void start() {
		run(new Board(), randomStartPlayer());
	}

	Token randomStartPlayer() {
		return Token.values()[
			new Random()
				.nextInt(
					Token.values().length)];
	}

	Token nextPlayer(Token currentPlayer) {
		return Token.values()[
			(currentPlayer.ordinal() + 1)
				% Token.values().length];
	}

	Token run(Board board, Token startPlayer) {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(board);
		Token currentPlayer = startPlayer;
		while (true) {
			try {
				System.out.print(currentPlayer.name() + " - play column:  ");
				final boolean isWin = board.play(currentPlayer, Integer.parseInt(reader.readLine()));
				System.out.println(board);
				if (isWin) {
					System.out.println(currentPlayer.name() + " wins!");
					return currentPlayer;
				}
				else {
					currentPlayer = nextPlayer(currentPlayer);
				}
			}
			catch (IOException|NumberFormatException|IndexOutOfBoundsException exception) {}
		}
	}

	static class Board {
		static final int NUM_COLUMNS = 7;
		static final int NUM_ROWS = 6;
		private static final int WIN_COUNT = 4;
		static final char EMPTY = '.';
		static final String HSPACE = " ";
		static final String NEWLINE = System.lineSeparator();

		// 7 columns of 6 rows
		private final List<List<Token>> board;

		Board() {
			final List<List<Token>> board = new ArrayList<>();
			for (int count = 0; count < NUM_COLUMNS; count++) {
				board.add(new ArrayList<>(NUM_ROWS));
			}
			this.board = Collections.unmodifiableList(board);
		}

		public boolean play(Token token, int columnIndex) {
			final List<Token> column = board.get(columnIndex);
			final int columnSize = column.size();
			if (columnSize >= NUM_ROWS) {
				throw new IndexOutOfBoundsException("column " + columnIndex + " full (" + columnSize + ")");
			}
			column.add(token);
			return isWin(columnIndex);
		}

		boolean isWin(int tokenColumnIndex) {
			boolean isWin = false;
			try {
				final int tokenRowIndex = board.get(tokenColumnIndex).size() - 1;
				for (final WinCondition winCondition : new WinCondition[] {
					this::isHorizontalWin,
					this::isVerticalWin,
					this::isUpLeftWin,
					this::isUpRightWin
				})
				{
					if (winCondition.isWin(tokenColumnIndex, tokenRowIndex)) {
						return true;
					}
				}
			}
			catch (IndexOutOfBoundsException exception) {}
			return false;
		}

		boolean isHorizontalWin(int tokenColumnIndex, int tokenRowIndex) {
			try {
				final Token matchToken = board.get(tokenColumnIndex).get(tokenRowIndex);
				int matchCount = 1;
				int matchColumnIndex = tokenColumnIndex;

				try {
					// move left
					while (board.get(--matchColumnIndex).get(tokenRowIndex) == matchToken) {
						matchCount++;
						if (matchCount >= WIN_COUNT) {
							return true;
						}
					}
				}
				catch (IndexOutOfBoundsException exception) {}

				// move right
				matchColumnIndex = tokenColumnIndex;
				while (board.get(++matchColumnIndex).get(tokenRowIndex) == matchToken) {
					matchCount++;
					if (matchCount >= WIN_COUNT) {
						return true;
					}
				}
			}
			catch (IndexOutOfBoundsException exception) {}
			return false;
		}

		boolean isVerticalWin(int tokenColumnIndex, int tokenRowIndex) {
			try {
				final List<Token> matchColumn = board.get(tokenColumnIndex);
				final Token matchToken = matchColumn.get(tokenRowIndex);
				int matchCount = 1;
				int matchRowIndex = tokenRowIndex;

				try {
					// move down
					while (matchColumn.get(--matchRowIndex) == matchToken) {
						matchCount++;
						if (matchCount >= WIN_COUNT) {
							return true;
						}
					}
				}
				catch (IndexOutOfBoundsException exception) {}

				// move up
				matchRowIndex = tokenRowIndex;
				while (matchColumn.get(++matchRowIndex) == matchToken) {
					matchCount++;
					if (matchCount >= WIN_COUNT) {
						return true;
					}
				}
			}
			catch (IndexOutOfBoundsException exception) {}
			return false;
		}

		boolean isUpRightWin(int tokenColumnIndex, int tokenRowIndex) {
			try {
				final Token matchToken = board.get(tokenColumnIndex).get(tokenRowIndex);
				int matchCount = 1;
				int matchColumnIndex = tokenColumnIndex;
				int matchRowIndex = tokenRowIndex;

				try {
					// move down left
					while (board.get(--matchColumnIndex).get(--matchRowIndex) == matchToken) {
						matchCount++;
						if (matchCount >= WIN_COUNT) {
							return true;
						}
					}
				}
				catch (IndexOutOfBoundsException exception) {}

				// move up right
				matchColumnIndex = tokenColumnIndex;
				matchRowIndex = tokenRowIndex;
				while (board.get(++matchColumnIndex).get(++matchRowIndex) == matchToken) {
					matchCount++;
					if (matchCount >= WIN_COUNT) {
						return true;
					}
				}
			}
			catch (IndexOutOfBoundsException exception) {}
			return false;
		}

		boolean isUpLeftWin(int tokenColumnIndex, int tokenRowIndex) {
			try {
				final Token matchToken = board.get(tokenColumnIndex).get(tokenRowIndex);
				int matchCount = 1;
				int matchColumnIndex = tokenColumnIndex;
				int matchRowIndex = tokenRowIndex;

				try {
					// move up left
					while (board.get(--matchColumnIndex).get(++matchRowIndex) == matchToken) {
						matchCount++;
						if (matchCount >= WIN_COUNT) {
							return true;
						}
					}
				}
				catch (IndexOutOfBoundsException exception) {}

				// move down right
				matchColumnIndex = tokenColumnIndex;
				matchRowIndex = tokenRowIndex;
				while (board.get(++matchColumnIndex).get(--matchRowIndex) == matchToken) {
					matchCount++;
					if (matchCount >= WIN_COUNT) {
						return true;
					}
				}
			}
			catch (IndexOutOfBoundsException exception) {}
			return false;
		}

		@Override
		public String toString() {
			final StringBuilder boardStringBuilder = new StringBuilder();
			for (int count = 0; count < NUM_COLUMNS; count++) {
				boardStringBuilder.append(count)
					.append(HSPACE);
			}
			boardStringBuilder.append(NEWLINE);
			for (int rowIndex = NUM_ROWS - 1; rowIndex >= 0; rowIndex--) {
				for (final List<Token> column : board) {
					boardStringBuilder.append(column.size() > rowIndex ? column.get(rowIndex) : EMPTY)
						.append(HSPACE);
				}
				boardStringBuilder.append(NEWLINE);
			}
			return boardStringBuilder.toString();
		}

		interface WinCondition {
			boolean isWin(int tokenColumnIndex, int tokenRowIndex);
		}
	}

	public static void main(String... args) {
		new Connect4().start();
	}
}
