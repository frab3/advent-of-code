package net.bassmann.adventofcode.year2015.day01;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 1: Not Quite Lisp.
 *
 * <p>Santa is trying to deliver presents in a large apartment building, but he can't find the right
 * floor - the directions he got are a little confusing. He starts on the ground floor (floor 0) and
 * then follows the instructions one character at a time.
 *
 * <p>An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ),
 * means he should go down one floor.
 *
 * <p>The apartment building is very tall, and the basement is very deep; he will never find the top
 * or bottom floors.
 *
 * <h2>Part One</h2>
 *
 * To what floor do the instructions take Santa?
 *
 * <h2>Part Two</h2>
 *
 * Now, given the same instructions, find the position of the first character that causes him to
 * enter the basement (floor -1). The first character in the instructions has position 1, the second
 * character has position 2, and so on.
 *
 * <p>What is the position of the character that causes Santa to first enter the basement?
 */
public class Year2015Day01 extends AbstractDay {

  public Year2015Day01() {
    super(2015, 1);
  }

  @Override
  public String solvePart1() {
    long finalFloor = getFinalFloor(getRiddleInput().firstLine());
    return Long.toString(finalFloor);
  }

  @Override
  public String solvePart2() {
    int firstTimeIntoBasement = getFirstTimeIntoBasement(getRiddleInput().firstLine());
    return Integer.toString(firstTimeIntoBasement);
  }

  /**
   * This is the algorithm to solve part 1: The idea is to translate the instructions to either 1 or
   * -1 and then sum them up.
   */
  static long getFinalFloor(String input) {
    return input.chars().map(c -> parseInstruction((char) c)).sum();
  }

  /**
   * This is the algorithm to part 2: We iterate over the input, and just return the index when the
   * current floor (=sum) is -1 the first time.
   */
  static int getFirstTimeIntoBasement(String input) {
    int currentFloor = 0;
    for (int i = 0; i < input.length(); i++) {
      currentFloor += parseInstruction(input.charAt(i));
      if (currentFloor == -1) {
        return i + 1;
      }
    }
    return -1;
  }

  private static int parseInstruction(char instruction) {
    switch (instruction) {
      case '(':
        return 1;
      case ')':
        return -1;
      default:
        throw new IllegalArgumentException("Not a valid instruction");
    }
  }
}
