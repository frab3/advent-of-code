package net.bassmann.adventofcode.year2017.day23;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.year2017.day21.Year2017Day21;

public class Year2017Day23 extends AbstractDay {

  public Year2017Day23() {
    super(2017,23 );
  }

  @Override
  public String solvePart1() {
    return null;
  }

  @Override
  public String solvePart2() {
    return null;
  }

  static long countMul(List<String> input, boolean debug) {
    Processor p = new Processor(input);

    if (!debug) {
      p.set("a", 1);
    }

    while (!p.isWaiting()) {
      p.step();
    }

//    return p.getMulCount();
    return p.get("h");
  }

  public static void main(String[] args) {
    Year2017Day23 d = new Year2017Day23();
    System.out.println(countMul(d.getRiddleInput().asList(), false));
  }
}
