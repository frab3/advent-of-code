package net.bassmann.adventofcode.year2015.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SimpleLightGridTest {

  @Test
  void lightGridTest() {
    LightGrid g = new SimpleLightGrid(1000);
    assertEquals(0, g.count());

    g.executeCommand("turn on 0,0 through 999,999");
    assertEquals(1_000_000, g.count());

    g.executeCommand("turn off 499,499 through 500,500");
    assertEquals(1_000_000 - 4, g.count());

    g.executeCommand("toggle 0,0 through 999,0");
    assertEquals(1_000_000 - 1004, g.count());
  }

}
