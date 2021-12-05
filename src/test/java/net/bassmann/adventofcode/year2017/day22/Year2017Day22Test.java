package net.bassmann.adventofcode.year2017.day22;

import static net.bassmann.adventofcode.year2017.day22.Year2017Day22.solve1;
import static net.bassmann.adventofcode.year2017.day22.Year2017Day22.solve2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day22Test {

  private final Day today = new Year2017Day22();

  private final List<String> example = List.of("..#", "#..", "...");

  @Test
  void solvePart1() {
    assertEquals("5182", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("2512008", today.solvePart2());
  }

  @Test
  void exampleOneTest() {
    assertEquals(41, solve1(example, 70));
  }

  @Test
  void exampleTwoTest() {
    assertEquals(26, solve2(example, 100));
    assertEquals(2511944, solve2(example, 10_000_000));
  }
}
