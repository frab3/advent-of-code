package net.bassmann.adventofcode.year2017.day16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 16: Permutation Promenade.
 *
 * <p>You come upon a very unusual sight; a group of programs here appear to be <a
 * href="https://www.youtube.com/watch?v=lyZQPjUT5B4&amp;t=53">dancing</a>.
 *
 * <p>There are sixteen programs in total, named <code>a</code> through <code>p</code>. They start
 * by standing in a <span title="This is called a 'newline'.">line</span>: <code>a</code> stands in
 * position <code>0</code>, <code>b</code> stands in position <code>1</code>, and so on until <code>
 * p</code>, which stands in position <code>15</code>.
 *
 * <p>The programs' <em>dance</em> consists of a sequence of <em>dance moves</em>:
 *
 * <ul>
 *   <li><em>Spin</em>, written <code>sX</code>, makes <code>X</code> programs move from the end to
 *       the front, but maintain their order otherwise. (For example, <code>s3</code> on <code>abcde
 *       </code> produces <code>cdeab</code>).
 *   <li><em>Exchange</em>, written <code>xA/B</code>, makes the programs at positions <code>A
 *       </code> and <code>B</code> swap places.
 *   <li><em>Partner</em>, written <code>pA/B</code>, makes the programs named <code>A</code> and
 *       <code>B</code> swap places.
 * </ul>
 *
 * <p>For example, with only five programs standing in a line (<code>abcde</code>), they could do
 * the following dance:
 *
 * <ul>
 *   <li><code>s1</code>, a spin of size <code>1</code>: <code>eabcd</code>.
 *   <li><code>x3/4</code>, swapping the last two programs: <code>eabdc</code>.
 *   <li><code>pe/b</code>, swapping programs <code>e</code> and <code>b</code>: <code>baedc</code>.
 * </ul>
 *
 * <p>After finishing their dance, the programs end up in order <code>baedc</code>.
 *
 * <p>
 *
 * <h2>Part One</h2>
 *
 * <p>You watch the dance for a while and record their dance moves (your puzzle input). <em>In what
 * order are the programs standing</em> after their dance?
 *
 * <h2>Part Two</h2>
 *
 * Now that you're starting to get a feel for the dance moves, you turn your attention to <em>the
 * dance as a whole</em>.
 *
 * <p>Keeping the positions they ended up in from their previous dance, the programs perform it
 * again and again: including the first dance, a total of <em>one billion</em> (<code>1000000000
 * </code>) times.
 *
 * <p>In the example above, their second dance would <em>begin</em> with the order <code>baedc
 * </code>, and use the same dance moves:
 *
 * <ul>
 *   <li><code>s1</code>, a spin of size <code>1</code>: <code>cbaed</code>.
 *   <li><code>x3/4</code>, swapping the last two programs: <code>cbade</code>.
 *   <li><code>pe/b</code>, swapping programs <code>e</code> and <code>b</code>: <code>ceadb</code>.
 * </ul>
 *
 * <p><em>In what order are the programs standing</em> after their billion dances?
 */
public class Year2017Day16 extends AbstractDay {

  private Solution solution;

  public Year2017Day16() {
    super(2017, 16);
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
      String input = getRiddleInput().firstLine();
      solution = new Solution(dance(input, 16, 1), dance(input, 16, 1_000_000_000));
    }
    return solution;
  }

  /**
   * Lets the programs dance, according to the input. For part two, that dance should be repeated a
   * number of times. The dance pattern will lead to repeats, so I cache a the program oder after
   * doing one dance. So that when I encounter a start configuration I can skip to the end.
   *
   * @param input comma separated list of dance moves.
   * @param programsLength how many programs there are for the dance.
   * @param repeats how often the dance should be repeated.
   * @return the order of programs after the dance.
   */
  static String dance(String input, int programsLength, long repeats) {
    final List<DanceMove> danceMoves = parseDanceMoves(input);

    char[] programs = generatePrograms(programsLength);

    Map<String, String> seen = new HashMap<>();
    boolean firstTime = true;

    for (long l = 0; l < repeats; l++) {
      final String before = String.valueOf(programs);
      if (seen.containsKey(before)) {
        if (firstTime) {
          long rest = repeats % seen.size();
          // skip forward, based on how big the cycle is.
          l = repeats - rest;
          firstTime = false;
        }
        programs = seen.get(before).toCharArray();
      } else {
        for (DanceMove move : danceMoves) {
          move.doMove(programs);
        }
        seen.put(before, String.valueOf(programs));
      }
    }
    return String.valueOf(programs);
  }

  /**
   * Parsing the dance move commands into objects, that will apply the actual move on any given
   * input array of programs. This has the benefit, that input is only parsed once before all
   * iterations and not while iterating.
   *
   * @param input a comma separated list of dance moves.
   * @return a list of dance move objects, that can be applied to a program array.
   */
  private static List<DanceMove> parseDanceMoves(String input) {
    return Arrays.stream(input.split(",")).map(DanceMove::fromString).collect(Collectors.toList());
  }

  static char[] generatePrograms(int length) {
    char[] programs = new char[length];
    final int a = (int) 'a';
    for (int i = 0; i < length; i++) {
      programs[i] = (char) (a + i);
    }
    return programs;
  }
}
