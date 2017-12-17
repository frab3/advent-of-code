package net.bassmann.adventofcode.year2016.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2016Day01Test {

  private final Day today = new Year2016Day01();

  @Test
  void solvePart1() {
    assertEquals("239", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("141", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({"'R2, L3', 5", "'R2, R2, R2', 2", "'R5, L5, R5, R3', 12"})
  void examplePartOne(String input, String expected) {
    assertEquals(expected, Year2016Day01.solve(input).getPartOne());
  }

  @Test
  void examplePartTwo() {
    assertEquals("4", Year2016Day01.solve("R8, R4, R4, R8").getPartTwo());
  }
}
