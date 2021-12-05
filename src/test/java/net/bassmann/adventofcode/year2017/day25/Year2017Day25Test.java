package net.bassmann.adventofcode.year2017.day25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day25Test {

  private final Day today = new Year2017Day25();

  @Test
  void solvePart1() {
    assertEquals("2526", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("Merry Christmas", today.solvePart2());
  }
}
