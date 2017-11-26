package net.bassmann.adventofcode.year2015.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LightGridTest {

  private static final List<String> INIT_STATE =
      List.of(".#.#.#", "...##.", "#....#", "..#...", "#.#..#", "####..");

  private static final List<String> NEXT_STATE =
      List.of("..##..", "..##.#", "...##.", "......", "#.....", "#.##..");

  @Test
  void testGetNextState() {
    LightGrid lg = new LightGrid(6);
    lg.init(INIT_STATE);
    assertFalse(lg.getState(0, 0));
    assertTrue(lg.getState(0, 1));
    assertFalse(lg.getState(1, 0));
    lg.next();
    List<String> actual = lg.asList();
    Assertions.assertLinesMatch(NEXT_STATE, actual);
    assertEquals(11, lg.countOn());
  }

  @Test
  void testGetNextState2() {
    LightGrid lg = new LightGrid(3);
    List<String> init = List.of("###", ".#.", "...");
    lg.init(init);
    int count = lg.countOnNeighbours(1, 1);
    assertEquals(3, count);
    assertEquals(4, lg.countOn());
  }
}
