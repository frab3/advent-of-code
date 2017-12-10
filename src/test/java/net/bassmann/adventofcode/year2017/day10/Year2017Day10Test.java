package net.bassmann.adventofcode.year2017.day10;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2017Day10Test {

  private final Day today = new Year2017Day10();

  @Test
  void solvePart1() {
    assertEquals("23874", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("e1a65bfb5a5ce396025fab5528c25a87", today.solvePart2());
  }

  @Test
  void partOneExample() {
    int actual = Year2017Day10.simpleKnot("3,4,1,5", 5);
    assertEquals(12, actual);
  }

  @ParameterizedTest
  @CsvSource({
      "'', a2582a3a0e66e6e86e3812dcb672a272",
      "'AoC 2017', 33efeb34ea91902bb2f59c9920caa6cd",
      "'1,2,3', 3efbe78a8d82f29979031a4aa0b16a9d",
      "'1,2,4', 63960835bcdc130f0b66d7ff4f6a5a8e",
  })
  void makeKnotHashTest(String input, String expected) {
    assertEquals(expected, Year2017Day10.makeKnotHash(input));
  }

  @Test
  void reverseTest_exercisePartOneExample() {
    int[] array = {0, 1, 2, 3, 4};
    int[] expected = {2, 1, 0, 3, 4};

    Year2017Day10.reverse(array, 0, 3);
    assertArrayEquals(expected, array);

    expected = new int[]{4, 3, 0, 1, 2};
    Year2017Day10.reverse(array, 3, 4);
    assertArrayEquals(expected, array);

    Year2017Day10.reverse(array, 3, 1);
    assertArrayEquals(expected, array);

    expected = new int[]{3, 4, 2, 1, 0};
    Year2017Day10.reverse(array, 1, 5);
    assertArrayEquals(expected, array);
  }

  @Test
  void toLengthArrayTest() {
    int[] actual = Year2017Day10.toLengthArray("1,2,3");
    int[] expected = {49, 44, 50, 44, 51, 17, 31, 73, 47, 23};
    assertArrayEquals(expected, actual);
  }

  @Test
  void toDenseHashTest() {
    int[] input = {65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22};
    int[] expected = {64};
    int[] actual = Year2017Day10.toDenseHash(input);
    assertArrayEquals(expected, actual);
  }

  @Test
  void toHexTest() {
    int[] input = {64, 7, 255};
    String expected = "4007ff";
    assertEquals(expected, Year2017Day10.toHex(input));
  }
}
