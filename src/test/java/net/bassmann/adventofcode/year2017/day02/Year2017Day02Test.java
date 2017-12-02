package net.bassmann.adventofcode.year2017.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2017Day02Test {

  private final Day today = new Year2017Day02();

  @Test
  void solvePart1() {
    assertEquals("44216", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("320", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({"'5 1 9 5', 8", "'7 5 3', 4", "'2 4 6 8', 6"})
  void testChecksum(String input, int expected) {
    int[] sortedValues = Year2017Day02.splitToSortedArray(input);
    assertEquals(expected, Year2017Day02.checksum(sortedValues));
  }

  @ParameterizedTest
  @CsvSource({"'5 9 2 8', 4", "'9 4 7 3', 3", "'3 8 6 5', 2"})
  void testDivisible(String input, int expected) {
    int[] sortedValues = Year2017Day02.splitToSortedArray(input);
    assertEquals(expected, Year2017Day02.divisible(sortedValues));
  }
}
