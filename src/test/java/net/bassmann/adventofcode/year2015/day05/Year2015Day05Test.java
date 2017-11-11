package net.bassmann.adventofcode.year2015.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day05Test {

  private final Day today = new Year2015Day05();

  @Test
  void solvePart1() {
    assertEquals("255", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("55", today.solvePart2());
  }
}
