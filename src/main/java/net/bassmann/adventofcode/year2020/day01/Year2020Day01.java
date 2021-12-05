package net.bassmann.adventofcode.year2020.day01;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day01 extends AbstractDay {

  private final int[] inputArray = getRiddleInput().lines().mapToInt(Integer::parseInt).toArray();

  public Year2020Day01() {
    super(2020, 1);
  }

  @Override
  public String solvePart1() {
    return Integer.toString(findMatchingPairAndMultiply(inputArray));
  }

  static int findMatchingPairAndMultiply(int[] input) {
    for (int i = 0; i < input.length - 2; i++) {
      int match = 2020 - input[i];
      for (int j = i + 1; j < input.length - 1; j++) {
        if (input[j] == match) {
          return input[i] * input[j];
        }
      }
    }
    return -1;
  }

  @Override
  public String solvePart2() {
    return Long.toString(findMatchingTripleAndMultiply(inputArray));
  }

  static long findMatchingTripleAndMultiply(int[] input) {
    for (int i = 0; i < input.length - 3; i++) {
      for (int j = i + 1; j < input.length - 2; j++) {
        int match = 2020 - input[i] - input[j];
        for (int k = j + 1; k < input.length - 1; k++) {
          if (input[k] == match) {
            return (long)input[i] * input[j] * input[k];
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    var day = new Year2020Day01();
    System.out.println(day.solvePart1());
    System.out.println(day.solvePart2());
  }
}
