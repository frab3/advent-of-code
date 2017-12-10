package net.bassmann.adventofcode.year2017.day10;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 10: Knot Hash.
 *
 * <p>You come across some programs that are trying to implement a software emulation of a hash
 * based on knot-tying. The hash these programs are implementing isn't very strong, but you decide
 * to help them anyway. You make a mental note to remind the Elves later not to invent their own
 * cryptographic functions.
 *
 * <p>This hash function simulates tying a knot in a circle of string with 256 marks on it. Based on
 * the input to be hashed, the function repeatedly selects a span of string, brings the ends
 * together, and gives the span a half-twist to reverse the order of the marks within it. After
 * doing this many times, the order of the marks is used to build the resulting hash.
 *
 * <pre>
 * #   4--5   pinch   4  5           4   1
 * #  /    \  5,0,1  / \/ \  twist  / \ / \
 * # 3      0  -->  3      0  -->  3   X   0
 * #  \    /         \ /\ /         \ / \ /
 * #   2--1           2  1           2   5
 * </pre>
 *
 * To achieve this, begin with a list of numbers from 0 to 255, a current position which begins at 0
 * (the first element in the list), a skip size (which starts at 0), and a sequence of lengths (your
 * puzzle input). Then, for each length:
 *
 * <ul>
 * <li>Reverse the order of that length of elements in the list, starting with the element at the
 * current position.
 * <li>Move the current position forward by that length plus the skip size.
 * <li>Increase the skip size by one.
 * </ul>
 *
 * The list is circular; if the current position and the length try to reverse elements beyond the
 * end of the list, the operation reverses using as many extra elements as it needs from the front
 * of the list. If the current position moves past the end of the list, it wraps around to the
 * front. Lengths larger than the size of the list are invalid.
 *
 * <p>Here's an example using a smaller list:
 *
 * <p>Suppose we instead only had a circular list containing five elements, 0, 1, 2, 3, 4, and were
 * given input lengths of 3, 4, 1, 5.
 *
 * <ul>
 * <li>The list begins as [0] 1 2 3 4 (where square brackets indicate the current position).
 * <li>The first length, 3, selects ([0] 1 2) 3 4 (where parentheses indicate the sublist to be
 * reversed).
 * <li>After reversing that section (0 1 2 into 2 1 0), we get ([2] 1 0) 3 4.
 * <li>Then, the current position moves forward by the length, 3, plus the skip size, 0: 2 1 0 [3]
 * 4. Finally, the skip size increases to 1.
 * </ul>
 *
 * <ul>
 * <li>The second length, 4, selects a section which wraps: 2 1) 0 ([3] 4.
 * <li>The sublist 3 4 2 1 is reversed to form 1 2 4 3: 4 3) 0 ([1] 2.
 * <li>The current position moves forward by the length plus the skip size, a total of 5, causing
 * it not to move because it wraps around: 4 3 0 [1] 2. The skip size increases to 2.
 * </ul>
 *
 * <ul>
 * <li>The third length, 1, selects a sublist of a single element, and so reversing it has no
 * effect.
 * <li>The current position moves forward by the length (1) plus the skip size (2): 4 [3] 0 1 2.
 * The skip size increases to 3.
 * </ul>
 *
 * <ul>
 * <li>The fourth length, 5, selects every element starting with the second: 4) ([3] 0 1 2.
 * Reversing this sublist (3 0 1 2 4 into 4 2 1 0 3) produces: 3) ([4] 2 1 0.
 * <li>Finally, the current position moves forward by 8: 3 4 2 1 [0]. The skip size increases to
 * 4.
 * </ul>
 *
 * In this example, the first two numbers in the list end up being 3 and 4; to check the process,
 * you can multiply them together to produce 12.
 *
 * <h2>Part One</h2>
 *
 * However, you should instead use the standard list size of 256 (with values 0 to 255) and the
 * sequence of lengths in your puzzle input. Once this process is complete, what is the result of
 * multiplying the first two numbers in the list?
 *
 * <h2>Part Two</h2>
 *
 * The logic you've constructed forms a single round of the Knot Hash algorithm; running the full
 * thing requires many of these rounds. Some input and output processing is also required.
 *
 * <p>First, from now on, your input should be taken not as a list of numbers, but as a string of
 * bytes instead. Unless otherwise specified, convert characters to bytes using their ASCII codes.
 * This will allow you to handle arbitrary ASCII strings, and it also ensures that your input
 * lengths are never larger than 255. For example, if you are given 1,2,3, you should convert it to
 * the ASCII codes for each character: 49,44,50,44,51.
 *
 * <p>Once you have determined the sequence of lengths to use, add the following lengths to the end
 * of the sequence: 17, 31, 73, 47, 23. For example, if you are given 1,2,3, your final sequence of
 * lengths should be 49,44,50,44,51,17,31,73,47,23 (the ASCII codes from the input string combined
 * with the standard length suffix values).
 *
 * <p>Second, instead of merely running one round like you did above, run a total of 64 rounds,
 * using the same length sequence in each round. The current position and skip size should be
 * preserved between rounds. For example, if the previous example was your first round, you would
 * start your second round with the same length sequence (3, 4, 1, 5, 17, 31, 73, 47, 23, now
 * assuming they came from ASCII codes and include the suffix), but start with the previous round's
 * current position (4) and skip size (4).
 *
 * <p>Once the rounds are complete, you will be left with the numbers from 0 to 255 in some order,
 * called the sparse hash. Your next task is to reduce these to a list of only 16 numbers called the
 * dense hash. To do this, use numeric bitwise XOR to combine each consecutive block of 16 numbers
 * in the sparse hash (there are 16 such blocks in a list of 256 numbers). So, the first element in
 * the dense hash is the first sixteen elements of the sparse hash XOR'd together, the second
 * element in the dense hash is the second sixteen elements of the sparse hash XOR'd together, etc.
 *
 * <p>For example, if the first sixteen elements of your sparse hash are as shown below, and the XOR
 * operator is ^, you would calculate the first output number like this:
 *
 * <pre>65 ^ 27 ^ 9 ^ 1 ^ 4 ^ 3 ^ 40 ^ 50 ^ 91 ^ 7 ^ 6 ^ 0 ^ 2 ^ 5 ^ 68 ^ 22 = 64</pre>
 *
 * Perform this operation on each of the sixteen blocks of sixteen numbers in your sparse hash to
 * determine the sixteen numbers in your dense hash.
 *
 * <p>Finally, the standard way to represent a Knot Hash is as a single hexadecimal string; the
 * final output is the dense hash in hexadecimal notation. Because each number in your dense hash
 * will be between 0 and 255 (inclusive), always represent each number as two hexadecimal digits
 * (including a leading zero as necessary). So, if your first three numbers are 64, 7, 255, they
 * correspond to the hexadecimal numbers 40, 07, ff, and so the first six characters of the hash
 * would be 4007ff. Because every Knot Hash is sixteen such numbers, the hexadecimal representation
 * is always 32 hexadecimal digits (0-f) long.
 *
 * <p>Here are some example hashes:
 *
 * <ul>
 * <li>The empty string becomes a2582a3a0e66e6e86e3812dcb672a272.
 * <li>AoC 2017 becomes 33efeb34ea91902bb2f59c9920caa6cd.
 * <li>1,2,3 becomes 3efbe78a8d82f29979031a4aa0b16a9d.
 * <li>1,2,4 becomes 63960835bcdc130f0b66d7ff4f6a5a8e.
 * </ul>
 *
 * Treating your puzzle input as a string of ASCII characters, what is the Knot Hash of your puzzle
 * input? Ignore any leading or trailing whitespace you might encounter.
 */
public class Year2017Day10 extends AbstractDay {

  public Year2017Day10() {
    super(2017, 10);
  }

  @Override
  public String solvePart1() {
    int solution = simpleKnot(getRiddleInput().firstLine(), 256);
    return Integer.toString(solution);
  }

  @Override
  public String solvePart2() {
    return makeKnotHash(getRiddleInput().firstLine());
  }

  static int simpleKnot(String input, int length) {
    int[] lengthArray = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
    int[] array = IntStream.range(0, length).toArray();
    int pos = 0;
    int skip = 0;

    for (int l : lengthArray) {
      reverse(array, pos, l);
      pos = (pos + l + skip++) % array.length;
    }

    return array[0] * array[1];
  }

  /**
   * Reverse a part of an circular array, that means it wraps.
   */
  static void reverse(int[] array, int from, int length) {
    int to = from + length;
    for (int i = 0; i < length / 2; i++) {
      swap(array, (from + i) % array.length, (to - 1 - i) % array.length);
    }
  }

  private static void swap(int[] array, int i, int j) {
    int t = array[i];
    array[i] = array[j];
    array[j] = t;
  }

  static String makeKnotHash(String input) {
    int[] lengthArray = toLengthArray(input);
    int[] array = IntStream.range(0, 256).toArray();
    int pos = 0;
    int skip = 0;

    final int rounds = 64;
    for (int i = 0; i < rounds; i++) {
      for (int l : lengthArray) {
        reverse(array, pos, l);
        pos = (pos + l + skip++) % array.length;
      }
    }

    return toHex(toDenseHash(array));
  }

  static int[] toLengthArray(String input) {
    return IntStream.concat(input.chars(), IntStream.of(17, 31, 73, 47, 23)).toArray();
  }

  static int[] toDenseHash(int[] sparseHash) {
    final int denseLength = sparseHash.length / 16;
    return IntStream.range(0, denseLength)
        .map(x -> xorRangeReduce(sparseHash, x * 16, (x + 1) * 16))
        .toArray();
  }

  private static int xorRangeReduce(int[] array, int startInclusive, int endExclusive) {
    return Arrays.stream(array, startInclusive, endExclusive)
        .reduce(0, (left, right) -> left ^ right);
  }

  static String toHex(int[] input) {
    return Arrays.stream(input)
        .mapToObj(i -> String.format("%02x", i))
        .collect(Collectors.joining());
  }
}
