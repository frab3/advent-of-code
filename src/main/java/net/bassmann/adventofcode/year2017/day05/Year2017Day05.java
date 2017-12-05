package net.bassmann.adventofcode.year2017.day05;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 5: A Maze of Twisty Trampolines, All Alike.
 *
 * <p>An urgent interrupt arrives from the CPU: it's trapped in a maze of jump instructions, and it
 * would like assistance from any programs with spare cycles to help find the exit.
 *
 * <p>The message includes a list of the offsets for each jump. Jumps are relative: -1 moves to the
 * previous instruction, and 2 skips the next one. Start at the first instruction in the list. The
 * goal is to follow the jumps until one leads outside the list.
 *
 * <p>In addition, these instructions are a little strange; after each jump, the offset of that
 * instruction increases by 1. So, if you come across an offset of 3, you would move three
 * instructions forward, but change it to a 4 for the next time it is encountered.
 *
 * <p>For example, consider the following list of jump offsets:
 *
 * <pre>
 * 0
 * 3
 * 0
 * 1
 * -3
 * </pre>
 *
 * Positive jumps ("forward") move downward; negative jumps move upward. For legibility in this
 * example, these offset values will be written all on one line, with the current instruction marked
 * in parentheses. The following steps would be taken before an exit is found:
 *
 * <ul>
 *   <li>(0) 3 0 1 -3 - before we have taken any steps.
 *   <li>(1) 3 0 1 -3 - jump with offset 0 (that is, don't jump at all). Fortunately, the
 *       instruction is then incremented to 1.
 *   <li>2 (3) 0 1 -3 - step forward because of the instruction we just modified. The first
 *       instruction is incremented again, now to 2.
 *   <li>2 4 0 1 (-3) - jump all the way to the end; leave a 4 behind.
 *   <li>2 (4) 0 1 -2 - go back to where we just were; increment -3 to -2.
 *   <li>2 5 0 1 -2 - jump 4 steps forward, escaping the maze.
 * </ul>
 *
 * In this example, the exit is reached in 5 steps.
 *
 * <h2>Part One</h2>
 *
 * How many steps does it take to reach the exit?
 *
 * <h2>Part Two</h2>
 *
 * Now, the jumps are even stranger: after each jump, if the offset was three or more, instead
 * decrease it by 1. Otherwise, increase it by 1 as before.
 *
 * <p>Using this rule with the above example, the process now takes 10 steps, and the offset values
 * after finding the exit are left as 2 3 2 3 -1.
 *
 * <p>How many steps does it now take to reach the exit?
 */
public class Year2017Day05 extends AbstractDay {

  public Year2017Day05() {
    super(2017, 5);
  }

  @Override
  public String solvePart1() {
    int[] jumpList = getRiddleInput().lines().mapToInt(Integer::parseInt).toArray();
    int jumps = countStrangeJumps(jumpList);
    return Integer.toString(jumps);
  }

  @Override
  public String solvePart2() {
    int[] jumpList = getRiddleInput().lines().mapToInt(Integer::parseInt).toArray();
    int jumps = countEvenStrangerJumps(jumpList);
    return Integer.toString(jumps);
  }

  static int countStrangeJumps(int[] jumpList) {
    return countJumps(jumpList, false);
  }

  static int countEvenStrangerJumps(int[] jumpList) {
    return countJumps(jumpList, true);
  }

  /**
   * The solution is to jump around and modify the jump instruction list until we reach a position
   * outside of the list's range; note: this will modify the input list directly.
   *
   * @param jumpList the jump instruction list; will be modified.
   * @param part2 set this to {@code true} to get the modification behaviour needed for part 2.
   * @return the number of jumps until an index outside of the input list is reached.
   */
  private static int countJumps(int[] jumpList, boolean part2) {
    int count = 0;
    int pos = 0;
    int offset;

    final int length = jumpList.length;
    while (pos >= 0 && pos < length) {
      offset = jumpList[pos];
      if (part2 && offset >= 3) {
        jumpList[pos]--;
      } else {
        jumpList[pos]++;
      }
      pos = pos + offset;
      count++;
    }

    return count;
  }
}
