package net.bassmann.adventofcode.year2017.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import net.bassmann.adventofcode.common.Solution;
import org.junit.jupiter.api.Test;

class Year2017Day12Test {

  private static final List<String> example =
      List.of(
          "0 <-> 2",
          "1 <-> 1",
          "2 <-> 0, 3, 4",
          "3 <-> 2, 4",
          "4 <-> 2, 3, 6",
          "5 <-> 6",
          "6 <-> 4, 5");

  private final Day today = new Year2017Day12();

  @Test
  void solvePart1() {
    assertEquals("175", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("213", today.solvePart2());
  }

  @Test
  void makeGroupsTest() {
    Solution s = Year2017Day12.makeGroups(example);
    assertEquals("6", s.getPartOne());
    assertEquals("2", s.getPartTwo());
  }

  @Test
  void parseLineTest() {
    List<Integer> expected = List.of(0, 2, 3);
    List<Integer> actual = Year2017Day12.parseLine("2 <-> 0, 3");
    assertEquals(expected, actual);
  }
}
