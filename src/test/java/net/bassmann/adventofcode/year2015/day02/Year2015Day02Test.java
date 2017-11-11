package net.bassmann.adventofcode.year2015.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day02Test {

  private final Day today = new Year2015Day02();

  @Test
  void solvePart1() {
    assertEquals("1588178", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("3783758", today.solvePart2());
  }
}