package net.bassmann.adventofcode.year2020.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2020Day10Test {

  private final List<String> exampleInput1 =
      List.of("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4");

  private final List<String> exampleInput2 =
      List.of(
          "28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24", "23", "49", "45", "19",
          "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4", "2", "34", "10", "3");

  @Test
  void example1Test() {
    int[] numbers =
        Year2020Day10.padNumbers(
            exampleInput1.stream().mapToInt(Integer::parseInt).sorted().toArray());

    int num1 = Year2020Day10.countDifferenceWithStep(numbers, 1);
    int num3 = Year2020Day10.countDifferenceWithStep(numbers, 3);

    assertEquals(7, num1);
    assertEquals(5, num3);

    long numberOfBranches = Year2020Day10.calculateNumberOfBranches(numbers);
    assertEquals(8, numberOfBranches);
  }

  @Test
  void example2Test() {
    int[] numbers =
        Year2020Day10.padNumbers(
            exampleInput2.stream().mapToInt(Integer::parseInt).sorted().toArray());

    int num1 = Year2020Day10.countDifferenceWithStep(numbers, 1);
    int num3 = Year2020Day10.countDifferenceWithStep(numbers, 3);

    assertEquals(22, num1);
    assertEquals(10, num3);

    long numberOfBranches = Year2020Day10.calculateNumberOfBranches(numbers);
    assertEquals(19208, numberOfBranches);
  }
}
