package net.bassmann.adventofcode.year2015.day11;

import static net.bassmann.adventofcode.year2015.day11.PasswordIncrementor.findIndexToIncrement;
import static net.bassmann.adventofcode.year2015.day11.PasswordIncrementor.increment;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PasswordIncrementorTest {

  @Test
  void incrementTest() {
    assertEquals("xy", increment("xx"));
    assertEquals("ya", increment("xz"));
    assertEquals("yb", increment("ya"));
    assertEquals("baaaaaa", increment("azzzzzz"));
    assertEquals("aaa", increment("zz"));
  }

  @Test
  void findIndexToIncrementTest() {
    assertEquals(0, findIndexToIncrement("a"));
    assertEquals(1, findIndexToIncrement("xx"));
    assertEquals(0, findIndexToIncrement("xz"));
    assertEquals(-1, findIndexToIncrement("z"));
    assertEquals(-1, findIndexToIncrement("zz"));
  }
}
