package net.bassmann.adventofcode.year2017.day06;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 6: Memory Reallocation.
 *
 * <p>A debugger program here is having an issue: it is trying to repair a memory reallocation
 * routine, but it keeps getting stuck in an infinite loop.
 *
 * <p>In this area, there are sixteen memory banks; each memory bank can hold any number of blocks.
 * The goal of the reallocation routine is to balance the blocks between the memory banks.
 *
 * <p>The reallocation routine operates in cycles. In each cycle, it finds the memory bank with the
 * most blocks (ties won by the lowest-numbered memory bank) and redistributes those blocks among
 * the banks. To do this, it removes all of the blocks from the selected bank, then moves to the
 * next (by index) memory bank and inserts one of the blocks. It continues doing this until it runs
 * out of blocks; if it reaches the last memory bank, it wraps around to the first one.
 *
 * <p>The debugger would like to know how many redistributions can be done before a blocks-in-banks
 * configuration is produced that has been seen before.
 *
 * <p>For example, imagine a scenario with only four memory banks:
 *
 * <ul>
 * <li>The banks start with 0, 2, 7, and 0 blocks. The third bank has the most blocks, so it is
 * chosen for redistribution.
 * <li>Starting with the next bank (the fourth bank) and then continuing to the first bank, the
 * second bank, and so on, the 7 blocks are spread out over the memory banks. The fourth,
 * first, and second banks get two blocks each, and the third bank gets one back. The final
 * result looks like this: 2 4 1 2.
 * <li>Next, the second bank is chosen because it contains the most blocks (four). Because there
 * are four memory banks, each gets one block. The result is: 3 1 2 3.
 * <li>Now, there is a tie between the first and fourth memory banks, both of which have three
 * blocks. The first bank wins the tie, and its three blocks are distributed evenly over the
 * other three banks, leaving it with none: 0 2 3 4.
 * <li>The fourth bank is chosen, and its four blocks are distributed such that each of the four
 * banks receives one: 1 3 4 1.
 * <li>The third bank is chosen, and the same thing happens: 2 4 1 2.
 * </ul>
 *
 * At this point, we've reached a state we've seen before: 2 4 1 2 was already seen. The infinite
 * loop is detected after the fifth block redistribution cycle, and so the answer in this example is
 * 5.
 *
 * <h2>Part One</h2>
 *
 * Given the initial block counts in your puzzle input, how many redistribution cycles must be
 * completed before a configuration is produced that has been seen before?
 *
 * <h2>Part Two</h2>
 *
 * Out of curiosity, the debugger would also like to know the size of the loop: starting from a
 * state that has already been seen, how many block redistribution cycles must be performed before
 * that same state is seen again?
 *
 * <p>In the example above, 2 4 1 2 is seen again after four cycles, and so the answer in that
 * example would be 4.
 *
 * <p>How many cycles are in the infinite loop that arises from the configuration in your puzzle
 * input?
 */
public class Year2017Day06 extends AbstractDay {

  private Solution part1Solution = null;

  public Year2017Day06() {
    super(2017, 6);
  }

  @Override
  public String solvePart1() {
    Solution s = parseInputAndSolve(getRiddleInput().firstLine());
    part1Solution = s;
    return Integer.toString(s.count);
  }

  @Override
  public String solvePart2() {
    if (part1Solution == null) {
      solvePart1();
    }
    Solution s = parseInputAndSolve(part1Solution.memBank);
    return Integer.toString(s.count);
  }

  static Solution parseInputAndSolve(String input) {
    int[] memBank = parseMemBank(input);

    final Set<String> seenDistributions = new HashSet<>();
    while (seenDistributions.add(bankToString(memBank))) {
      distribute(memBank);
    }
    return new Solution(seenDistributions.size(), bankToString(memBank));
  }

  private static int[] parseMemBank(String input) {
    String[] split = input.split("\\s+");
    int[] memBank = new int[split.length];
    for (int i = 0; i < split.length; i++) {
      memBank[i] = Integer.parseInt(split[i]);
    }
    return memBank;
  }

  /**
   * This is the algorithm for the day. Distributing the value evenly means, that the floor of the
   * division {@code value/#elements} is added all elements on the bank and all the following rest
   * ({@code value%#elements}) elements are incremented by one as well.
   *
   * @param memBank the memory bank, it will be modified.
   */
  static void distribute(int[] memBank) {
    final int index = largestIndex(memBank);
    final int value = memBank[index];
    final int length = memBank.length;

    final int floor = value / length;
    final int rest = value % length;

    memBank[index] = 0;

    for (int i = 1; i <= length; i++) {
      int pos = (index + i) % length;

      if (i <= rest) {
        memBank[pos] += floor + 1;
      } else {
        memBank[pos] += floor;
      }
    }
  }

  private static int largestIndex(int[] memBank) {
    int maxI = 0;
    for (int i = 1; i < memBank.length; i++) {
      if (memBank[i] > memBank[maxI]) {
        maxI = i;
      }
    }
    return maxI;
  }

  private static String bankToString(int[] memBank) {
    return IntStream.of(memBank).mapToObj(Integer::toString).collect(Collectors.joining(" "));
  }

  static class Solution {

    final int count;
    final String memBank;

    Solution(int count, String memBank) {
      this.count = count;
      this.memBank = memBank;
    }
  }
}
