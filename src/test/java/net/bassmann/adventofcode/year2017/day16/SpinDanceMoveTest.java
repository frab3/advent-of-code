package net.bassmann.adventofcode.year2017.day16;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SpinDanceMoveTest {

  @Test
  void doMoveTest() {
    char[] programs = {'a', 'b', 'c', 'd', 'e'};
    char[] expected = {'e', 'a', 'b', 'c', 'd'};
    new SpinDanceMove("s1").doMove(programs);
    assertArrayEquals(expected, programs);

    char[] programs2 = {'a', 'b', 'c', 'd', 'e'};
    char[] expected2 = {'d', 'e', 'a', 'b', 'c'};
    new SpinDanceMove("s2").doMove(programs2);
    assertArrayEquals(expected2, programs2);
  }
}
