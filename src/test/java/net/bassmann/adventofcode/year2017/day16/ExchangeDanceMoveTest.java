package net.bassmann.adventofcode.year2017.day16;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ExchangeDanceMoveTest {

  @Test
  void doMoveTest() {
    char[] programs = {'a', 'b', 'c', 'd', 'e'};
    char[] expected = {'d', 'b', 'c', 'a', 'e'};
    new ExchangeDanceMove("x0/3").doMove(programs);
    assertArrayEquals(expected, programs);
  }
}
