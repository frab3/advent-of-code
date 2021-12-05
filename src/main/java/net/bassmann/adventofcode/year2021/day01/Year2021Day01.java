package net.bassmann.adventofcode.year2021.day01;

import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day01 extends AbstractDay {

  protected Year2021Day01() {
    super(2021, 1);
  }

  @Override
  public String solvePart1() {
    // return getRiddleInput().firstLine();
    var ints = getRiddleInput().lines().mapToInt(Integer::parseInt).toArray();
    int c = 0;
    for (int i = 1; i < ints.length; i++) {
      if (ints[i - 1] < ints[i]) {
        c = c + 1;
      }
    }
    return String.valueOf(c);
  }

  @Override
  public String solvePart2() {
    var ints = getRiddleInput().lines().mapToInt(Integer::parseInt).toArray();
    var bints = IntStream.range(1, ints.length-1).map(i ->
        ints[i-1] + ints[i] + ints[i+1]).toArray();
    int c = 0;
    for (int i = 1; i < bints.length; i++) {
      if (bints[i - 1] < bints[i]) {
        c = c + 1;
      }
    }
    return String.valueOf(c);

  }

  int[] transform(int[] input) {
    return IntStream.range(1, input.length-1).map(i ->
        input[i-1] + input[i] + input[i+1]).toArray();
  }

  public static void main(String[] args) {
    var today = new Year2021Day01();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
