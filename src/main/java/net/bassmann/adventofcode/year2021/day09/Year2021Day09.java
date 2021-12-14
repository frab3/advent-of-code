package net.bassmann.adventofcode.year2021.day09;

import java.util.Arrays;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day09 extends AbstractDay {

  Year2021Day09() {
    super(2021, 9);
  }

  @Override
  public String solvePart1() {
    var s = getRiskLevelSumOfLowPoints(toMap(getRiddleInput().asList()));
    return String.valueOf(s);
  }

  @Override
  public String solvePart2() {
    var count = countBasins(markAllBasins(toMap(getRiddleInput().asList())));
    Arrays.sort(count);
    var v = count[count.length - 2] * count[count.length - 3] * count[count.length - 4];
    return String.valueOf(v);
  }

  static int[][] toMap(List<String> input) {
    int maxX = input.get(0).length();
    int maxY = input.size();

    var map = new int[maxX][maxY];
    for (int y = 0; y < maxY; y++) {
      String l = input.get(y);
      for (int x = 0; x < maxX; x++) {
        map[x][y] = Integer.parseInt(l.substring(x, x + 1));
      }
    }

    return map;
  }

  static int getRiskLevelSumOfLowPoints(int[][] input) {
    int maxX = input.length;
    int maxY = input[0].length;
    int sum = 0;
    for (int x = 0; x < maxX; x++) {
      for (int y = 0; y < maxY; y++) {
        if (isLocalLow(x, y, maxX, maxY, input)) {
          sum += 1 + input[x][y];
        }
      }
    }
    return sum;
  }

  private static boolean isLocalLow(int x, int y, int maxX, int maxY, int[][] input) {
    int l = input[x][y];
    int up = 10;
    int down = 10;
    int left = 10;
    int right = 10;
    if (y - 1 >= 0) {
      up = input[x][y - 1];
    }
    if (y + 1 < maxY) {
      down = input[x][y + 1];
    }
    if (x - 1 >= 0) {
      left = input[x - 1][y];
    }
    if (x + 1 < maxX) {
      right = input[x + 1][y];
    }
    return l < up && l < down && l < left && l < right;
  }

  static int[][] markAllBasins(int[][] input) {
    int maxX = input.length;
    int maxY = input[0].length;
    int basin = 0;
    int[][] basins = new int[maxX][maxY];
    for (int x = 0; x < maxX; x++) {
      for (int y = 0; y < maxY; y++) {
        if (input[x][y] != 9 && basins[x][y] == 0) {
          markBasin(x, y, ++basin, maxX, maxY, input, basins);
        }
      }
    }
    return basins;
  }

  static void markBasin(
      int x, int y, int basin, int maxX, int maxY, int[][] input, int[][] basins) {
    if (x < 0 || y < 0 || x > maxX - 1 || y > maxY - 1 || input[x][y] == 9 || basins[x][y] != 0) {
      return;
    }
    basins[x][y] = basin;
    markBasin(x - 1, y, basin, maxX, maxY, input, basins);
    markBasin(x + 1, y, basin, maxX, maxY, input, basins);
    markBasin(x, y - 1, basin, maxX, maxY, input, basins);
    markBasin(x, y + 1, basin, maxX, maxY, input, basins);
  }

  static int[] countBasins(int[][] basins) {
    int maxX = basins.length;
    int maxY = basins[0].length;
    int totalBasins = 0;
    for (int x = 0; x < maxX; x++) {
      for (int y = 0; y < maxY; y++) {
        if (basins[x][y] > totalBasins) {
          totalBasins = basins[x][y];
        }
      }
    }
    int[] countInBasins = new int[totalBasins + 1];
    for (int x = 0; x < maxX; x++) {
      for (int y = 0; y < maxY; y++) {
        countInBasins[basins[x][y]]++;
      }
    }
    return countInBasins;
  }

  public static void main(String[] args) {
    var today = new Year2021Day09();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
