package net.bassmann.adventofcode.year2017.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2017Day11Test {

  private final Year2017Day11 today = new Year2017Day11();

  @Test
  void solvePart1() {
    assertEquals("818", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1596", today.solvePart2());
  }

  @ParameterizedTest
  @CsvSource({
      "'ne,ne,ne', 3, 3",
      "'ne,ne,sw,sw', 0, 2",
      "'ne,ne,s,s', 2, 2",
      "'se,sw,se,sw,sw', 3, 3",
      "'ne,se,s,sw,nw', 1, 2"
  })
  void exampleInput(String input, String distance, String maxDistance) {
    Solution s = Year2017Day11.moveAroundHexGrid(input.split(","));
    assertEquals(distance, s.getPartOne());
    assertEquals(maxDistance, s.getPartTwo());
  }

  @Test
  void distanceTest() {
    final HexGridCell center = HexGridCell.CENTER;
    HexGridCell c = center.move("ne");
    c = c.move("ne");
    c = c.move("ne");
    assertEquals(3, c.distance(HexGridCell.CENTER));

    c = center;
    c = c.move("ne");
    c = c.move("ne");
    c = c.move("sw");
    c = c.move("sw");
    assertEquals(0, c.distance(HexGridCell.CENTER));
  }
}
