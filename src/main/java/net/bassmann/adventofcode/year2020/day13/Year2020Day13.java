package net.bassmann.adventofcode.year2020.day13;

import java.util.Arrays;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

/** Bus schedule */
public class Year2020Day13 extends AbstractDay {

  public Year2020Day13() {
    super(2020, 13);
  }

  @Override
  public String solvePart1() {
    List<String> input = getRiddleInput().asList();
    int departFromTime = Integer.parseInt(input.get(0));
    int[] busNumbers =
        Arrays.stream(input.get(1).split(","))
            .filter(s -> !s.equals("x"))
            .mapToInt(Integer::parseInt)
            .toArray();

    int result = findBusNumberWhichLeavesNext(departFromTime, busNumbers);
    return Integer.toString(result);
  }

  static int findBusNumberWhichLeavesNext(int departFromTime, int[] busNumbers) {
    int busToPick = 0;
    int leavesIn = Integer.MAX_VALUE;
    for (int i = 0; i < busNumbers.length; i++) {
      int bus = busNumbers[i];
      int l = bus - departFromTime % bus;
      if (l < leavesIn) {
        busToPick = bus;
        leavesIn = l;
      }
    }

    return busToPick * leavesIn;
  }

  @Override
  public String solvePart2() {
    int[] busNumbers =
        Arrays.stream(getRiddleInput().asList().get(1).split(","))
            .mapToInt(s -> s.equals("x") ? -1 : Integer.parseInt(s))
            .toArray();
    printBusAndOffset(busNumbers);
    return null;
  }

  void printBusAndOffset(int[] busNumbers) {
    for (int offset = 0; offset < busNumbers.length; offset++) {
      int bus = busNumbers[offset];
      if (bus > 0) {
        System.out.printf("Bus %d should have offset %d%n", bus, offset);
      }
    }
  }

  // Bus 13 should have offset 0
  // Bus 41 should have offset 3
  // Bus 997 should have offset 13
  // Bus 23 should have offset 21
  // Bus 19 should have offset 32
  // Bus 29 should have offset 42
  // Bus 619 should have offset 44
  // Bus 37 should have offset 50
  // Bus 17 should have offset 61

  // 17,x,13,19 is 3417.
  // (3417 + 0) % 17 = 0 | 3417 / 17 = 201 | 3417 % 17 = 0
  // (3417 + 2) % 13 = 0 | 2419 / 13 = 263 | 3417 % 13 = 11
  // (3417 + 3) % 19 = 0 | 3420 / 19 = 180 | 3417 % 19 = 16

  public static void main(String[] args) {
    var today = new Year2020Day13();
    // System.out.println(findBusNumberWhichLeavesNext(939, new int[] {7, 13, 59, 31, 19}));
    // System.out.println(today.solvePart1());
    // System.out.println(today.solvePart2());
    long increment = 19;
    long n = increment - 3;
    while (n % 17 != 0 || n % 13 != 13 - 2) {
      // number % bus != bus - offset
      n += increment;
    }
    System.out.println(n);

    increment = 997;
    n = increment - 13;
    while (n % 13 != 0
        // Bus 13 should have offset 0
        || n % 41 != 41 - 3
        // Bus 41 should have offset 3
        // Bus 997 should have offset 13
        || n % 23 != 23 - 21
        // Bus 23 should have offset 21
        || n % 19 != 19 - (32 - 19)
        // Bus 19 should have offset 32
        || n % 29 != 29 - (42 - 29)
        // Bus 29 should have offset 42
        || n % 619 != 619 - 44
        // Bus 619 should have offset 44
        || n % 37 != 37 - (50 - 37)
        // Bus 37 should have offset 50
        || n % 17 != 17 - (61 % 17)
    // Bus 17 should have offset 61
    ) {
      n += increment;
    }
    System.out.println(n);
  }
}
