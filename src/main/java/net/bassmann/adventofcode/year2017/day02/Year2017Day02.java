package net.bassmann.adventofcode.year2017.day02;

import java.util.regex.Pattern;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 2: Corruption Checksum.
 *
 * <p>As you walk through the door, a glowing humanoid shape yells in your direction. "You there!
 * Your state appears to be idle. Come help us repair the corruption in this spreadsheet - if we
 * take another millisecond, we'll have to display an hourglass cursor!"
 *
 * <p>The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery
 * process is on the right track, they need you to calculate the spreadsheet's checksum. For each
 * row, determine the difference between the largest value and the smallest value; the checksum is
 * the sum of all of these differences.
 *
 * <p>For example, given the following spreadsheet:
 *
 * <pre>
 * 5 1 9 5
 * 7 5 3
 * 2 4 6 8
 * </pre>
 *
 * <ul>
 * <li>The first row's largest and smallest values are 9 and 1, and their difference is 8.
 * <li>The second row's largest and smallest values are 7 and 3, and their difference is 4.
 * <li>The third row's difference is 6.
 * </ul>
 *
 * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 *
 * <h2>Part One</h2>
 *
 * What is the checksum for the spreadsheet in your puzzle input?
 *
 * <h2>Part Two</h2>
 *
 * "Great work; looks like we're on the right track after all. Here's a star for your effort."
 * However, the program seems a little worried. Can programs be worried?
 *
 * <p>"Based on what we're seeing, it looks like all the User wanted is some information about the
 * evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind
 * of calculation - most of us specialize in bitwise operations."
 *
 * <p>It sounds like the goal is to find the only two numbers in each row where one evenly divides
 * the other - that is, where the result of the division operation is a whole number. They would
 * like you to find those numbers on each line, divide them, and add up each line's result.
 *
 * <p>For example, given the following spreadsheet:
 *
 * <pre>
 * 5 9 2 8
 * 9 4 7 3
 * 3 8 6 5
 * </pre>
 *
 * <ul>
 * <li>In the first row, the only two numbers that evenly divide are 8 and 2; the result of this
 * division is 4.
 * <li>In the second row, the two numbers are 9 and 3; the result is 3.
 * <li>In the third row, the result is 2.
 * </ul>
 *
 * In this example, the sum of the results would be 4 + 3 + 2 = 9.
 *
 * <p>What is the sum of each row's result in your puzzle input?
 */
public class Year2017Day02 extends AbstractDay {

  private static final Pattern WHITESPACE = Pattern.compile("\\s");

  public Year2017Day02() {
    super(2017, 2);
  }

  @Override
  public String solvePart1() {
    final int sum = getRiddleInput().lines().mapToInt(Year2017Day02::checksum).sum();
    return Integer.toString(sum);
  }

  @Override
  public String solvePart2() {
    final int sum = getRiddleInput().lines().mapToInt(Year2017Day02::divisible).sum();
    return Integer.toString(sum);
  }

  private static IntStream splitToIntStream(String line) {
    return WHITESPACE.splitAsStream(line).mapToInt(Integer::parseInt);
  }

  /**
   * Solution for part one: returns the difference between the maximum number and the minimum number
   * of the input.
   */
  static int checksum(String input) {
    int max = splitToIntStream(input).max().orElse(0);
    int min = splitToIntStream(input).min().orElse(0);
    return max - min;
  }

  /**
   * Solution for part two: To find the divisible result I first sort the input (min to max) and
   * then check if any number can divide the rest of the numbers evenly.
   */
  static int divisible(String input) {
    int[] values = splitToIntStream(input).sorted().toArray();

    for (int divisorIndex = 0; divisorIndex < values.length; divisorIndex++) {
      for (int dividendIndex = divisorIndex + 1; dividendIndex < values.length; dividendIndex++) {
        final int divisor = values[divisorIndex];
        final int dividend = values[dividendIndex];
        if (divisor % dividend == 0) {
          return divisor / dividend;
        }
      }
    }

    return 0;
  }
}
