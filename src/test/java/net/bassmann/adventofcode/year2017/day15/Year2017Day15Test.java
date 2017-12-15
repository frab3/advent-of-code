package net.bassmann.adventofcode.year2017.day15;

import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.DIVISOR_A;
import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.DIVISOR_B;
import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.FACTOR_A;
import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.FACTOR_B;
import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.generate;
import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.solve1;
import static net.bassmann.adventofcode.year2017.day15.Year2017Day15.solve2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day15Test {

  private final Day today = new Year2017Day15();

  private final long startA = 65;
  private final long startB = 8921;

  @Test
  void solvePart1() {
    assertEquals("567", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("323", today.solvePart2());
  }

  @Test
  void example1() {
    assertEquals(588, solve1(startA, startB));
  }

  @Test
  void example2() {
    assertEquals(309, solve2(startA, startB));
  }

  @Test
  void generateTest() {
    assertEquals(1092455, generate(startA, FACTOR_A));
    assertEquals(430625591, generate(startB, FACTOR_B));

    assertEquals(1352636452, generate(startA, FACTOR_A, DIVISOR_A));
    assertEquals(1233683848, generate(startB, FACTOR_B, DIVISOR_B));
  }

  @Test
  void last16BitMatchTest() {
    assertFalse(Year2017Day15.last16BitMatch(1092455, 430625591));
    assertFalse(Year2017Day15.last16BitMatch(1181022009, 1233683848));
    assertTrue(Year2017Day15.last16BitMatch(245556042, 1431495498));
    assertFalse(Year2017Day15.last16BitMatch(1744312007, 137874439));
    assertFalse(Year2017Day15.last16BitMatch(1352636452, 285222916));
  }
}
