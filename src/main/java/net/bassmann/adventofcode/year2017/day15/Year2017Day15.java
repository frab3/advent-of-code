package net.bassmann.adventofcode.year2017.day15;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 15: Dueling Generators.
 *
 * <p>Here, you encounter a pair of dueling <span title="I guess they *are* a little banjo-shaped.
 * Why do you ask?">generators</span>. The generators, called <em>generator A</em> and <em>generator
 * B</em>, are trying to agree on a sequence of numbers. However, one of them is malfunctioning, and
 * so the sequences don't always match.
 *
 * <p>As they do this, a <em>judge</em> waits for each of them to generate its next value, compares
 * the lowest 16 bits of both values, and keeps track of the number of times those parts of the
 * values match.
 *
 * <p>The generators both work on the same principle. To create its next value, a generator will
 * take the previous value it produced, multiply it by a <em>factor</em> (generator A uses <code>
 * 16807</code>; generator B uses <code>48271</code>), and then keep the remainder of dividing that
 * resulting product by <code>2147483647</code>. That final remainder is the value it produces next.
 *
 * <p>To calculate each generator's first value, it instead uses a specific starting value as its
 * "previous value" (as listed in your puzzle input).
 *
 * <p>For example, suppose that for starting values, generator A uses <code>65</code>, while
 * generator B uses <code>8921</code>. Then, the first five pairs of generated values are:
 *
 * <pre>
 * --Gen. A--  --Gen. B--
 *    1092455   430625591
 * 1181022009  1233683848
 *  245556042  1431495498
 * 1744312007   137874439
 * 1352636452   285222916
 * </pre>
 *
 * <p>In binary, these pairs are (with generator A's value first in each pair):
 *
 * <pre>
 * 00000000000100001010101101100111
 * 00011001101010101101001100110111
 *
 * 01000110011001001111011100111001
 * 01001001100010001000010110001000
 *
 * 00001110101000101110001101001010
 * 01010101010100101110001101001010
 *
 * 01100111111110000001011011000111
 * 00001000001101111100110000000111
 *
 * 01010000100111111001100000100100
 * 00010001000000000010100000000100
 * </pre>
 *
 * <p>Here, you can see that the lowest (here, rightmost) 16 bits of the third value match: <code>
 * 1110001101001010</code>. Because of this one match, after processing these five pairs, the judge
 * would have added only <code>1</code> to its total.
 *
 * <p>To get a significant sample, the judge would like to consider <em>40 million</em> pairs. (In
 * the example above, the judge would eventually find a total of <code>588</code> pairs that match
 * in their lowest 16 bits.)
 *
 * <h2>Part One</h2>
 *
 * <p>After 40 million pairs, <em>what is the judge's final count</em>?
 *
 * <h2>Part Two</h2>
 *
 * <p>In the interest of trying to align a little better, the generators get more picky about the
 * numbers they actually give to the judge.
 *
 * <p>They still generate values in the same way, but now they only hand a value to the judge when
 * it meets their <em>criteria</em>:
 *
 * <ul>
 *   <li>Generator A looks for values that are multiples of <code><em>4</em></code>.
 *   <li>Generator B looks for values that are multiples of <code><em>8</em></code>.
 * </ul>
 *
 * <p>Each generator functions completely <em>independently</em>: they both go through values
 * entirely on their own, only occasionally handing an acceptable value to the judge, and otherwise
 * working through the same sequence of values as before until they find one.
 *
 * <p>The judge still waits for each generator to provide it with a value before comparing them
 * (using the same comparison method as before). It keeps track of the order it receives values; the
 * first values from each generator are compared, then the second values from each generator, then
 * the third values, and so on.
 *
 * <p>Using the example starting values given above, the generators now produce the following first
 * five values each:
 *
 * <pre>
 * --Gen. A--  --Gen. B--
 * 1352636452  1233683848
 * 1992081072   862516352
 *  530830436  1159784568
 * 1980017072  1616057672
 *  740335192   412269392
 * </pre>
 *
 * <p>These values have the following corresponding binary values:
 *
 * <pre>
 * 01010000100111111001100000100100
 * 01001001100010001000010110001000
 *
 * 01110110101111001011111010110000
 * 00110011011010001111010010000000
 *
 * 00011111101000111101010001100100
 * 01000101001000001110100001111000
 *
 * 01110110000001001010100110110000
 * 01100000010100110001010101001000
 *
 * 00101100001000001001111001011000
 * 00011000100100101011101101010000
 * </pre>
 *
 * <p>Unfortunately, even though this change makes more bits similar on average, none of these
 * values' lowest 16 bits match. Now, it's not until the 1056th pair that the judge finds the first
 * match:
 *
 * <pre>
 * --Gen. A--  --Gen. B--
 * 1023762912   896885216
 *
 * 00111101000001010110000111100000
 * 00110101011101010110000111100000
 * </pre>
 *
 * <p>This change makes the generators much slower, and the judge is getting impatient; it is now
 * only willing to consider <em>5 million</em> pairs. (Using the values from the example above,
 * after five million pairs, the judge would eventually find a total of <code>309</code> pairs that
 * match in their lowest 16 bits.)
 *
 * <p>After 5 million pairs, but using this new generator logic, <em>what is the judge's final
 * count</em>?
 */
public class Year2017Day15 extends AbstractDay {

  static final long FACTOR_A = 16807;
  static final long DIVISOR_A = 4;
  static final long FACTOR_B = 48271;
  static final long DIVISOR_B = 8;
  private static final long DIVISOR = 2147483647;
  private static final long BIT_MASK = 65535;

  private Solution solution;

  public Year2017Day15() {
    super(2017, 15);
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
      List<String> input = getRiddleInput().asList();
      long startA = Long.parseLong(input.get(0).split(" ")[4]);
      long startB = Long.parseLong(input.get(1).split(" ")[4]);
      solution = new Solution(solve1(startA, startB), solve2(startA, startB));
    }
    return solution;
  }

  static long solve1(long startA, long startB) {
    long count = 0;
    long a = startA;
    long b = startB;
    for (long i = 0; i < 40_000_000; i++) {
      a = generate(a, FACTOR_A);
      b = generate(b, FACTOR_B);
      if (last16BitMatch(a, b)) {
        count++;
      }
    }
    return count;
  }

  static long solve2(long startA, long startB) {
    long count = 0;
    long a = startA;
    long b = startB;
    for (long i = 0; i < 5_000_000; i++) {
      a = generate(a, FACTOR_A, DIVISOR_A);
      b = generate(b, FACTOR_B, DIVISOR_B);
      if (last16BitMatch(a, b)) {
        count++;
      }
    }
    return count;
  }

  static long generate(long start, long factor) {
    return (start * factor) % DIVISOR;
  }

  static long generate(long start, long factor, long divisor) {
    long val = start;
    do {
      val = (val * factor) % DIVISOR;
    } while (val % divisor != 0);
    return val;
  }

  static boolean last16BitMatch(long l, long m) {
    return (l & BIT_MASK) == (m & BIT_MASK);
  }
}
