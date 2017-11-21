package net.bassmann.adventofcode.year2015.day12;

import static net.bassmann.adventofcode.year2015.day12.Year2015Day12.sumAllNumbersIgnoringRed;
import static net.bassmann.adventofcode.year2015.day12.Year2015Day12.sumAllNumbersInString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2015Day12Test {

  private final Day today = new Year2015Day12();

  @Test
  void solvePart1() {
    assertEquals("156366", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("96852", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({
    "'[1,2,3]', 6",
    "'{\"a\":2,\"b\":4}', 6",
    "'[[[3]]]', 3",
    "'{\"a\":{\"b\":4},\"c\":-1}', 3",
    "'{\"a\":[-1,1]}', 0",
    "'[-1,{\"a\":1}]', 0",
    "'[]', 0",
    "'{}', 0"
  })
  void sumAllNumbersInStringTest(String input, int expected) {
    assertEquals(expected, sumAllNumbersInString(input));
  }

  @ParameterizedTest
  @CsvSource({
    "'[1,2,3]', 6",
    "'[1,{\"c\":\"red\",\"b\":2},3]', 4",
    "'{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}', 0",
    "'[1,\"red\",5] ', 6"
  })
  void sumAllNumbersIgnoringRedTest(String input, int expected) {
    assertEquals(expected, sumAllNumbersIgnoringRed(input));
  }
}
