package net.bassmann.adventofcode.year2021.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class GridTest {
  @Test
  void diagonal1() {
    var lines = List.of("3,3 -> 1,5", "3,3 -> 5,5", "3,3 -> 5,1", "3,3 -> 1,1");
    Grid g = new Grid(6);
    lines.stream().map(Line::new).forEach(g::add);
    assertEquals("""
        000000
        010001
        001010
        000400
        001010
        010001""", g.toString());
  }
}
