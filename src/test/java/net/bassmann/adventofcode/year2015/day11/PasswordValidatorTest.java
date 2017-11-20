package net.bassmann.adventofcode.year2015.day11;

import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.containsForbiddenChar;
import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.containsStraight;
import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.containsTwoNonOverlappingPairs;
import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.indexOfForbiddenChar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordValidatorTest {

  @Test
  void isValidTest() {}

  @Test
  void containsStraightTest() {
    assertTrue(containsStraight("abc"));
    assertTrue(containsStraight("bcd"));

    assertFalse(containsStraight("abd"));
  }

  @Test
  void containsForbiddenCharTest() {
    assertTrue(containsForbiddenChar("i"));
    assertTrue(containsForbiddenChar("l"));
    assertTrue(containsForbiddenChar("o"));
    assertTrue(containsForbiddenChar("ko"));
    assertTrue(containsForbiddenChar("hello"));

    assertFalse(containsForbiddenChar(""));
    assertFalse(containsForbiddenChar("a"));
    assertFalse(containsForbiddenChar("abc"));
  }

  @Test
  void indexOfForbiddenCharTest() {
    assertEquals(-1, indexOfForbiddenChar(""));
    assertEquals(0, indexOfForbiddenChar("i"));
    assertEquals(0, indexOfForbiddenChar("ii"));
    assertEquals(1, indexOfForbiddenChar("koi"));
  }

  @Test
  void containsTwoNonOverlappingPairsTest() {
    assertTrue(containsTwoNonOverlappingPairs("abbceffg"));
    assertFalse(containsTwoNonOverlappingPairs("abbbg"));
    assertFalse(containsTwoNonOverlappingPairs("abbbbg"));
  }
}
