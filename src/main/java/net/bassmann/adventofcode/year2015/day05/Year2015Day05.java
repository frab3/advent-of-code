package net.bassmann.adventofcode.year2015.day05;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 5: Doesn't He Have Intern-Elves For This?
 *
 * <p>Santa needs help figuring out which strings in his text file are naughty or nice.
 *
 * <p>A nice string is one with all of the following properties:
 *
 * <ul>
 *   <li>It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 *   <li>It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or
 *       aabbccdd (aa, bb, cc, or dd).
 *   <li>It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the
 *       other requirements.
 * </ul>
 *
 * <h2>Part One</h2>
 *
 * How many strings are nice?
 *
 * <h2>Part Two</h2>
 *
 * Realizing the error of his ways, Santa has switched to a better model of determining whether a
 * string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.
 *
 * <p>Now, a nice string is one with all of the following properties:
 *
 * <ul>
 *   <li>It contains a pair of any two letters that appears at least twice in the string without
 *       overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 *   <li>It contains at least one letter which repeats with exactly one letter between them, like
 *       xyx, abcdefeghi (efe), or even aaa.
 * </ul>
 */
public class Year2015Day05 extends AbstractDay {

  public Year2015Day05() {
    super(2015, 5);
  }

  @Override
  public String solvePart1() {
    StringChecker checker = new StringChecker();
    long niceStringCount = getRiddleInput().lines().filter(checker::isNice).count();
    return Long.toString(niceStringCount);
  }

  @Override
  public String solvePart2() {
    ImprovedStringChecker checker = new ImprovedStringChecker();
    long niceStringCount = getRiddleInput().lines().filter(checker::isNice).count();
    return Long.toString(niceStringCount);
  }
}
