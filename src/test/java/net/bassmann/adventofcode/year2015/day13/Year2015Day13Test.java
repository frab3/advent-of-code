package net.bassmann.adventofcode.year2015.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day13Test {

  private final Day today = new Year2015Day13();

  static final List<String> TEST_INPUT =
      List.of(
          "Alice would gain 54 happiness units by sitting next to Bob.",
          "Alice would lose 79 happiness units by sitting next to Carol.",
          "Alice would lose 2 happiness units by sitting next to David.",
          "Bob would gain 83 happiness units by sitting next to Alice.",
          "Bob would lose 7 happiness units by sitting next to Carol.",
          "Bob would lose 63 happiness units by sitting next to David.",
          "Carol would lose 62 happiness units by sitting next to Alice.",
          "Carol would gain 60 happiness units by sitting next to Bob.",
          "Carol would gain 55 happiness units by sitting next to David.",
          "David would gain 46 happiness units by sitting next to Alice.",
          "David would lose 7 happiness units by sitting next to Bob.",
          "David would gain 41 happiness units by sitting next to Carol.");

  @Test
  void testInput() {
    int actual = Year2015Day13.getOptimalHappiness(TEST_INPUT);

    assertEquals(330, actual);
  }

  @Test
  void solvePart1() {
    assertEquals("709", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("668", today.solvePart2());
  }
}
