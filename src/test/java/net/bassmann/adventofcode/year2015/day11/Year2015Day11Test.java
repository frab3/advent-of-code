package net.bassmann.adventofcode.year2015.day11;

import static net.bassmann.adventofcode.year2015.day11.Year2015Day11.nextPassword;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day11Test {

  private final Day today = new Year2015Day11();

  @Test
  void solvePart1() {
    assertEquals("cqjxxyzz", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("cqkaabcc", today.solvePart2());
  }

  @Test
  void examples() {
    assertEquals("abcdffaa", nextPassword("abcdefgh"));
    assertEquals("ghjaabcc", nextPassword("ghijklmn"));
  }
}
