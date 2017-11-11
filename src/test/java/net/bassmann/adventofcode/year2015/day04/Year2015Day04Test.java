package net.bassmann.adventofcode.year2015.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day04Test {

  private final Day today = new Year2015Day04();

  @Test
  void solvePart1() {
    assertEquals("254575", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1038736", today.solvePart2());
  }
}