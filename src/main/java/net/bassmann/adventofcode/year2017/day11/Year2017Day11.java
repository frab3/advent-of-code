package net.bassmann.adventofcode.year2017.day11;

import java.util.ArrayList;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 11: Hex Ed.
 *
 * <p>Crossing the bridge, you've barely reached the other side of the stream when a program comes
 * up to you, clearly in distress. "It's my child process," she says, "he's gotten lost in an
 * infinite grid!"
 *
 * <p>Fortunately for her, you have plenty of experience with infinite grids.
 *
 * <p>Unfortunately for you, it's a hex grid.
 *
 * <p>The hexagons ("hexes") in this grid are aligned such that adjacent hexes can be found to the
 * north, northeast, southeast, south, southwest, and northwest:
 *
 * <pre>
 * #   \ n  /
 * # nw +--+ ne
 * #   /    \
 * # -+      +-
 * #   \    /
 * # sw +--+ se
 * #   / s  \
 * </pre>
 *
 * <h2>Part One</h2>
 *
 * You have the path the child process took. Starting where he started, you need to determine the
 * fewest number of steps required to reach him. (A "step" means to move from the hex you are in to
 * any adjacent hex.)
 *
 * <p>For example:
 *
 * <ul>
 * <li>ne,ne,ne is 3 steps away.
 * <li>ne,ne,sw,sw is 0 steps away (back where you started).
 * <li>ne,ne,s,s is 2 steps away (se,se).
 * <li>se,sw,se,sw,sw is 3 steps away (s,s,sw).
 * </ul>
 *
 * <h2>Part Two</h2>
 *
 * How many steps away is the furthest he ever got from his starting position?
 */
public class Year2017Day11 extends AbstractDay {

  private Solution solution;

  public Year2017Day11() {
    super(2017, 11);
  }

  @Override
  public String solvePart1() {
    return getSolution().getPartOne();
  }

  @Override
  public String solvePart2() {
    return getSolution().getPartTwo();
  }

  private Solution getSolution() {
    if (solution == null) {
      solution = moveAroundHexGrid(getRiddleInput().firstLine().split(","));
    }
    return solution;
  }

  /**
   * @param directions an array of all the movement instructions.
   * @return the solution contains the distance of the final position to the start cell, and the
   * maximum distance from the start while walking.
   */
  static Solution moveAroundHexGrid(String[] directions) {
    List<Integer> distances = new ArrayList<>();
    HexGridCell c = HexGridCell.CENTER;
    for (String d : directions) {
      c = c.move(d);
      distances.add(c.distanceToCenter());
    }

    final int distanceToCenter = c.distanceToCenter();
    final int maxDistanceToCenter = distances.stream().mapToInt(Integer::intValue).max().orElse(0);

    return new Solution(distanceToCenter, maxDistanceToCenter);
  }
}
