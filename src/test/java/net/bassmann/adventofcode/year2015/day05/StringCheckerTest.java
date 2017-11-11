package net.bassmann.adventofcode.year2015.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class StringCheckerTest {

  private final StringChecker sut = new StringChecker();

  @ParameterizedTest
  @CsvFileSource(resources = "/2015/day05-part1.csv")
  void test_isNice(String input, boolean expected) {

    boolean actual = sut.isNice(input);
    assertEquals(expected, actual);
  }
}
