package net.bassmann.adventofcode.year2017.day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 8: I Heard You Like Registers.
 *
 * <p>You receive a signal directly from the CPU. Because of your recent assistance with jump
 * instructions, it would like you to compute the result of a series of unusual register
 * instructions.
 *
 * <p>Each instruction consists of several parts: the register to modify, whether to increase or
 * decrease that register's value, the amount by which to increase or decrease it, and a condition.
 * If the condition fails, skip the instruction without modifying the register. The registers all
 * start at 0. The instructions look like this:
 *
 * <pre>
 * b inc 5 if a &gt; 1
 * a inc 1 if b &lt; 5
 * c dec -10 if a &gt;= 1
 * c inc -20 if c == 10
 * </pre>
 *
 * These instructions would be processed as follows:
 *
 * <ul>
 * <li>Because a starts at 0, it is not greater than 1, and so b is not modified.
 * <li>a is increased by 1 (to 1) because b is less than 5 (it is 0).
 * <li>c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
 * <li>c is increased by -20 (to -10) because c is equal to 10.
 * </ul>
 *
 * After this process, the largest value in any register is 1.
 *
 * <p>You might also encounter &lt;= (less than or equal to) or != (not equal to). However, the CPU
 * doesn't have the bandwidth to tell you what all the registers are named, and leaves that to you
 * to determine.
 *
 * <h2>Part One</h2>
 *
 * What is the largest value in any register after completing the instructions in your puzzle input?
 *
 * <h2>Part Two</h2>
 *
 * To be safe, the CPU also needs to know the highest value held in any register during this process
 * so that it can decide how much memory to allocate to these operations. For example, in the above
 * instructions, the highest value ever held was 10 (in register c after the third instruction was
 * evaluated).
 */
public class Year2017Day08 extends AbstractDay {

  private Solution solution = null;

  public Year2017Day08() {
    super(2017, 8);
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
      solution = processRegisters(getRiddleInput().asList());
    }
    return solution;
  }

  /**
   * For today the solution is pretty straight forward: Just parse each line of the input file,
   * check the condition stated and if true, apply the operation to get an new value (keep the max
   * for part 2) and save it back to the register.
   */
  private Solution processRegisters(List<String> input) {
    Map<String, Integer> registers = new HashMap<>();
    int max = 0;

    for (String line : input) {
      String[] split = line.split(" ");
      String register = split[0];
      String op = split[1];
      int change = Integer.parseInt(split[2]);

      String compRegister = split[4];
      String comparator = split[5];
      int value = Integer.parseInt(split[6]);

      if (!conditionIsTrue(registers, compRegister, comparator, value)) {
        continue;
      }

      int v = getNewValue(registers, register, op, change);
      registers.put(register, v);

      if (v > max) {
        max = v;
      }
    }

    return new Solution(
        registers.values().stream().mapToInt(Integer::intValue).max().orElse(0), max);
  }

  private static boolean conditionIsTrue(
      Map<String, Integer> registers, String field, String operator, int value) {

    int register = registers.getOrDefault(field, 0);

    switch (operator) {
      case ">":
        return register > value;
      case "<":
        return register < value;
      case ">=":
        return register >= value;
      case "<=":
        return register <= value;
      case "!=":
        return register != value;
      case "==":
        return register == value;
      default:
        throw new IllegalArgumentException("Unknown op: " + operator);
    }
  }

  private static int getNewValue(
      Map<String, Integer> registers, String field, String op, int value) {
    int register = registers.getOrDefault(field, 0);
    switch (op) {
      case "dec":
        return register - value;
      case "inc":
        return register + value;
      default:
        throw new IllegalArgumentException("Unknown op: " + op);
    }
  }

}
