package net.bassmann.adventofcode.year2015.day11;

import static net.bassmann.adventofcode.year2015.day11.PasswordIncrementor.increment;
import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.containsStraight;
import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.containsTwoNonOverlappingPairs;
import static net.bassmann.adventofcode.year2015.day11.PasswordValidator.indexOfForbiddenChar;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 11: Corporate Policy
 *
 * <p>Santa's previous password expired, and he needs help choosing a new one.
 *
 * <p>To help him remember his new password after the old one expires, Santa has devised a method of
 * coming up with a password based on the previous one. Corporate policy dictates that passwords
 * must be exactly eight lowercase letters (for security reasons), so he finds his new password by
 * incrementing his old password string repeatedly until it is valid.
 *
 * <p>Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so on. Increase the
 * rightmost letter one step; if it was z, it wraps around to a, and repeat with the next letter to
 * the left until one doesn't wrap around.
 *
 * <p>Unfortunately for Santa, a new Security-Elf recently started, and he has imposed some
 * additional password requirements:
 *
 * <ul>
 *   <li>Passwords must include one increasing straight of at least three letters, like abc, bcd,
 *       cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
 *   <li>Passwords may not contain the letters i, o, or l, as these letters can be mistaken for
 *       other characters and are therefore confusing.
 *   <li>Passwords must contain at least two different, non-overlapping pairs of letters, like aa,
 *       bb, or zz.
 * </ul>
 *
 * For example:
 *
 * <ul>
 *   <li>hijklmmn meets the first requirement (because it contains the straight hij) but fails the
 *       second requirement requirement (because it contains i and l).
 *   <li>abbceffg meets the third requirement (because it repeats bb and ff) but fails the first
 *       requirement.
 *   <li>abbcegjk fails the third requirement, because it only has one double letter (bb).
 *   <li>The next password after abcdefgh is abcdffaa.
 *   <li>The next password after ghijklmn is ghjaabcc, because you eventually skip all the passwords
 *       that start with ghi..., since i is not allowed.
 * </ul>
 *
 * <h2>Part One</h2>
 *
 * Given Santa's current password (your puzzle input), what should his next password be?
 *
 * <h2>Part Two</h2>
 *
 * Santa's password expired again. What's the next one?
 */
public class Year2015Day11 extends AbstractDay {

  public Year2015Day11() {
    super(2015, 11);
  }

  @Override
  public String solvePart1() {
    return nextPassword(getRiddleInput().firstLine());
  }

  @Override
  public String solvePart2() {
    return nextPassword(increment(solvePart1()));
  }

  static String nextPassword(String password) {
    String next = password;
    while (true) {
      // if we find a forbidden char, we can skip a group of passwords, which all contains that
      int index = indexOfForbiddenChar(next);
      if (index > -1) {
        next = increment(next, index);
        continue;
      }
      if (containsStraight(next) && containsTwoNonOverlappingPairs(next)) {
        break;
      }
      next = increment(next);
    }

    return next;
  }
}
