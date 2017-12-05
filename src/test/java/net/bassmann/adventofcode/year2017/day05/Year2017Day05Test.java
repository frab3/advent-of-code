package net.bassmann.adventofcode.year2017.day05;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day05Test {

  private final Day today = new Year2017Day05();

  @Test
  void solvePart1() {
    assertEquals("373160", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("26395586", today.solvePart2());
  }

  @Test
  void countJumpsTest() {
    int[] testInput = {0, 3, 0, 1, -3};
    int[] after = {2, 5, 0, 1, -2};
    int expected = 5;
    int actual = Year2017Day05.countStrangeJumps(testInput);
    assertEquals(expected, actual);
    assertArrayEquals(after, testInput);
  }

  @Test
  void countEventStrangerJumpsTest() {
    int[] testInput = {0, 3, 0, 1, -3};
    int[] after = {2, 3, 2, 3, -1};
    int expected = 10;
    int actual = Year2017Day05.countEventStrangerJumps(testInput);
    assertEquals(expected, actual);
    assertArrayEquals(after, testInput);
  }
}
