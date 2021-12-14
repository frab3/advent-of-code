package net.bassmann.adventofcode.year2021.day11;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day11 extends AbstractDay {

  Year2021Day11() {
    super(2021, 11);
  }

  @Override
  public String solvePart1() {
    var o = new DumboOctopuses(getRiddleInput().asList());
    int flashes = 0;
    for (int s = 0; s < 100; s++) {
      flashes += o.oneStep();
    }
    return String.valueOf(flashes);
  }

  @Override
  public String solvePart2() {
    var o = new DumboOctopuses(getRiddleInput().asList());
    int flashes = 0;
    int steps = 0;
    while (flashes != 100) {
      flashes = o.oneStep();
      steps++;
    }
    return String.valueOf(steps);
  }

  public static void main(String[] args) {
    var today = new Year2021Day11();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
