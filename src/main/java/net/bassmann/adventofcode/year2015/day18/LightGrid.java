package net.bassmann.adventofcode.year2015.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class LightGrid {

  private final int size;
  private boolean[][] grid;

  LightGrid(int size) {
    this.size = size;
    grid = new boolean[size][size];
  }

  void init(List<String> input) {
    IntStream.range(0, size).forEach(i -> this.initRow(i, input.get(i)));
  }

  private void initRow(int row, String input) {
    if (!isInRange(row)) {
      throw new IllegalArgumentException("Row number not in valid range");
    }
    if (input.length() != size) {
      throw new IllegalArgumentException("Input has wrong size");
    }
    for (int col = 0; col < size; col++) {
      grid[row][col] = input.charAt(col) == '#';
    }
  }

  void next() {
    boolean[][] newGrid = new boolean[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        newGrid[row][col] = getNextState(row, col);
      }
    }
    grid = newGrid;
  }

  /**
   * Logic how to get the next state for a given cell.
   *
   * <ul>
   *   <li>A light which is on stays on when 2 or 3 neighbors are on, and turns off otherwise.
   *   <li>A light which is off turns on if exactly 3 neighbors are on, and stays off otherwise.
   * </ul>
   */
  private boolean getNextState(int row, int col) {
    boolean current = getState(row, col);
    int onNeighbours = countOnNeighbours(row, col);
    return current && onNeighbours >= 2 && onNeighbours <= 3 || !current && onNeighbours == 3;
  }

  int countOnNeighbours(int row, int col) {
    int count = 0;
    for (int r = row - 1; r <= row + 1; r++) {
      for (int c = col - 1; c <= col + 1; c++) {
        if (r != row || c != col) {
          count += getState(r, c) ? 1 : 0;
        }
      }
    }
    return count;
  }

  boolean getState(int row, int col) {
    return isInRange(row) && isInRange(col) && grid[row][col];
  }

  void setState(int row, int col, boolean state) {
    grid[row][col] = state;
  }

  private boolean isInRange(int i) {
    return i >= 0 && i < size;
  }

  List<String> asList() {
    List<String> list = new ArrayList<>(size);
    for (int row = 0; row < size; row++) {
      list.add(rowToString(row));
    }
    return list;
  }

  private String rowToString(int row) {
    StringBuilder sb = new StringBuilder(size);
    for (int col = 0; col < size; col++) {
      sb.append(grid[row][col] ? '#' : '.');
    }
    return sb.toString();
  }

  int countOn() {
    int count = 0;
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        count += getState(row, col) ? 1 : 0;
      }
    }
    return count;
  }
}
