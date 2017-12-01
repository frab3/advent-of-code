package net.bassmann.adventofcode.year2017.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class Year2017Day01Test {

  private final Day today = new Year2017Day01();

  @Test
  void solvePart1() {
    assertEquals("1390", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1232", today.solvePart2());
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/2017/day01-part1.csv")
  void testExamplePart1(String input, int expected) {
    assertEquals(expected, Year2017Day01.captcha(input, 1));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/2017/day01-part2.csv")
  void testExamplePart2(String input, int expected) {
    assertEquals(expected, Year2017Day01.captcha(input, input.length()/2));
  }
}
