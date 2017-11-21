package net.bassmann.adventofcode.year2015.day12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 12: JSAbacusFramework.io.
 *
 * <p>Santa's Accounting-Elves need help balancing the books after a recent order. Unfortunately,
 * their accounting software uses a peculiar storage format. That's where you come in.
 *
 * <p>They have a JSON document which contains a variety of things: arrays ([1,2,3]), objects
 * ({"a":1, "b":2}), numbers, and strings. Your first job is to simply find all of the numbers
 * throughout the document and add them together.
 *
 * <p>For example:
 *
 * <ul>
 *   <li>[1,2,3] and {"a":2,"b":4} both have a sum of 6.
 *   <li>[[[3]]] and {"a":{"b":4},"c":-1} both have a sum of 3.
 *   <li>{"a":[-1,1]} and [-1,{"a":1}] both have a sum of 0.
 *   <li>[] and {} both have a sum of 0.
 * </ul>
 *
 * You will not encounter any strings containing numbers.
 *
 * <h2>Part One</h2>
 *
 * What is the sum of all numbers in the document?
 *
 * <h2>Part Two</h2>
 *
 * Uh oh - the Accounting-Elves have realized that they double-counted everything red.
 *
 * <p>Ignore any object (and all of its children) which has any property with the value "red". Do
 * this only for objects ({...}), not arrays ([...]).
 *
 * <ul>
 *   <li>[1,2,3] still has a sum of 6.
 *   <li>[1,{"c":"red","b":2},3] now has a sum of 4, because the middle object is ignored.
 *   <li>{"d":"red","e":[1,2,3,4],"f":5} now has a sum of 0, because the entire structure is
 *       ignored.
 *   <li>[1,"red",5] has a sum of 6, because "red" in an array has no effect.
 * </ul>
 */
public class Year2015Day12 extends AbstractDay {

  public Year2015Day12() {
    super(2015, 12);
  }

  @Override
  public String solvePart1() {
    int sum = sumAllNumbersInString(getRiddleInput().firstLine());
    return Integer.toString(sum);
  }

  @Override
  public String solvePart2() {
    int sum = sumAllNumbersIgnoringRed(getRiddleInput().firstLine());
    return Integer.toString(sum);
  }

  /**
   * Solution for part one is straight forward: Just extract all numbers from the input string and
   * sum them up. I used a regex here, because it is much shorter then parsing each character
   * manually.
   */
  static int sumAllNumbersInString(String input) {
    int sum = 0;
    Matcher m = Pattern.compile("-?\\d+").matcher(input);
    while (m.find()) {
      final String r = m.group();
      sum += Integer.parseInt(r);
    }
    return sum;
  }

  static int sumAllNumbersIgnoringRed(String input) {
    RedSum r = new RedSum(input);
    return r.parseAndSum();
  }
}
