package net.bassmann.adventofcode.year2015.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BrightnessLightGridTest {

  @Test
  void lightGridTest() {
    LightGrid g = new BrightnessLightGrid(1000);
    assertEquals(0, g.count());

    g.executeCommand("turn off 0,0 through 999,999");
    assertEquals(0, g.count());

    g.executeCommand("turn on 0,0 through 999,999");
    assertEquals(1_000_000, g.count());

    g.executeCommand("turn on 0,0 through 999,999");
    assertEquals(2_000_000, g.count());

    g.executeCommand("toggle 0,0 through 999,999");
    assertEquals(4_000_000, g.count());

    g.executeCommand("turn off 0,0 through 999,999");
    assertEquals(3_000_000, g.count());
  }

}
