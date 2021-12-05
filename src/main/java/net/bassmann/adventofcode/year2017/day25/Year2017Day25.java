package net.bassmann.adventofcode.year2017.day25;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2017Day25 extends AbstractDay {

  public Year2017Day25() {
    super(2017, 25);
  }

  @Override
  public String solvePart1() {
    int checksum = solve(12656374);
    return Integer.toString(checksum);
  }

  @Override
  public String solvePart2() {
    return "Merry Christmas";
  }

  private static int solve(int steps) {
    TuringMachine tm = new TuringMachine();
    for (int i = 0; i < steps; i++) {
      tm.step();
    }
    return tm.checksum();
  }
}
