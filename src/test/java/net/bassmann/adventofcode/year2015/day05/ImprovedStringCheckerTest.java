package net.bassmann.adventofcode.year2015.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ImprovedStringCheckerTest {

  private final ImprovedStringChecker sut = new ImprovedStringChecker();

  @ParameterizedTest
  @CsvFileSource(resources = "/2015/day05-part2.csv")
  void test_isNice(String input, boolean expected) {
    boolean actual = sut.isNice(input);
    assertEquals(expected, actual);
  }

  @Test
  void lookAheadTest() {
    String xyxy = "xyxy";
    String xy = "xy";

    assertEquals(0, xyxy.indexOf(xy));
    assertEquals(2, xyxy.indexOf(xy,1));
    assertEquals(2, xyxy.indexOf(xy,2));
    assertEquals(-1, xyxy.indexOf(xy, 3));
  }
}
