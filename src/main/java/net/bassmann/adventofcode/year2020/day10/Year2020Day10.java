package net.bassmann.adventofcode.year2020.day10;

import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day10 extends AbstractDay {

  public Year2020Day10() {
    super(2020, 10);
  }

  @Override
  public String solvePart1() {
    int[] adapters =
        padNumbers(getRiddleInput().lines().mapToInt(Integer::parseInt).sorted().toArray());

    int num1 = countDifferenceWithStep(adapters, 1);
    int num3 = countDifferenceWithStep(adapters, 3);
    return Integer.toString(num1 * num3);
  }

  @Override
  public String solvePart2() {
    int[] adapters =
        padNumbers(getRiddleInput().lines().mapToInt(Integer::parseInt).sorted().toArray());
    return Long.toString(calculateNumberOfBranches(adapters));
  }

  static int[] padNumbers(int[] numbers) {
    int[] r = new int[numbers.length + 2];

    r[0] = 0;
    System.arraycopy(numbers, 0, r, 1, numbers.length);
    r[r.length - 1] = numbers[numbers.length - 1] + 3;

    return r;
  }

  static int countDifferenceWithStep(int[] numbers, int step) {
    return (int)
        IntStream.range(0, numbers.length - 1)
            .filter(i -> numbers[i + 1] - numbers[i] == step)
            .count();
  }

  static long calculateNumberOfBranches(int[] numbers) {
    long[] cache = new long[numbers[numbers.length - 1]];
    return recursion(0, numbers, cache);
  }

  static long recursion(int pos, int[] numbers, long[] cache) {
    if (pos == numbers.length - 1) return 1;
    int number = numbers[pos];
    if (cache[number] == 0) {
      int lookAt = pos + 1;
      while (lookAt < numbers.length && numbers[lookAt] - number <= 3) {
        cache[number] += recursion(lookAt, numbers, cache);
        lookAt++;
      }
    }
    return cache[number];
  }

  public static void main(String[] args) {
    var today = new Year2020Day10();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
