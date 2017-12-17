package net.bassmann.adventofcode.year2017.day16;

import static net.bassmann.adventofcode.year2017.day16.Year2017Day16.generatePrograms;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day16Test {

  private final Day today = new Year2017Day16();

  @Test
  void solvePart1() {
    assertEquals("fnloekigdmpajchb", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("amkjepdhifolgncb", today.solvePart2());
  }

  @Test
  void exampleTest() {
    String example = "s1,x3/4,pe/b";
    String actual = Year2017Day16.dance(example, 5, 1);
    assertEquals("baedc", actual);

    actual = Year2017Day16.dance(example, 5, 2);
    assertEquals("ceadb", actual);
  }

  @Test
  void exampleDanceMoveTest() {
    char[] programs = {'a', 'b', 'c', 'd', 'e'};
    DanceMove.fromString("s1").doMove(programs);
    assertEquals("eabcd", String.valueOf(programs));
    DanceMove.fromString("x3/4").doMove(programs);
    assertEquals("eabdc", String.valueOf(programs));
    DanceMove.fromString("pe/b").doMove(programs);
    assertEquals("baedc", String.valueOf(programs));
  }

  @Test
  void generateProgramsTest() {
    char[] actual = generatePrograms(5);
    char[] expected5 = {'a', 'b', 'c', 'd', 'e'};
    assertArrayEquals(expected5, actual);

    actual = generatePrograms(16);
    char[] expected16 = {
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'
    };
    assertArrayEquals(expected16, actual);
  }
}
