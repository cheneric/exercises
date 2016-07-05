package cheneric.exercise;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Connect4Test {
	private Connect4.Board board;
	private Connect4 connect4;

	@Before
	public void setUp() {
		board = new Connect4.Board();
		connect4 = new Connect4();
	}

	@Test
	public void testBoardToString() {
		final StringBuilder emptyRowStringBuilder = new StringBuilder();
		for (int count = 0; count < Connect4.Board.NUM_COLUMNS; count++) {
			emptyRowStringBuilder.append(Connect4.Board.EMPTY)
				.append(Connect4.Board.HSPACE);
		}
		emptyRowStringBuilder.append(Connect4.Board.NEWLINE);
		final String emptyRow = emptyRowStringBuilder.toString();

		final StringBuilder boardStringBuilder = new StringBuilder();
		for (int count = 0; count < Connect4.Board.NUM_COLUMNS; count++) {
			boardStringBuilder.append(count)
				.append(Connect4.Board.HSPACE);
		}
		boardStringBuilder.append(Connect4.Board.NEWLINE);
		for (int count = 0; count < Connect4.Board.NUM_ROWS; count++) {
			boardStringBuilder.append(emptyRow);
		}

		assertEquals(boardStringBuilder.toString(), board.toString());
		assertMoveString(boardStringBuilder, board, Connect4.Token.red, 3, 6);
		assertMoveString(boardStringBuilder, board, Connect4.Token.black, 3, 5);
		assertMoveString(boardStringBuilder, board, Connect4.Token.red, 0, 6);
		assertMoveString(boardStringBuilder, board, Connect4.Token.black, 6, 6);
		assertMoveString(boardStringBuilder, board, Connect4.Token.red, 6, 5);
		assertMoveString(boardStringBuilder, board, Connect4.Token.black, 6, 4);
		assertMoveString(boardStringBuilder, board, Connect4.Token.red, 0, 5);
	}

	private void assertMoveString(StringBuilder boardStringBuilder, Connect4.Board board, Connect4.Token token, int columnIndex, int rowIndex) {
		board.play(token, columnIndex);
		assertEquals(updateBoardString(boardStringBuilder, token, columnIndex, rowIndex), board.toString());
	}

	private String updateBoardString(StringBuilder boardStringBuilder, Connect4.Token token, int columnIndex, int rowIndex) {
		final int ROW_LENGTH = 15;
		final int COLUMN_WIDTH = 2;
		final int replaceStartIndex = (rowIndex * ROW_LENGTH) + (columnIndex * COLUMN_WIDTH);
		return boardStringBuilder.replace(replaceStartIndex, replaceStartIndex + 1, token.toString()).toString();
	}

	@Test
	public void testConnect4NextPlayer() {
		Assert.assertEquals(Connect4.Token.black, connect4.nextPlayer(Connect4.Token.red));
		Assert.assertEquals(Connect4.Token.red, connect4.nextPlayer(Connect4.Token.black));
	}

	@Test
	public void testTokenToString() {
		Assert.assertEquals("B", Connect4.Token.black.toString());
		Assert.assertEquals("R", Connect4.Token.red.toString());
	}
}
