package net.bassmann.adventofcode.year2017.day14;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.year2017.day10.Year2017Day10;

/**
 * Day 14: Disk Defragmentation.
 *
 * <p>Suddenly, a scheduled job activates the system's <a
 * href="https://en.wikipedia.org/wiki/Defragmentation">disk defragmenter</a>. Were the situation
 * different, you might <a href="https://www.youtube.com/watch?v=kPv1gQ5Rs8A&amp;t=37">sit and watch
 * it for a while</a>, but today, you just don't have that kind of time. It's soaking up valuable
 * system resources that are needed elsewhere, and so the only option is to help it finish its task
 * as soon as possible.
 *
 * <p>The disk in question consists of a 128x128 grid; each square of the grid is either
 * <em>free</em> or <em>used</em>. On this disk, the state of the grid is tracked by the bits in a
 * sequence of <em>knot hashes</em>.
 *
 * <p>A total of 128 knot hashes are calculated, each corresponding to a single row in the grid;
 * each hash contains 128 bits which correspond to individual grid squares. Each bit of a hash
 * indicates whether that square is <em>free</em> (<code>0</code>) or <em>used</em> (<code>1</code>
 * ).
 *
 * <p>The hash inputs are a key string (your puzzle input), a dash, and a number from <code>0</code>
 * to <code>127</code> corresponding to the row. For example, if your key string were <code>flqrgnkx
 * </code>, then the first row would be given by the bits of the knot hash of <code>flqrgnkx-0
 * </code>, the second row from the bits of the knot hash of <code>flqrgnkx-1</code>, and so on
 * until the last row, <code>flqrgnkx-127</code>.
 *
 * <p>The output of a knot hash is traditionally represented by 32 hexadecimal digits; each of these
 * digits correspond to 4 bits, for a total of <code>4 * 32 = 128</code> bits. To convert to bits,
 * turn each hexadecimal digit to its equivalent binary value, high-bit first: <code>0</code>
 * becomes <code>0000</code>, <code>1</code> becomes <code>0001</code>, <code>e</code> becomes
 * <code>1110</code>, <code>f</code> becomes <code>1111</code>, and so on; a hash that begins with
 * <code>a0c2017...</code> in hexadecimal would begin with <code>10100000110000100000000101110000...
 * </code> in binary.
 *
 * <p>Continuing this process, the <em>first 8 rows and columns</em> for key <code>flqrgnkx</code>
 * appear as follows, using <code>#</code> to denote used squares, and <code>.</code> to denote free
 * ones:
 *
 * <pre>
 * ##.#.#..--&gt;
 * .#.#.#.#
 * ....#.#.
 * #.#.##.#
 * .##.#...
 * ##..#..#
 * .#...#..
 * ##.#.##.--&gt;
 * |      |
 * V      V
 * </pre>
 *
 * <p>In this example, <code>8108</code> squares are used across the entire 128x128 grid.
 *
 * <h2>Part One</h2>
 *
 * <p>Given your actual key string, <em>how many squares are used</em>?
 *
 * <h2>Part Two</h2>
 *
 * Now, <span title="This is exactly how it works in real life.">all the defragmenter needs to
 * know</span> is the number of <em>regions</em>. A region is a group of <em>used</em> squares that
 * are all <em>adjacent</em>, not including diagonals. Every used square is in exactly one region:
 * lone used squares form their own isolated regions, while several adjacent squares all count as a
 * single region.
 *
 * <p>In the example above, the following nine regions are visible, each marked with a distinct
 * digit:
 *
 * <pre>
 * 11.2.3..--&gt;
 * .1.2.3.4
 * ....5.6.
 * 7.8.55.9
 * .88.5...
 * 88..5..8
 * .8...8..
 * 88.8.88.--&gt;
 * |      |
 * V      V
 * </pre>
 *
 * <p>Of particular interest is the region marked <code>8</code>; while it does not appear
 * contiguous in this small view, all of the squares marked <code>8</code> are connected when
 * considering the whole 128x128 grid. In total, in this example, <code>1242</code> regions are
 * present.
 *
 * <p><em>How many regions</em> are present given your key string?
 */
public class Year2017Day14 extends AbstractDay {

  private List<String> grid;

  public Year2017Day14() {
    super(2017, 14);
  }

  @Override
  public String solvePart1() {
    return Integer.toString(countUsed(getGrid()));
  }

  @Override
  public String solvePart2() {
    return Integer.toString(countDistinctRegions(getGrid()));
  }

  private List<String> getGrid() {
    if (grid == null) {
      grid = makeGrid(getRiddleInput().firstLine());
    }
    return grid;
  }

  static List<String> makeGrid(String seed) {
    return IntStream.rangeClosed(0, 127)
        .mapToObj(i -> seed + "-" + i)
        .map(Year2017Day10::makeKnotHash)
        .map(Year2017Day14::hexStringToGridRow)
        .collect(Collectors.toList());
  }

  static int countUsed(List<String> input) {
    return input
        .stream()
        .mapToInt(s -> (int) s.chars().filter(c -> isUsed((char) c)).count())
        .sum();
  }

  static int countDistinctRegions(List<String> input) {
    int[][] grid = makeIntGrid(input);
    int currentRegion = 0;
    for (int row = 0; row < 128; row++) {
      for (int col = 0; col < 128; col++) {
        int current = grid[row][col];
        if (current == 0) {
          markRegion(grid, row, col, ++currentRegion);
        }
      }
    }
    return currentRegion;
  }

  private static int[][] makeIntGrid(List<String> input) {
    int[][] grid = new int[128][];
    for (int i = 0; i < 128; i++) {
      int[] row = new int[128];
      for (int j = 0; j < 128; j++) {
        row[j] = isUsed(input.get(i).charAt(j)) ? 0 : -1;
      }
      grid[i] = row;
    }
    return grid;
  }

  private static void markRegion(int[][] grid, int row, int col, int region) {
    grid[row][col] = region;
    if (row > 0 && grid[row - 1][col] == 0) markRegion(grid, row - 1, col, region);
    if (row < 127 && grid[row + 1][col] == 0) markRegion(grid, row + 1, col, region);
    if (col > 0 && grid[row][col - 1] == 0) markRegion(grid, row, col - 1, region);
    if (col < 127 && grid[row][col + 1] == 0) markRegion(grid, row, col + 1, region);
  }

  static String hexStringToGridRow(String input) {
    return input.chars().mapToObj(x -> hexCharToGridPart((char) x)).collect(Collectors.joining());
  }

  private static boolean isUsed(char c) {
    return c == '#';
  }

  private static String hexCharToGridPart(char c) {
    switch (c) {
      case '0':
        return "....";
      case '1':
        return "...#";
      case '2':
        return "..#.";
      case '3':
        return "..##";
      case '4':
        return ".#..";
      case '5':
        return ".#.#";
      case '6':
        return ".##.";
      case '7':
        return ".###";
      case '8':
        return "#...";
      case '9':
        return "#..#";
      case 'a':
        return "#.#.";
      case 'b':
        return "#.##";
      case 'c':
        return "##..";
      case 'd':
        return "##.#";
      case 'e':
        return "###.";
      case 'f':
        return "####";
      default:
        throw new IllegalArgumentException();
    }
  }
}
