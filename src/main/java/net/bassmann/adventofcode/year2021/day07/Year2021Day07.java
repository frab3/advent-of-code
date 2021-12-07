package net.bassmann.adventofcode.year2021.day07;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.regex.Pattern;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day07 extends AbstractDay {

  Year2021Day07() {
    super(2021, 7);
  }

  @Override
  public String solvePart1() {
    int[] input =
        Pattern.compile(",")
            .splitAsStream(getRiddleInput().firstLine())
            .mapToInt(Integer::parseInt)
            .toArray();
    return String.valueOf(findMinPos(input, i -> i));
  }

  static int findMinPos(int[] input, IntUnaryOperator distanceFunction) {
    int min = Arrays.stream(input).min().orElse(-1);
    int max = Arrays.stream(input).max().orElse(-1);

    var minPos = min;
    var minFuel = fuelUsage(input, minPos, distanceFunction);

    for (int p = min + 1; p <= max; p++) {
      var fuel = fuelUsage(input, p, distanceFunction);
      if (fuel < minFuel) {
        minFuel = fuel;
        minPos = p;
      }
    }

    return minFuel;
  }

  static int fuelUsage(int[] crabs, int pos, IntUnaryOperator distanceFunction) {
    return Arrays.stream(crabs).map(c -> Math.abs(c - pos)).map(distanceFunction).sum();
  }

  static int sumUpTo(int n) {
    return (n * (n + 1)) / 2;
  }

  @Override
  public String solvePart2() {
    int[] input =
        Pattern.compile(",")
            .splitAsStream(getRiddleInput().firstLine())
            .mapToInt(Integer::parseInt)
            .toArray();
    return String.valueOf(findMinPos(input, Year2021Day07::sumUpTo));
  }

  public static void main(String[] args) {
    var today = new Year2021Day07();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
