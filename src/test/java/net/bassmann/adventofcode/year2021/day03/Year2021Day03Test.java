package net.bassmann.adventofcode.year2021.day03;

import static net.bassmann.adventofcode.year2021.day03.Year2021Day03.solve1;
import static net.bassmann.adventofcode.year2021.day03.Year2021Day03.solve2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2021Day03Test {

  Day day = new Year2021Day03();

  List<String> input =
      List.of(
          "00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001",
          "00010", "01010");

  @Test
  void solvePart1() {
    var actual = solve1(input);
    assertEquals("10110", actual.get(0));
    assertEquals("01001",actual.get(1));
    assertEquals("3277364", day.solvePart1());
  }

  @Test
  void solvePart2() {
    var actual = solve2(input);
    assertEquals("10111", actual.get(0));
    assertEquals("01010",actual.get(1));
    assertEquals("5736383", day.solvePart2());
  }
}
