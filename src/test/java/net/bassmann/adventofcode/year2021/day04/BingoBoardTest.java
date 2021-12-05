package net.bassmann.adventofcode.year2021.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BingoBoardTest {

  @Test
  void prettyPrintTest() {
    String expected =
        "22 13 17 11  0\n"
            + " 8  2 23  4 24\n"
            + "21  9 14 16  7\n"
            + " 6 10  3 18  5\n"
            + " 1 12 20 15 19";

    BingoBoard board =
        new BingoBoard(
            List.of(
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19"));

    assertEquals(expected, board.toString());
  }

  @Test
  void bingoTest() {
    BingoBoard board =
        new BingoBoard(
            List.of(
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19"));

    board.mark(22);
    assertFalse(board.checkForBingo(board.mark(22)));
    assertFalse(board.checkForBingo(board.mark(11)));
    assertFalse(board.checkForBingo(board.mark(0)));
    assertFalse(board.checkForBingo(board.mark(17)));
    assertTrue(board.checkForBingo(board.mark(13)));
  }

  @Test
  void sumTest() {
    String expected =
        "14* 21* 17* 24*  4*\n"
            + "10  16  15   9* 19 \n"
            + "18   8  23* 26  20 \n"
            + "22  11* 13   6   5*\n"
            + " 2*  0* 12   3   7*";

    BingoBoard b =
        new BingoBoard(
            List.of(
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"));

    int[] draws = new int[] {7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24};

    Arrays.stream(draws).forEach(b::mark);
    assertEquals(expected, b.toString());
    assertEquals(188, b.unmarkedSum());
  }
}
