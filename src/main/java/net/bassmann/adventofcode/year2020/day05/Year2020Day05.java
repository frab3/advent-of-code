package net.bassmann.adventofcode.year2020.day05;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day05 extends AbstractDay {

  public Year2020Day05() {
    super(2020, 5);
  }

  @Override
  public String solvePart1() {
    int max = getRiddleInput().lines().mapToInt(Year2020Day05::getId).max().orElse(-1);
    return Integer.toString(max);
  }

  @Override
  public String solvePart2() {
    final boolean[] allSeats = new boolean[948];
    getRiddleInput().lines().mapToInt(Year2020Day05::getId).forEach(i -> allSeats[i] = true);
    for (int i = 1; i < allSeats.length - 2; i++) {
      if (allSeats[i - 1] && !allSeats[i] && allSeats[i + 1]) {
        return Integer.toString(i);
      }
    }
    return null;
  }

  static int binarySpaceDecode(String input, char lower, char upper) {
    final int range = (1 << input.length());
    int l = 0;
    int u = range - 1;
    for (int i = 0; i < input.length(); i++) {
      final int mid = l + ((u - l) / 2);
      if (input.charAt(i) == lower) {
        u = mid;
      } else {
        l = mid + 1;
      }
    }
    return l;
  }

  static int getId(String input) {
    return binarySpaceDecode(input.substring(0, 7), 'F', 'R') * 8
        + binarySpaceDecode(input.substring(7), 'L', 'R');
  }

  public static void main(String[] args) {
    System.out.println(binarySpaceDecode("FBFBBFF", 'F', 'B')); // 44
    System.out.println(binarySpaceDecode("RLR", 'L', 'R')); // 5
    System.out.println(getId("FBFBBFFRLR")); // 357

    Year2020Day05 today = new Year2020Day05();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
