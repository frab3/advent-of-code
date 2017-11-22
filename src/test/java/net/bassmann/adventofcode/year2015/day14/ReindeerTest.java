package net.bassmann.adventofcode.year2015.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReindeerTest {

  @Test
  void Comet_getDistanceAfterSeconds() {
    Reindeer comet = new Reindeer("Comet", 14, 10, 127);

    assertEquals(14, comet.getDistanceAfterSeconds(1));
    assertEquals(140, comet.getDistanceAfterSeconds(10));
    assertEquals(1120, comet.getDistanceAfterSeconds(1000));
  }

  @Test
  void Dancer_getDistanceAfterSeconds() {
    Reindeer dancer = new Reindeer("Dancer", 16, 11, 162);

    assertEquals(16, dancer.getDistanceAfterSeconds(1));
    assertEquals(160, dancer.getDistanceAfterSeconds(10));
    assertEquals(1056, dancer.getDistanceAfterSeconds(1000));
  }
}
