package net.bassmann.adventofcode.year2016.day01;

import java.util.HashSet;
import java.util.Set;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

public class Year2016Day01 extends AbstractDay {

  private Solution solution;

  public Year2016Day01() {
    super(2016, 1);
  }

  @Override
  public String solvePart1() {
    return getSolution().getPartOne();
  }

  @Override
  public String solvePart2() {
    return getSolution().getPartTwo();
  }

  Solution getSolution() {
    if (solution == null) {
      solution = solve(getRiddleInput().firstLine());
    }
    return solution;
  }

  static Solution solve(String input) {
    String[] instructions = input.split(", ");

    Coordinates c = new Coordinates(0, 0);
    Direction d = Direction.NORTH;

    Coordinates bunnyHQ = null;
    Set<Coordinates> visited = new HashSet<>();

    for (String i : instructions) {
      d = d.turn(i.charAt(0));
      for (int step = Integer.parseInt(i.substring(1)); step > 0; step--) {
        c = c.go(d);
        if (bunnyHQ == null && !visited.add(c)) {
          bunnyHQ = c;
        }
      }
    }

    return new Solution(
        c.getManhattanDistance(), bunnyHQ != null ? bunnyHQ.getManhattanDistance() : 0);
  }
}
