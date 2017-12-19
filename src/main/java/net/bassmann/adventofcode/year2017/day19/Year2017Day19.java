package net.bassmann.adventofcode.year2017.day19;

import static net.bassmann.adventofcode.year2017.day19.Year2017Day19.Direction.EAST;
import static net.bassmann.adventofcode.year2017.day19.Year2017Day19.Direction.NORTH;
import static net.bassmann.adventofcode.year2017.day19.Year2017Day19.Direction.SOUTH;
import static net.bassmann.adventofcode.year2017.day19.Year2017Day19.Direction.WEST;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 19: A Series of Tubes.
 *
 * <p>Somehow, a network packet got <span title="I know how fast it's going, but I don't know where
 * it is.">lost</span> and ended up here. It's trying to follow a routing diagram (your puzzle
 * input), but it's confused about where to go.
 *
 * <p>Its starting point is just off the top of the diagram. Lines (drawn with <code>|</code>,
 * <code>-</code>, and <code>+</code>) show the path it needs to take, starting by going down onto
 * the only line connected to the top of the diagram. It needs to follow this path until it reaches
 * the end (located somewhere within the diagram) and stop there.
 *
 * <p>Sometimes, the lines cross over each other; in these cases, it needs to continue going the
 * same direction, and only turn left or right when there's no other option. In addition, someone
 * has left <em>letters</em> on the line; these also don't change its direction, but it can use them
 * to keep track of where it's been. For example:
 *
 * <pre>
 * <code>     |</code>
 * <code>     |  +--+</code>
 * <code>     A  |  C</code>
 * <code> F---|----E|--+</code>
 * <code>     |  |  |  D</code>
 * <code>     +B-+  +--+</code>
 *
 * </pre>
 *
 * <p>Given this diagram, the packet needs to take the following path:
 *
 * <ul>
 *   <li>Starting at the only line touching the top of the diagram, it must go down, pass through
 *       <code>A</code>, and continue onward to the first <code>+</code>.
 *   <li>Travel right, up, and right, passing through <code>B</code> in the process.
 *   <li>Continue down (collecting <code>C</code>), right, and up (collecting <code>D</code>).
 *   <li>Finally, go all the way left through <code>E</code> and stopping at <code>F</code>.
 * </ul>
 *
 * <p>Following the path to the end, the letters it sees on its path are <code>ABCDEF</code>.
 *
 * <h2>Part One</h2>
 *
 * <p>The little packet looks up at you, hoping you can help it find the way. <em>What letters will
 * it see</em> (in the order it would see them) if it follows the path? (The routing diagram is very
 * wide; make sure you view it without line wrapping.)
 *
 * <h2>Part Two</h2>
 *
 * <p>The packet is curious how many steps it needs to go.
 *
 * <p>For example, using the same routing diagram from the example above...
 *
 * <pre>
 * <code>     |</code>
 * <code>     |  +--+</code>
 * <code>     A  |  C</code>
 * <code> F---|----E|--+</code>
 * <code>     |  |  |  D</code>
 * <code>     +B-+  +--+</code>
 *
 * </pre>
 *
 * <p>...the packet would go:
 *
 * <ul>
 *   <li><code>6</code> steps down (including the first line at the top of the diagram).
 *   <li><code>3</code> steps right.
 *   <li><code>4</code> steps up.
 *   <li><code>3</code> steps right.
 *   <li><code>4</code> steps down.
 *   <li><code>3</code> steps right.
 *   <li><code>2</code> steps up.
 *   <li><code>13</code> steps left (including the <code>F</code> it stops on).
 * </ul>
 *
 * <p>This would result in a total of <code>38</code> steps.
 *
 * <p><em>How many steps</em> does the packet need to go?
 */
public class Year2017Day19 extends AbstractDay {

  private Solution solution;

  public Year2017Day19() {
    super(2017, 19);
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
      solution = solve(getRiddleInput().asList());
    }
    return solution;
  }

  static Solution solve(List<String> input) {
    int row = 0;
    int col = input.get(row).indexOf('|');
    Direction dir = SOUTH;
    StringBuilder sb = new StringBuilder();
    int steps = 0;

    while (true) {
      char cur = input.get(row).charAt(col);
      if (cur == ' ') {
        break;
      } else if (cur == '+') {
        dir = turn(input, row, col, dir);
      } else if (Character.isLetter(cur)) {
        sb.append(cur);
      }

      // go ahead
      steps++;
      switch (dir) {
        case SOUTH:
          row++;
          break;
        case NORTH:
          row--;
          break;
        case EAST:
          col++;
          break;
        case WEST:
          col--;
          break;
      }
    }

    return new Solution(sb.toString(), Integer.toString(steps));
  }

  private static Direction turn(List<String> input, int row, int col, Direction dir) {
    switch (dir) {
      case NORTH:
      case SOUTH:
        return (input.get(row).charAt(col - 1) == ' ') ? EAST : WEST;
      case EAST:
      case WEST:
        String rowAbove = input.get(row - 1);
        return rowAbove.length() > col && rowAbove.charAt(col) != ' ' ? NORTH : SOUTH;
      default:
        throw new IllegalStateException();
    }
  }

  enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }
}
