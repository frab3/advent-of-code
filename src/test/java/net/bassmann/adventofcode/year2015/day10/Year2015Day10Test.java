package net.bassmann.adventofcode.year2015.day10;

import static net.bassmann.adventofcode.year2015.day10.Year2015Day10.lookAndSay;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day10Test {

  private Day today = new Year2015Day10();

  @Test
  void solvePart1() {
    assertEquals("492982", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("6989950", today.solvePart2());
  }

  @Test
  void lookAndSayTest() {
    assertEquals("11", lookAndSay("1"));
    assertEquals("21", lookAndSay("11"));
    assertEquals("1211", lookAndSay("21"));
    assertEquals("111221", lookAndSay("1211"));
    assertEquals("312211", lookAndSay("111221"));
  }
}
