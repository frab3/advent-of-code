package net.bassmann.adventofcode.year2020.day03;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Day;

public class Year2020Day03 extends AbstractDay {

  public Year2020Day03() {
    super(2020, 3);
  }

  @Override
  public String solvePart1() {
    int trees = countTrees(getRiddleInput().asList(), 3, 1);
    return Integer.toString(trees);
  }

  @Override
  public String solvePart2() {
    long product = countAllSlopes(getRiddleInput().asList());
    return Long.toString(product);
  }

  int countTrees(List<String> lines, int right, int down) {
    final int lineLength = lines.get(0).length();
    final int lineSize = lines.size();
    int treeCount = 0;
    int pos = 0;
    int line = 0;
    while (line < lineSize) {
      if (lines.get(line).charAt(pos % lineLength) == '#') {
        treeCount++;
      }
      line += down;
      pos += right;
    }
    return treeCount;
  }

  public static void main(String[] args) {
    Day today = new Year2020Day03();

    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }

  public long countAllSlopes(List<String> map) {
    return countTrees(map, 1, 1)
        * countTrees(map, 3, 1)
        * countTrees(map, 5, 1)
        * countTrees(map, 7, 1)
        * countTrees(map, 1, 2);
  }
}
