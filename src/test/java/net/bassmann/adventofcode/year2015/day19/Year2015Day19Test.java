package net.bassmann.adventofcode.year2015.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day19Test {

  private final Day today = new Year2015Day19();

  @Test
  void solvePart1() {
    assertEquals("535", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("212", today.solvePart2());
  }
}
