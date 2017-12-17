package net.bassmann.adventofcode.year2017.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day17Test {

  private final Day today = new Year2017Day17();

  @Test
  void solvePart1() {
    assertEquals("1311", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("39170601", today.solvePart2());
  }

  @Test
  void examplePart1() {
    assertEquals(1, Year2017Day17.solve(3, 2));
    assertEquals(1, Year2017Day17.solve(3, 3));
    assertEquals(3, Year2017Day17.solve(3, 4));
    assertEquals(2, Year2017Day17.solve(3, 5));
    assertEquals(1, Year2017Day17.solve(3, 6));
    assertEquals(2, Year2017Day17.solve(3, 7));
    assertEquals(6, Year2017Day17.solve(3, 8));
    assertEquals(5, Year2017Day17.solve(3, 9));
    assertEquals(638, Year2017Day17.solve(3, 2017));
  }

  @Test
  void solve2_testFirstIterations() {
    assertEquals(1, Year2017Day17.solve2(3, 1));
    assertEquals(2, Year2017Day17.solve2(3, 2));
    assertEquals(2, Year2017Day17.solve2(3, 3));
    assertEquals(2, Year2017Day17.solve2(3, 4));
    assertEquals(5, Year2017Day17.solve2(3, 5));
    assertEquals(5, Year2017Day17.solve2(3, 6));
    assertEquals(5, Year2017Day17.solve2(3, 7));
    assertEquals(5, Year2017Day17.solve2(3, 8));
    assertEquals(9, Year2017Day17.solve2(3, 9));
  }
}
