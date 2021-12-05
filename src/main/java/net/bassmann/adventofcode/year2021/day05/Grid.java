package net.bassmann.adventofcode.year2021.day05;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Grid {

  private final int[][] grid; // x,y
  private final int dimension;

  Grid(int dimension) {
    this.dimension = dimension;
    grid = new int[dimension][dimension];
  }

  void add(Line line) {
    if (line.isNotDiagonal()) {
      addRegular(line);
    } else {
      addDiagonal(line);
    }
  }

  void addRegular(Line line) {
    int x1 = min(line.x1, line.x2);
    int x2 = max(line.x1, line.x2);
    int y1 = min(line.y1, line.y2);
    int y2 = max(line.y1, line.y2);
    for (int x = x1; x <= x2; x++) {
      for (int y = y1; y <= y2; y++) {
        grid[x][y]++;
      }
    }
  }

  void addDiagonal(Line line) {
    // 3,3 => 5,5
    // 3,3 => 1,5
    // 3,3 => 1,1
    // 3,3 => 5,1

    int xMod = line.x1 < line.x2 ? 1 : -1;
    int yMod = line.y1 < line.y2 ? 1 : -1;
    int xStop = line.x2 + xMod;
    int yStop = line.y2 + yMod;

    for (int x = line.x1, y = line.y1; x != xStop && y != yStop; x += xMod, y += yMod) {
      grid[x][y]++;
    }
  }

  int count() {
    int c = 0;
    for (int x = 0; x < dimension; x++) {
      for (int y = 0; y < dimension; y++) {
        if (grid[x][y] >= 2) {
          c++;
        }
      }
    }
    return c;
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    for (int x = 0; x < dimension; x++) {
      for (int y = 0; y < dimension; y++) {
        b.append(grid[x][y]);
      }
      if (x < dimension - 1) {
        b.append('\n');
      }
    }
    return b.toString();
  }
}
