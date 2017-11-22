package net.bassmann.adventofcode.year2015.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day14Test {

  private final Day today = new Year2015Day14();

  @Test
  void solvePart1() {
    assertEquals("2655", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1059", today.solvePart2());
  }
}
