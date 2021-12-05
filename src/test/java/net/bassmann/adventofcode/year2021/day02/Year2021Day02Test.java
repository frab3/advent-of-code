package net.bassmann.adventofcode.year2021.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2021Day02Test {

  Day day = new Year2021Day02();

  @Test
  void solvePart1() {
    assertEquals("1670340", day.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1954293920", day.solvePart2());
  }
}
