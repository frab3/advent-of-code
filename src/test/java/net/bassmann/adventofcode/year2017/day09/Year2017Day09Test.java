package net.bassmann.adventofcode.year2017.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2017Day09Test {

  private final Day today = new Year2017Day09();

  @Test
  void solvePart1() {
    assertEquals("14421", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("6817", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({
      "'{}', 1",
      "'{{{}}}', 6",
      "'{{},{}}', 5",
      "'{{{},{},{{}}}}', 16",
      "'{<a>,<a>,<a>,<a>}', 1",
      "'{{<ab>},{<ab>},{<ab>},{<ab>}}', 9",
      "'{{<!!>},{<!!>},{<!!>},{<!!>}}', 9",
      "'{{<a!>},{<a!>},{<a!>},{<ab>}}', 3"
  })
  void part1Test(String input, String expected) {
    String actual = Year2017Day09.processInput(input).getPartOne();
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({
      "'<>', 0",
      "'<random characters>', 17",
      "'<<<<>', 3",
      "'<{!>}>', 2",
      "'<!!>', 0",
      "'<!!!>>', 0",
      "'<{o\"i!a,<{i<a>', 10"
  })
  void part2Test(String input, String expected) {
    String actual = Year2017Day09.processInput(input).getPartTwo();
    assertEquals(expected, actual);
  }
}
