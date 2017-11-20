package net.bassmann.adventofcode.year2015.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2015Day09Test {

  private final Year2015Day09 today = new Year2015Day09();

  static final List<String> TEST_INPUT =
      List.of("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141");

  @Test
  void testInput() {
    DistanceMap map = new DistanceMap();
    TEST_INPUT.forEach(map::addFromString);
    today.calculate(map);
    int expectedMin = 605;
    int expectedMax = 982;

    assertEquals(expectedMin, today.getMinDistance());
    assertEquals(expectedMax, today.getMaxDistance());
  }

  @Test
  void solvePart1() {
    assertEquals("141", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("736", today.solvePart2());
  }
}
