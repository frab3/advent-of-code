package net.bassmann.adventofcode.year2015.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day16Test {

  private final Day today = new Year2015Day16();

  @Test
  void solvePart1() {
    assertEquals("213", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("323", today.solvePart2());
  }
}
