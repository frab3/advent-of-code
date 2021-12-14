package net.bassmann.adventofcode.year2021.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class DumboOctopusesTest {

  List<String> input =
      List.of(
          "5483143223",
          "2745854711",
          "5264556173",
          "6141336146",
          "6357385478",
          "4167524645",
          "2176841721",
          "6882881134",
          "4846848554",
          "5283751526");

  @Test
  void example1() {
    var oct = new DumboOctopuses(input);
    int flashes = oct.oneStep();
    assertEquals(0, flashes);
    var actual = oct.toString();
    String expected =
        "6594254334\n"
            + "3856965822\n"
            + "6375667284\n"
            + "7252447257\n"
            + "7468496589\n"
            + "5278635756\n"
            + "3287952832\n"
            + "7993992245\n"
            + "5957959665\n"
            + "6394862637";

    assertEquals(expected, actual);

    String expected2 =
        "8807476555\n"
            + "5089087054\n"
            + "8597889608\n"
            + "8485769600\n"
            + "8700908800\n"
            + "6600088989\n"
            + "6800005943\n"
            + "0000007456\n"
            + "9000000876\n"
            + "8700006848";

    flashes = oct.oneStep();
    assertEquals(35, flashes);
    assertEquals(expected2, oct.toString());
  }

  @Test
  void example1From1To2() {
    List<String> input =
        List.of(
            "6594254334",
            "3856965822",
            "6375667284",
            "7252447257",
            "7468496589",
            "5278635756",
            "3287952832",
            "7993992245",
            "5957959665",
            "6394862637");

    String expected2 =
        "8807476555\n"
            + "5089087054\n"
            + "8597889608\n"
            + "8485769600\n"
            + "8700908800\n"
            + "6600088989\n"
            + "6800005943\n"
            + "0000007456\n"
            + "9000000876\n"
            + "8700006848";

    var oct = new DumboOctopuses(input);
    int flashes = oct.oneStep();
    assertEquals(expected2, oct.toString());

    assertEquals(35, flashes);
  }

  @Test
  void example1TenSteps() {
    var oct = new DumboOctopuses(input);
    int flashes = 0;
    for (int s = 0; s < 10; s++) {
      flashes += oct.oneStep();
    }
    assertEquals(204, flashes);
  }

  @Test
  void simpleTest() {
    List<String> input = List.of("11111", "19991", "19191", "19991", "11111");

    String expected = "34543\n" + "40004\n" + "50005\n" + "40004\n" + "34543";

    var oct = new DumboOctopuses(input);

    oct.oneStep();

    assertEquals(expected, oct.toString());

    String expected2 = "45654\n" + "51115\n" + "61116\n" + "51115\n" + "45654";

    oct.oneStep();
    assertEquals(expected2, oct.toString());
  }
}
