package net.bassmann.adventofcode.year2017.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day03Test {

  private final Day today = new Year2017Day03();

  @Test
  void solvePart1() {
    assertEquals("430", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("312453", today.solvePart2());
  }
}
