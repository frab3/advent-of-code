package net.bassmann.adventofcode.year2020.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.year2020.day11.Year2020Day11.WaitingArea;
import org.junit.jupiter.api.Test;

class Year2020Day11Test {

  private final List<String> testInput =
      List.of(
          "L.LL.LL.LL",
          "LLLLLLL.LL",
          "L.L.L..L..",
          "LLLL.LL.LL",
          "L.LL.LL.LL",
          "L.LLLLL.LL",
          "..L.L.....",
          "LLLLLLLLLL",
          "L.LLLLLL.L",
          "L.LLLLL.LL");

  @Test
  void testWaitingAreaCreation() {
    WaitingArea wa = WaitingArea.fromString(testInput);
    assertEquals(testInput.stream().collect(Collectors.joining("\n")), wa.toString());
  }

  @Test
  void testSteps() {
    WaitingArea wa = WaitingArea.fromString(testInput);
    wa.doStep();
    assertEquals(
        "#.##.##.##\n"
            + "#######.##\n"
            + "#.#.#..#..\n"
            + "####.##.##\n"
            + "#.##.##.##\n"
            + "#.#####.##\n"
            + "..#.#.....\n"
            + "##########\n"
            + "#.######.#\n"
            + "#.#####.##", wa.toString());
    wa.doStep();
    assertEquals(
        "#.LL.L#.##\n"
            + "#LLLLLL.L#\n"
            + "L.L.L..L..\n"
            + "#LLL.LL.L#\n"
            + "#.LL.LL.LL\n"
            + "#.LLLL#.##\n"
            + "..L.L.....\n"
            + "#LLLLLLLL#\n"
            + "#.LLLLLL.L\n"
            + "#.#LLLL.##",
        wa.toString());
    wa.doStep();
    assertEquals(
        "#.##.L#.##\n"
            + "#L###LL.L#\n"
            + "L.#.#..#..\n"
            + "#L##.##.L#\n"
            + "#.##.LL.LL\n"
            + "#.###L#.##\n"
            + "..#.#.....\n"
            + "#L######L#\n"
            + "#.LL###L.L\n"
            + "#.#L###.##",
        wa.toString());
    wa.doStep();
    assertEquals(
        "#.#L.L#.##\n"
            + "#LLL#LL.L#\n"
            + "L.L.L..#..\n"
            + "#LLL.##.L#\n"
            + "#.LL.LL.LL\n"
            + "#.LL#L#.##\n"
            + "..L.L.....\n"
            + "#L#LLLL#L#\n"
            + "#.LLLLLL.L\n"
            + "#.#L#L#.##",
        wa.toString());
    wa.doStep();
    assertEquals(
        "#.#L.L#.##\n"
            + "#LLL#LL.L#\n"
            + "L.#.L..#..\n"
            + "#L##.##.L#\n"
            + "#.#L.LL.LL\n"
            + "#.#L#L#.##\n"
            + "..L.L.....\n"
            + "#L#L##L#L#\n"
            + "#.LLLLLL.L\n"
            + "#.#L#L#.##",
        wa.toString());
  }

  @Test
  void testCount() {
    WaitingArea wa = WaitingArea.fromString(testInput);
    int count = wa.findStableStateAndCountOccupiedSeats();
    assertEquals(37, count);
  }

  @Test
  void countSeats2() {
    WaitingArea wa = WaitingArea.fromString(List.of(".##.##.",
        "#.#.#.#",
        "##...##",
        "...L...",
        "##...##",
        "#.#.#.#",
        ".##.##."));
  }
}
