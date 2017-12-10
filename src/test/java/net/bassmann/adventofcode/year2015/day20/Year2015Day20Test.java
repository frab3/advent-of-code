package net.bassmann.adventofcode.year2015.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2015Day20Test {

  private final Day today = new Year2015Day20();

  @Test
  void solvePart1() {
    assertEquals("665280", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("705600", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({
      "1, 1",
      "2, 3",
      "3, 4",
      "4, 7",
      "5, 6",
      "6, 12",
      "7, 8",
      "8, 15",
      "9, 13",
      "665280, 2926080"
  })
  void sigmaTest(int number, int expected) {
    assertEquals(expected, Year2015Day20.sigma(number));
  }

  @ParameterizedTest
  @CsvSource({
      "1, [1]",
      "2, '[1, 2]'",
      "3, '[1, 3]'",
      "4, '[1, 2, 4]'",
      "5, '[1, 5]'",
      "6, '[1, 2, 3, 6]'",
      "7, '[1, 7]'",
      "8, '[1, 2, 4, 8]'",
      "9, '[1, 3, 9]'",
  })
  void divisorsTest(int number, String expected) {
    int[] actual = Year2015Day20.divisors(number).sorted().toArray();
    assertEquals(expected, Arrays.toString(actual));
  }
}
