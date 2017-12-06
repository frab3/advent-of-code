package net.bassmann.adventofcode.year2017.day06;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day06Test {

  private final Day today = new Year2017Day06();

  @Test
  void solvePart1() {
    assertEquals("11137", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1037", today.solvePart2());
  }

  @Test
  void distributeTest() {
    int[] input = {0, 2, 7, 0};
    int[] expected = {2, 4, 1, 2};
    Year2017Day06.distribute(input);
    assertArrayEquals(expected, input);
  }

  @Test
  void exampleOneTest() {
    Year2017Day06.Solution actual = Year2017Day06.parseInputAndSolve("0 2 7 0");
    assertEquals(5, actual.count);
    assertEquals("2 4 1 2", actual.memBank);
  }

  @Test
  void exampleTwoTest() {
    Year2017Day06.Solution actual = Year2017Day06.parseInputAndSolve("2 4 1 2");
    assertEquals(4, actual.count);
    assertEquals("2 4 1 2", actual.memBank);
  }
}
