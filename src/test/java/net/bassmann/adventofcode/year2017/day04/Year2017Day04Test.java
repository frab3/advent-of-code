package net.bassmann.adventofcode.year2017.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2017Day04Test {

  private final Day today = new Year2017Day04();

  @Test
  void solvePart1() {
    assertEquals("466", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("251", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({"aa bb cc dd ee, true", "aa bb cc dd aa, false", "aa bb cc dd aaa, true"})
  void isValidTest(String input, boolean expected) {
    assertEquals(expected, Year2017Day04.isValid(input));
  }

  @ParameterizedTest
  @CsvSource({
      "abcde fghij, true",
      "abcde xyz ecdab, false",
      "a ab abc abd abf abj, true",
      "iiii oiii ooii oooi oooo, true",
      "oiii ioii iioi iiio, false"
  })
  void isValidNoAnagramsTest(String input, boolean expected) {
    assertEquals(expected, Year2017Day04.isValidNoAnagrams(input));
  }
}
