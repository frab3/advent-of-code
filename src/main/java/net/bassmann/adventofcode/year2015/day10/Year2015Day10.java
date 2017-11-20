package net.bassmann.adventofcode.year2015.day10;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 10: Elves Look, Elves Say.
 *
 * <p>Today, the Elves are playing a game called look-and-say. They take turns making sequences by
 * reading aloud the previous sequence and using that reading as the next sequence. For example, 211
 * is read as "one two, two ones", which becomes 1221 (1 2, 2 1s).
 *
 * <p>Look-and-say sequences are generated iteratively, using the previous value as input for the
 * next step. For each step, take the previous value, and replace each run of digits (like 111) with
 * the number of digits (3) followed by the digit itself (1).
 *
 * <p>For example:
 *
 * <ul>
 *   <li>1 becomes 11 (1 copy of digit 1).
 *   <li>11 becomes 21 (2 copies of digit 1).
 *   <li>21 becomes 1211 (one 2 followed by one 1).
 *   <li>1211 becomes 111221 (one 1, one 2, and two 1s).
 *   <li>111221 becomes 312211 (three 1s, two 2s, and one 1).
 * </ul>
 *
 * <h2>Part One</h2>
 *
 * Starting with the digits in your puzzle input, apply this process 40 times. What is the length of
 * the result?
 *
 * <h2>Part Two</h2>
 *
 * Neat, right? You might also enjoy hearing John Conway talking about this sequence (that's Conway
 * of Conway's Game of Life fame).
 *
 * <p>Now, starting again with the digits in your puzzle input, apply this process 50 times. What is
 * the length of the new result?
 */
public class Year2015Day10 extends AbstractDay {

  public Year2015Day10() {
    super(2015, 10);
  }

  @Override
  public String solvePart1() {
    int length = getLengthAfterIterations(getRiddleInput().firstLine(), 40);
    return Integer.toString(length);
  }

  @Override
  public String solvePart2() {
    int length = getLengthAfterIterations(getRiddleInput().firstLine(), 50);
    return Integer.toString(length);
  }

  int getLengthAfterIterations(String input, int iterations) {
    String current = input;

    for (int i = 1; i <= iterations; i++) {
      current = lookAndSay(current);
    }

    return current.length();
  }

  static String lookAndSay(String input) {
    final StringBuilder output = new StringBuilder();

    int count = 0;
    char take = ' ';
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);

      if (c == take) {
        count++;
      } else {
        if (count > 0) {
          output.append(count).append(take);
        }
        take = c;
        count = 1;
      }
    }
    if (count > 0) {
      output.append(count).append(take);
    }

    return output.toString();
  }
}
