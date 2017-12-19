package net.bassmann.adventofcode.year2017.day20;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2017Day20 extends AbstractDay {

  public Year2017Day20() {
    super(2017, 20);
  }

  @Override
  public String solvePart1() {
    int i = solve(getRiddleInput().asList());
    return Integer.toString(i);
  }

  @Override
  public String solvePart2() {
    return null;
  }

  static int solve(List<String> input) {


    return 0;
  }

  public static void main(String[] args) {
    List<String> example = List.of("");

    System.out.println(solve(example));
  }
}
