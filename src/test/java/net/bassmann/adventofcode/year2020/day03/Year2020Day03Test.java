package net.bassmann.adventofcode.year2020.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2020Day03Test {

  private final Year2020Day03 today = new Year2020Day03();

  private final List<String> exampleInput =
      List.of(
          "..##.......",
          "#...#...#..",
          ".#....#..#.",
          "..#.#...#.#",
          ".#...##..#.",
          "..#.##.....",
          ".#.#.#....#",
          ".#........#",
          "#.##...#...",
          "#...##....#",
          ".#..#...#.#");

  @Test
  void solvePart1() {
    assertEquals("250", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1592662500", today.solvePart2());
  }

  @Test
  void solveExamplePart1() {
    int trees = today.countTrees(exampleInput, 3, 1);
    assertEquals(7, trees);
  }

  @Test
  void solveExamplePart2() {
    long product = today.countAllSlopes(exampleInput);
    assertEquals(336L, product);
  }
}
