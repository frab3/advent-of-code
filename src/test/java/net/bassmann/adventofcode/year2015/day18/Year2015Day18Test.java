package net.bassmann.adventofcode.year2015.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day18Test {

  private final Day today = new Year2015Day18();

  @Test
  void solvePart1() {
    assertEquals("1061", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1006", today.solvePart2());
  }
}
