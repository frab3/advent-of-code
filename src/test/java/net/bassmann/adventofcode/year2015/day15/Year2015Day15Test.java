package net.bassmann.adventofcode.year2015.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day15Test {

  private final Day today = new Year2015Day15();

  @Test
  void solvePart1() {
    assertEquals("222870", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("117936", today.solvePart2());
  }
}
