package net.bassmann.adventofcode.year2017.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2017Day07Test {

  private static final List<String> EXAMPLE_INPUT =
      List.of(
          "pbga (66)",
          "xhth (57)",
          "ebii (61)",
          "havc (66)",
          "ktlj (57)",
          "fwft (72) -> ktlj, cntj, xhth",
          "qoyq (66)",
          "padx (45) -> pbga, havc, qoyq",
          "tknk (41) -> ugml, padx, fwft",
          "jptl (61)",
          "ugml (68) -> gyxo, ebii, jptl",
          "gyxo (61)",
          "cntj (57)");

  private final Year2017Day07 today = new Year2017Day07();

  @Test
  void solvePart1() {
    assertEquals("mkxke", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("268", today.solvePart2());
  }

  @Test
  void part1Test() {
    String actual = Year2017Day07.findRootNode(EXAMPLE_INPUT);
    assertEquals("tknk", actual);
  }

  @Test
  void part2Test() {
    int actual = Year2017Day07.buildTreeAndSolve("tknk", EXAMPLE_INPUT);
    assertEquals(60, actual);
  }

  @Test
  void parseNodeNameTest() {
    String actual = Year2017Day07.parseNodeName("pbga (66)");
    assertEquals("pbga", actual);

    actual = Year2017Day07.parseNodeName("fwft (72) -> ktlj, cntj, xhth");
    assertEquals("fwft", actual);
  }

  @Test
  void parseValueTest() {
    int actual = Year2017Day07.parseWeight("pbga (66)");
    assertEquals(66, actual);

    actual = Year2017Day07.parseWeight("fwft (72) -> ktlj, cntj, xhth");
    assertEquals(72, actual);
  }

  @Test
  void parseChildNameTest() {
    List<String> actual = Year2017Day07.parseChildNames("pbga (66)");
    assertEquals(0, actual.size());

    actual = Year2017Day07.parseChildNames("fwft (72) -> ktlj, cntj, xhth");
    assertEquals(3, actual.size());
    assertEquals(List.of("ktlj", "cntj", "xhth"), actual);
  }
}
