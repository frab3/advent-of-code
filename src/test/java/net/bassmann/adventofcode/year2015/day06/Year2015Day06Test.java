package net.bassmann.adventofcode.year2015.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day06Test {

  private final Day today = new Year2015Day06();

  @Test
  void solvePart1() {
    assertEquals("377891", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("14110788", today.solvePart2());
  }
}