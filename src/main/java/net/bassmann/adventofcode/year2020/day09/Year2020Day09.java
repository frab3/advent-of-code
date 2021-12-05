package net.bassmann.adventofcode.year2020.day09;

import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day09 extends AbstractDay {

  public Year2020Day09() {
    super(2020, 9);
  }

  @Override
  public String solvePart1() {
    long[] numbers = getRiddleInput().lines().mapToLong(Long::parseLong).toArray();
    return Long.toString(doesNotMatchPreamble(numbers, 25));
  }

  @Override
  public String solvePart2() {
    long invalidNumber = 1124361034L;
    long[] numbers = getRiddleInput().lines().mapToLong(Long::parseLong).toArray();
    return Long.toString(breakCode(numbers, invalidNumber));
  }

  long doesNotMatchPreamble(long[] numbers, int preamble) {
    for (int lookAtPos = preamble; lookAtPos < numbers.length; lookAtPos++) {
      if (!hasSum(lookAtPos, numbers, preamble)) return numbers[lookAtPos];
    }
    return -1;
  }

  boolean hasSum(int lookAtPos, long[] numbers, int preamble) {
    int beginIndex = lookAtPos - preamble;
    for (int i = beginIndex; i < lookAtPos - 1; i++) {
      for (int j = beginIndex + 1; j < lookAtPos; j++) {
        if (numbers[i] + numbers[j] == numbers[lookAtPos]) return true;
      }
    }
    return false;
  }

  long breakCode(long[] numbers, long invalidNumber) {
    int startIndex = 0;
    long sum = 0;
    int endIndex = startIndex;
    while (sum != invalidNumber) {
      while (sum < invalidNumber) {
        sum += numbers[endIndex++];
      }
      if (sum > invalidNumber) {
        endIndex = ++startIndex;
        sum = 0;
      }
    }
    long min =
        IntStream.rangeClosed(startIndex, endIndex).mapToLong(i -> numbers[i]).min().orElse(0);
    long max =
        IntStream.rangeClosed(startIndex, endIndex).mapToLong(i -> numbers[i]).max().orElse(0);
    return min + max;
  }

  public static void main(String[] args) {
    var today = new Year2020Day09();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
