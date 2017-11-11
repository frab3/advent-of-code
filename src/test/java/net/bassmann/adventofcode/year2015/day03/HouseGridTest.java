package net.bassmann.adventofcode.year2015.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HouseGridTest {

  @ParameterizedTest
  @CsvSource({">, 2", "^>v<, 4", "^v^v^v^v^v, 2"})
  void testInputsPartOne(String instructions, int expected) {
    HouseGrid hg = new HouseGrid();
    hg.deliverPresents(instructions);
    assertEquals(expected, hg.countHousesVisited());
  }

  @ParameterizedTest
  @CsvSource({"^v, 3", "^>v<, 3", "^v^v^v^v^v, 11"})
  void testInputsPartTwo(String instructions, int expected) {
    HouseGrid hg = new HouseGrid();
    hg.deliverPresentsUsingRoboSanta(instructions);
    assertEquals(expected, hg.countHousesVisited());
  }
}
