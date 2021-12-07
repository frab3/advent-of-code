package net.bassmann.adventofcode.year2021.day06;

import java.util.regex.Pattern;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day06 extends AbstractDay {

  public Year2021Day06() {
    super(2021, 6);
  }

  @Override
  public String solvePart1() {
    var input =
        Pattern.compile(",")
            .splitAsStream(getRiddleInput().firstLine())
            .mapToInt(Integer::parseInt)
            .toArray();

    return String.valueOf(doDays(new LanternFishSimulator(input), 80));
  }

  static long doDays(LanternFishSimulator sim, int days) {
    for (int d = 1; d <= days; d++) {
      sim.doDay();
    }
    return sim.size();
  }

  @Override
  public String solvePart2() {

    var input =
        Pattern.compile(",")
            .splitAsStream(getRiddleInput().firstLine())
            .mapToInt(Integer::parseInt)
            .toArray();

    return String.valueOf(doDays(new LanternFishSimulator(input), 256));
  }

  public static void main(String[] args) {
    var today = new Year2021Day06();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
