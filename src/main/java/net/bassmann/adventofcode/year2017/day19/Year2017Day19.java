package net.bassmann.adventofcode.year2017.day19;

import java.util.Collections;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2017Day19 extends AbstractDay {

  public Year2017Day19() {
    super(2017, 19);
  }

  @Override
  public String solvePart1() {
    int i = solve(getRiddleInput().asList());
    return Integer.toString(i);
  }

  @Override
  public String solvePart2() {
    return "0";
  }

  static int solve(List<String> input) {
    return 0;
  }

  public static void main(String[] args) {
    System.out.println(solve(Collections.emptyList()));
  }
}
