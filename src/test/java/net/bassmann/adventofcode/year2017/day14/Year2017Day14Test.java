package net.bassmann.adventofcode.year2017.day14;

import static net.bassmann.adventofcode.year2017.day14.Year2017Day14.countDistinctRegions;
import static net.bassmann.adventofcode.year2017.day14.Year2017Day14.countUsed;
import static net.bassmann.adventofcode.year2017.day14.Year2017Day14.hexStringToGridRow;
import static net.bassmann.adventofcode.year2017.day14.Year2017Day14.makeGrid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day14Test {

  private final Day today = new Year2017Day14();

  @Test
  void solvePart1() {
    assertEquals("8226", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("1128", today.solvePart2());
  }

  @Test
  void hexStringToGridRowTest() {
    String actual = hexStringToGridRow("a0c2017");
    String expected = "1010000011000010000000010111";
    String expectedGrid =
        expected
            .chars()
            .map(c -> (char) c == '1' ? '#' : '.')
            .mapToObj(c -> Character.toString((char) c))
            .collect(Collectors.joining());
    assertEquals(expectedGrid, actual);
  }

  @Test
  void exampleTest() {
    List<String> grid = makeGrid("flqrgnkx");
    assertTrue(grid.get(0).startsWith("##.#.#.."));
    assertTrue(grid.get(1).startsWith(".#.#.#.#"));
    assertTrue(grid.get(2).startsWith("....#.#."));
    assertTrue(grid.get(3).startsWith("#.#.##.#"));
    assertTrue(grid.get(4).startsWith(".##.#..."));
    assertTrue(grid.get(5).startsWith("##..#..#"));
    assertTrue(grid.get(6).startsWith(".#...#.."));
    assertTrue(grid.get(7).startsWith("##.#.##."));

    int used = countUsed(grid);
    assertEquals(8108, used);

    int regions = countDistinctRegions(grid);
    assertEquals(1242, regions);
  }
}
