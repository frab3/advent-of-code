package net.bassmann.adventofcode.year2017.day16;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class PartnerDanceMoveTest {

  @Test
  void doMoveTest() {
    char[] programs = {'a', 'b', 'c', 'd', 'e'};
    char[] expected = {'a', 'e', 'c', 'd', 'b'};
    new PartnerDanceMove("pb/e").doMove(programs);
    assertArrayEquals(expected, programs);
  }
}
