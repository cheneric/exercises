package chen.eric.exercise;

import static org.junit.Assert.*;
import static chen.eric.exercise.Connect4.Board.*;

import org.junit.Before;
import org.junit.Test;

import chen.eric.exercise.Connect4.Board;
import chen.eric.exercise.Connect4.Token;

public class Connect4Test {
	private Board board;
	private Connect4 connect4;

	@Before
	public void setUp() {
		board = new Board();
		connect4 = new Connect4();
	}

	@Test
	public void testBoardToString() {
		final StringBuilder emptyRowStringBuilder = new StringBuilder();
		for (int count = 0; count < NUM_COLUMNS; count++) {
			emptyRowStringBuilder.append(EMPTY)
				.append(HSPACE);
		}
		emptyRowStringBuilder.append(NEWLINE);
		final String emptyRow = emptyRowStringBuilder.toString();

		final StringBuilder boardStringBuilder = new StringBuilder();
		for (int count = 0; count < NUM_COLUMNS; count++) {
			boardStringBuilder.append(count)
				.append(HSPACE);
		}
		boardStringBuilder.append(NEWLINE);
		for (int count = 0; count < NUM_ROWS; count++) {
			boardStringBuilder.append(emptyRow);
		}

		assertEquals(boardStringBuilder.toString(), board.toString());
		assertMoveString(boardStringBuilder, board, Token.red, 3, 6);
		assertMoveString(boardStringBuilder, board, Token.black, 3, 5);
		assertMoveString(boardStringBuilder, board, Token.red, 0, 6);
		assertMoveString(boardStringBuilder, board, Token.black, 6, 6);
		assertMoveString(boardStringBuilder, board, Token.red, 6, 5);
		assertMoveString(boardStringBuilder, board, Token.black, 6, 4);
		assertMoveString(boardStringBuilder, board, Token.red, 0, 5);
	}

	private void assertMoveString(StringBuilder boardStringBuilder, Board board, Token token, int columnIndex, int rowIndex) {
		board.play(token, columnIndex);
		assertEquals(updateBoardString(boardStringBuilder, token, columnIndex, rowIndex), board.toString());
	}

	private String updateBoardString(StringBuilder boardStringBuilder, Token token, int columnIndex, int rowIndex) {
		final int ROW_LENGTH = 15;
		final int COLUMN_WIDTH = 2;
		final int replaceStartIndex = (rowIndex * ROW_LENGTH) + (columnIndex * COLUMN_WIDTH);
		return boardStringBuilder.replace(replaceStartIndex, replaceStartIndex + 1, token.toString()).toString();
	}

	@Test
	public void testConnect4NextPlayer() {
		assertEquals(Token.black, connect4.nextPlayer(Token.red));
		assertEquals(Token.red, connect4.nextPlayer(Token.black));
	}

	@Test
	public void testTokenToString() {
		assertEquals("B", Token.black.toString());
		assertEquals("R", Token.red.toString());
	}
}
