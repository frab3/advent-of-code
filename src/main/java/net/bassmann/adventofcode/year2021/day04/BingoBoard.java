package net.bassmann.adventofcode.year2021.day04;

import static java.lang.String.format;

import java.util.List;

public class BingoBoard {
  private static final int DIM = 5;

  private final int[][] numbers = new int[DIM][DIM];
  private final boolean[][] marked = new boolean[DIM][DIM];

  BingoBoard(List<String> input) {
    if (input.size() != DIM) {
      throw new IllegalArgumentException("input has wrong size");
    }

    for (int l = 0; l < DIM; l++) {
      String line = input.get(l);
      if (line.length() != 3 * DIM - 1) {
        throw new IllegalArgumentException("line '" + line + "'has wrong size:" + line.length());
      }
      for (int c = 0; c < DIM; c++) {
        numbers[c][l] = Integer.parseInt(line.substring(c * 3, c * 3 + 2).trim());
      }
    }
  }

  public int[] mark(int number) {
    for (int row = 0; row < DIM; row++) {
      for (int col = 0; col < DIM; col++) {
        if (numbers[col][row] == number) {
          marked[col][row] = true;
          return new int[] {row, col};
        }
      }
    }
    return null;
  }

  public boolean checkForBingo(int[] pos) {
    if (pos == null) return false;
    int row = pos[0];
    int col = pos[1];
    return marked[0][row] && marked[1][row] && marked[2][row] && marked[3][row] && marked[4][row]
        || marked[col][0] && marked[col][1] && marked[col][2] && marked[col][3] && marked[col][4];
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    for (int row = 0; row < DIM; row++) {
      for (int col = 0; col < DIM; col++) {
        b.append(format("%2d", numbers[col][row]));
        if (marked[col][row]) {
          b.append('*');
        } else {
          b.append(' ');
        }

        if (col != DIM - 1) {
          b.append(' ');
        }
      }
      if (row != DIM - 1) {
        b.append('\n');
      }
    }

    return b.toString();
  }

  public int unmarkedSum() {
    int sum = 0;
    for (int row = 0; row < DIM; row++) {
      for (int col = 0; col < DIM; col++) {
        if (!marked[col][row]) {
          sum += numbers[col][row];
        }
      }
    }
    return sum;
  }
}
