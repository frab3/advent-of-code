package net.bassmann.adventofcode.year2017.day09;

import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 9: Stream Processing.
 *
 * <p>A large stream blocks your path. According to the locals, it's not safe to cross the stream at
 * the moment because it's full of garbage. You look down at the stream; rather than water, you
 * discover that it's a stream of characters.
 *
 * <p>You sit for a while and record part of the stream (your puzzle input). The characters
 * represent groups - sequences that begin with { and end with }. Within a group, there are zero or
 * more other things, separated by commas: either another group or garbage. Since groups can contain
 * other groups, a } only closes the most-recently-opened unclosed group - that is, they are
 * nestable. Your puzzle input represents a single, large group which itself contains many smaller
 * ones.
 *
 * <p>Sometimes, instead of a group, you will find garbage. Garbage begins with &lt; and ends with
 * &gt;. Between those angle brackets, almost any character can appear, including { and }. Within
 * garbage, &lt; has no special meaning.
 *
 * <p>In a futile attempt to clean up the garbage, some program has canceled some of the characters
 * within it using !: inside garbage, any character that comes after ! should be ignored, including
 * &lt;, &gt;, and even another !.
 *
 * <p>You don't see any characters that deviate from these rules. Outside garbage, you only find
 * well-formed groups, and garbage always terminates according to the rules above.
 *
 * <p>Here are some self-contained pieces of garbage:
 *
 * <pre>
 * &lt;&gt;, empty garbage.
 * &lt;random characters&gt;, garbage containing random characters.
 * &lt;&lt;&lt;&lt;&gt;, because the extra &lt; are ignored.
 * &lt;{!&gt;}&gt;, because the first &gt; is canceled.
 * &lt;!!&gt;, because the second ! is canceled, allowing the &gt; to terminate the garbage.
 * &lt;!!!&gt;&gt;, because the second ! and the first &gt; are canceled.
 * &lt;{o"i!a,&lt;{i&lt;a&gt;, which ends at the first &gt;.
 * </pre>
 *
 * Here are some examples of whole streams and the number of groups they contain:
 *
 * <pre>
 * {}, 1 group.
 * {{{}}}, 3 groups.
 * {{},{}}, also 3 groups.
 * {{{},{},{{}}}}, 6 groups.
 * {&lt;{},{},{{}}&gt;}, 1 group (which itself contains garbage).
 * {&lt;a&gt;,&lt;a&gt;,&lt;a&gt;,&lt;a&gt;}, 1 group.
 * {{&lt;a&gt;},{&lt;a&gt;},{&lt;a&gt;},{&lt;a&gt;}}, 5 groups.
 * {{&lt;!&gt;},{&lt;!&gt;},{&lt;!&gt;},{&lt;a&gt;}}, 2 groups (since all but the last &gt; are canceled).
 * </pre>
 *
 * Your goal is to find the total score for all groups in your input. Each group is assigned a score
 * which is one more than the score of the group that immediately contains it. (The outermost group
 * gets a score of 1.)
 *
 * <pre>
 * {}, score of 1.
 * {{{}}}, score of 1 + 2 + 3 = 6.
 * {{},{}}, score of 1 + 2 + 2 = 5.
 * {{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.
 * {&lt;a&gt;,&lt;a&gt;,&lt;a&gt;,&lt;a&gt;}, score of 1.
 * {{&lt;ab&gt;},{&lt;ab&gt;},{&lt;ab&gt;},{&lt;ab&gt;}}, score of 1 + 2 + 2 + 2 + 2 = 9.
 * {{&lt;!!&gt;},{&lt;!!&gt;},{&lt;!!&gt;},{&lt;!!&gt;}}, score of 1 + 2 + 2 + 2 + 2 = 9.
 * {{&lt;a!&gt;},{&lt;a!&gt;},{&lt;a!&gt;},{&lt;ab&gt;}}, score of 1 + 2 = 3.
 * </pre>
 *
 * <h2>Part One</h2>
 *
 * What is the total score for all groups in your input?
 *
 * <h2>Part Two</h2>
 *
 * Now, you're ready to remove the garbage.
 *
 * <p>To prove you've removed it, you need to count all of the characters within the garbage. The
 * leading and trailing &lt; and &gt; don't count, nor do any canceled characters or the ! doing the
 * canceling.
 *
 * <pre>
 * &lt;&gt;, 0 characters.
 * &lt;random characters&gt;, 17 characters.
 * &lt;&lt;&lt;&lt;&gt;, 3 characters.
 * &lt;{!&gt;}&gt;, 2 characters.
 * &lt;!!&gt;, 0 characters.
 * &lt;!!!&gt;&gt;, 0 characters.
 * &lt;{o"i!a,&lt;{i&lt;a&gt;, 10 characters.
 * </pre>
 *
 * How many non-canceled characters are within the garbage in your puzzle input?
 */
public class Year2017Day09 extends AbstractDay {

  private Solution solution = null;

  public Year2017Day09() {
    super(2017, 9);
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
      solution = processInput(getRiddleInput().firstLine());
    }
    return solution;
  }

  /**
   * The solution for today just iterates over the input. It has an internal state, that is modified
   * according to today's puzzles description.
   */
  static Solution processInput(String input) {

    int pos = 0;
    boolean isGarbage = false;
    int score = 0;
    int scoreSum = 0;
    int garbageCount = 0;

    while (pos < input.length()) {
      final char c = input.charAt(pos);

      if (isGarbage && c != '!' && c != '>') {
        garbageCount++;
      }

      switch (c) {
        case '!':
          pos++;
          break;
        case '<':
          isGarbage = true;
          break;
        case '>':
          isGarbage = false;
          break;
        case '{':
          if (!isGarbage) {
            score++;
          }
          break;
        case '}':
          if (!isGarbage) {
            scoreSum += score--;
          }
          break;
        default:
          // skip all other characters
      }

      pos++;
    }

    return new Solution(scoreSum, garbageCount);
  }
}
