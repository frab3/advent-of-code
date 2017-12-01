package net.bassmann.adventofcode.year2017.day01;

import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 1: Inverse Captcha.
 *
 * <p>The night before Christmas, one of Santa's Elves calls you in a panic. "The printer's broken!
 * We can't print the Naughty or Nice List!" By the time you make it to sub-basement 17, there are
 * only a few minutes until midnight. "We have a big problem," she says; "there must be almost fifty
 * bugs in this system, but nothing else can print The List. Stand in this square, quick! There's no
 * time to explain; if you can convince them to pay you in stars, you'll be able to--" She pulls a
 * lever and the world goes blurry.
 *
 * <p>When your eyes can focus again, everything seems a lot more pixelated than before. She must
 * have sent you inside the computer! You check the system clock: 25 milliseconds until midnight.
 * With that much time, you should be able to collect all fifty stars by December 25th.
 *
 * <p>Collect stars by solving puzzles. Two puzzles will be made available on each day millisecond
 * in the advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle
 * grants one star. Good luck!
 *
 * <p>You're standing in a room with "digitization quarantine" written in LEDs along one wall. The
 * only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized
 * Users Allowed."
 *
 * <p>It goes on to explain that you may only leave by solving a captcha to prove you're not a
 * human. Apparently, you only get one millisecond to solve the captcha: too fast for a normal
 * human, but it feels like hours to you.
 *
 * <p>The captcha requires you to review a sequence of digits (your puzzle input) and find the sum
 * of all digits that match the next digit in the list. The list is circular, so the digit after the
 * last digit is the first digit in the list.
 *
 * <p>For example:
 *
 * <ul>
 *   <li>1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and
 *       the third digit (2) matches the fourth digit.
 *   <li>1111 produces 4 because each digit (all 1) matches the next.
 *   <li>1234 produces 0 because no digit matches the next.
 *   <li>91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
 * </ul>
 *
 * <p>What is the solution to your captcha?
 *
 * <h2>Part Two</h2>
 *
 * You notice a progress bar that jumps to 50% completion. Apparently, the door isn't yet satisfied,
 * but it did emit a star as encouragement. The instructions change:
 *
 * <p>Now, instead of considering the next digit, it wants you to consider the digit halfway around
 * the circular list. That is, if your list contains 10 items, only include a digit in your sum if
 * the digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of
 * elements.
 *
 * <p>For example:
 *
 * <ul>
 *   <li>1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items
 *       ahead.
 *   <li>1221 produces 0, because every comparison is between a 1 and a 2.
 *   <li>123425 produces 4, because both 2s match each other, but no other digit has a match.
 *   <li>123123 produces 12.
 *   <li>12131415 produces 4.
 * </ul>
 *
 * <p>What is the solution to your new captcha?
 */
public class Year2017Day01 extends AbstractDay {

  public Year2017Day01() {
    super(2017, 1);
  }

  @Override
  public String solvePart1() {
    int captcha = captcha(getRiddleInput().firstLine(), 1);
    return Integer.toString(captcha);
  }

  @Override
  public String solvePart2() {
    final String input = getRiddleInput().firstLine();
    int captcha = captcha(input, input.length() / 2);
    return Integer.toString(captcha);
  }

  /**
   * Returns the next position with the given offset in a circular list.
   *
   * @param pos the current position.
   * @param length the length of the circular list.
   * @param steps the number of steps to advance in the circular list (the offset).
   * @return the next position, after applying the offset.
   */
  private static int getNextPosition(int pos, int length, int steps) {
    return (pos + steps) % length;
  }

  /** Returns {@code true} if two chars in a given input are the same. */
  private static boolean isPair(String input, int pos, int otherPos) {
    return input.charAt(pos) == input.charAt(otherPos);
  }

  /**
   * Solution for both part one and two. The only difference is the offset to get the next character
   * in the input, which is a circular list.
   *
   * @param input the input, a circular list with even length.
   * @param offset the offset of how to get the next character when solving the captcha.
   * @return the solution of the captcha.
   */
  static int captcha(String input, int offset) {
    final int length = input.length();
    return IntStream.range(0, input.length())
        .filter(i -> isPair(input, i, getNextPosition(i, length, offset)))
        .map(i -> Integer.parseInt(input, i, i + 1, 10))
        .sum();
  }
}
