package net.bassmann.adventofcode.year2017.day21;

import static org.junit.jupiter.api.Assertions.*;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day21Test {

  private final Day today = new Year2017Day21();

  @Test
  void solvePart1() {
    assertEquals("186", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("3018423", today.solvePart2());

  }
}
