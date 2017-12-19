package net.bassmann.adventofcode.year2017.day19;

import static net.bassmann.adventofcode.year2017.day19.Year2017Day19.solve;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import net.bassmann.adventofcode.common.Solution;
import org.junit.jupiter.api.Test;

class Year2017Day19Test {

  private final Day today = new Year2017Day19();

  private final List<String> example =
      List.of(
          "     |          ",
          "     |  +--+    ",
          "     A  |  C    ",
          " F---|--|-E---+ ",
          "     |  |  |  D ",
          "     +B-+  +--+ ",
          "                ");

  @Test
  void solvePart1() {
    assertEquals("XYFDJNRCQA", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("17450", today.solvePart2());
  }

  @Test
  void exampleTest() {
    Solution s = solve(example);
    assertEquals("ABCDEF", s.getPartOne());
    assertEquals("38", s.getPartTwo());
  }
}
