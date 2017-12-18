package net.bassmann.adventofcode.year2017.day18;

import static net.bassmann.adventofcode.year2017.day18.Year2017Day18.run1;
import static net.bassmann.adventofcode.year2017.day18.Year2017Day18.run2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day18Test {

  private final Day today = new Year2017Day18();

  @Test
  void solvePart1() {
    assertEquals("9423", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("7620", today.solvePart2());
  }

  @Test
  void exampleOneTest() {
    List<String> example =
        List.of(
            "set a 1",
            "add a 2",
            "mul a a",
            "mod a 5",
            "snd a",
            "set a 0",
            "rcv a",
            "jgz a -1",
            "set a 1",
            "jgz a -2");
    int rf = run1(example);
    assertEquals(4, rf);
  }

  @Test
  void exampleTwoTest() {
    List<String> example = List.of("snd 1", "snd 2", "snd p", "rcv a", "rcv b", "rcv c", "rcv d");
    int c = run2(example);
    assertEquals(3, c);
  }
}
