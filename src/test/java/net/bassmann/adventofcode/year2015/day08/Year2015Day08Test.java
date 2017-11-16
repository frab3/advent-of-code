package net.bassmann.adventofcode.year2015.day08;

import static net.bassmann.adventofcode.year2015.day08.Year2015Day08.getDecodedLengthDifference;
import static net.bassmann.adventofcode.year2015.day08.Year2015Day08.getEncodedLengthDifference;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Year2015Day08Test {

  private final Day today = new Year2015Day08();

  @Test
  void solvePart1() {
    assertEquals("1350", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("2085", today.solvePart2());
  }

  @DisplayName("Test example input")
  @ParameterizedTest(
    name = "test {index} ==> input=''{0}'', code.length={1}, mem.length={2}, enc.length={3}"
  )
  @CsvSource({
    "\"\", 2, 0, 6",
    "\"abc\", 5, 3, 9",
    "\"aaa\\\"aaa\", 10, 7, 16",
    "\"\\x27\", 6, 1, 11"
  })
  void exampleInputTest(String input, int inputLength, int decodedLength, int encodedLength) {
    assertEquals(decodedLength, inputLength - getDecodedLengthDifference(input));
    assertEquals(encodedLength, inputLength + getEncodedLengthDifference(input));
  }
}
