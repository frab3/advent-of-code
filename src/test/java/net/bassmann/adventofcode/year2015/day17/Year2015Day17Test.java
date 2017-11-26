package net.bassmann.adventofcode.year2015.day17;

import static net.bassmann.adventofcode.year2015.day17.Year2015Day17.countOfSize;
import static net.bassmann.adventofcode.year2015.day17.Year2015Day17.fillContainers;
import static net.bassmann.adventofcode.year2015.day17.Year2015Day17.getMinimum;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day17Test {

  private final Day today = new Year2015Day17();

  @Test
  void theExample() {

    final int eggnog = 25;

    List<Container> allContainers =
        List.of(
            new Container(20),
            new Container(15),
            new Container(10),
            new Container(5),
            new Container(5));

    assertEquals(20, allContainers.get(0).getCapacity());
    assertEquals(4, Year2015Day17.countPossibleCombinations(allContainers, 0, 25));

    final List<Set<Container>> filled = fillContainers(allContainers, new HashSet<>(), 0, eggnog);
    assertEquals(4, filled.size());

    final int min = getMinimum(filled);
    assertEquals(2, min);

    final long minCount = countOfSize(filled, min);
    assertEquals(3, minCount);
  }

  @Test
  void solvePart1() {
    assertEquals("4372", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("4", today.solvePart2());
  }
}
