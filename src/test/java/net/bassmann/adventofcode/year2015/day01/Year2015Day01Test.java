package net.bassmann.adventofcode.year2015.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class Year2015Day01Test {

  private final Day today = new Year2015Day01();

  /**
   * Test part 1 examples.
   *
   * <ul>
   *   <li>(()) and ()() both result in floor 0.
   *   <li>((( and (()(()( both result in floor 3.
   *   <li>))((((( also results in floor 3.
   *   <li>()) and ))( both result in floor -1 (the first basement level).
   *   <li>))) and )())()) both result in floor -3.
   * </ul>
   *
   * @param input the instructions
   * @param expected the final floor
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/2015/day01-part1.csv")
  void getFinalFloor(String input, int expected) {
    long actual = Year2015Day01.getFinalFloor(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"), 1", ")))), 1", "()()), 5"})
  void parseInstruction(String input, int expected) {
    int actual = Year2015Day01.getFirstTimeIntoBasement(input);
    assertEquals(expected, actual);
  }

  @Test
  void solvePart1() {
    assertEquals("138", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1771", today.solvePart2());
  }
}
