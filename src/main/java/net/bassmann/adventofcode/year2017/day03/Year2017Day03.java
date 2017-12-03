package net.bassmann.adventofcode.year2017.day03;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 3: Spiral Memory.
 *
 * <p>You come across an experimental new kind of memory stored on an infinite two-dimensional grid.
 *
 * <p>Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and
 * then counting up while spiraling outward. For example, the first few squares are allocated like
 * this:
 *
 * <pre>
 * 17  16  15  14  13
 * 18   5   4   3  12
 * 19   6   1   2  11
 * 20   7   8   9  10
 * 21  22  23---> ...
 * </pre>
 *
 * While this is very space-efficient (no squares are skipped), requested data must be carried back
 * to square 1 (the location of the only access port for this memory system) by programs that can
 * only move up, down, left, or right. They always take the shortest path: the Manhattan Distance
 * between the location of the data and square 1.
 *
 * <p>For example:
 *
 * <ul>
 * <li>Data from square 1 is carried 0 steps, since it's at the access port.
 * <li>Data from square 12 is carried 3 steps, such as: down, left, left.
 * <li>Data from square 23 is carried only 2 steps: up twice.
 * <li>Data from square 1024 must be carried 31 steps.
 * </ul>
 *
 * <h2>Part One</h2>
 *
 * How many steps are required to carry the data from the square identified in your puzzle input all
 * the way to the access port?
 *
 * <h2>Part Two</h2>
 *
 * As a stress test on the system, the programs here clear the grid and then store the value 1 in
 * square 1. Then, in the same allocation order as shown above, they store the sum of the values in
 * all adjacent squares, including diagonals.
 *
 * <p>So, the first few squares' values are chosen as follows:
 *
 * <ul>
 * <li>Square 1 starts with the value 1.
 * <li>Square 2 has only one adjacent filled square (with value 1), so it also stores 1.
 * <li>Square 3 has both of the above squares as neighbors and stores the sum of their values, 2.
 * <li>Square 4 has all three of the aforementioned squares as neighbors and stores the sum of
 * their values, 4.
 * <li>Square 5 only has the first and fourth squares as neighbors, so it gets the value 5.
 * </ul>
 *
 * Once a square is written, its value does not change. Therefore, the first few squares would
 * receive the following values:
 *
 * <pre>
 * 147  142  133  122   59
 * 304    5    4    2   57
 * 330   10    1    1   54
 * 351   11   23   25   26
 * 362  747  806--->   ...
 * </pre>
 *
 * What is the first value written that is larger than your puzzle input?
 */
public class Year2017Day03 extends AbstractDay {

  public Year2017Day03() {
    super(2017, 3);
  }

  @Override
  public String solvePart1() {
    Coordinates c = SquareSpiral.numberToCoordinates(getInput());
    return Integer.toString(c.getManhattanDistance());
  }

  @Override
  public String solvePart2() {
    final SquareSpiralWithNeighbourSums spiral = new SquareSpiralWithNeighbourSums();
    int solution = spiral.optimizedFindNumberGreaterThan(getInput());
    return Integer.toString(solution);
  }

  private int getInput() {
    return Integer.parseInt(getRiddleInput().firstLine());
  }
}
