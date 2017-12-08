package net.bassmann.adventofcode.year2017.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2017Day09Test {

  private final List<String> exampleInput = List.of("");

  private final Year2017Day09 today = new Year2017Day09();

  @Test
  void solvePart1() {
    assertEquals("0", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("0", today.solvePart2());
  }

  @Test
  void part1Test() {
    int actual = Year2017Day09.part1(exampleInput);
    assertEquals(0, actual);
  }

  @Test
  void part2Test() {
    int actual = Year2017Day09.part2(exampleInput);
    assertEquals(0, actual);
  }
}
