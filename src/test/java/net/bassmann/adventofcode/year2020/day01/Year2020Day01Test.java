package net.bassmann.adventofcode.year2020.day01;

import static net.bassmann.adventofcode.year2020.day01.Year2020Day01.findMatchingPairAndMultiply;
import static net.bassmann.adventofcode.year2020.day01.Year2020Day01.findMatchingTripleAndMultiply;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2020Day01Test {

  private final Day today = new Year2020Day01();

  private List<String> exampleInput = List.of("1721", "979", "366", "299", "675", "1456");
  private int[] exampleArray = exampleInput.stream().mapToInt(Integer::parseInt).toArray();

  @Test
  void solvePart1() {
    assertEquals("926464", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("65656536", today.solvePart2());
  }

  @Test
  void part1ExampleTest() {
    assertEquals(514579, findMatchingPairAndMultiply(exampleArray));
  }

  void part2ExampleTest() {
    assertEquals(241861950L, findMatchingTripleAndMultiply(exampleArray));
  }
}
