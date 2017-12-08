package net.bassmann.adventofcode.year2017.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day08Test {

  private final Day today = new Year2017Day08();

  @Test
  void solvePart1() {
    assertEquals("7296", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("8186", today.solvePart2());
  }
}
