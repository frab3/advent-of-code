package net.bassmann.adventofcode.year2021.day11;

import java.util.List;

public class DumboOctopuses {

  private final int size;
  private final int[][] octopuses;

  DumboOctopuses(List<String> input) {
    size = input.size();
    octopuses = new int[size][size];
    for (int l = 0; l < size; l++) {
      String line = input.get(l);
      for (int c = 0; c < size; c++) {
        octopuses[l][c] = Integer.parseInt(line.substring(c, c + 1));
      }
    }
  }

  int oneStep() {
    increaseEnergy();

    for (int l = 0; l < size; l++) {
      for (int c = 0; c < size; c++) {
        if (octopuses[l][c] == 10) {
          octopuses[l][c] = -1;
          flashAround(l, c);
        }
      }
    }

    int flashes = 0;
    for (int l = 0; l < size; l++) {
      for (int c = 0; c < size; c++) {
        if (octopuses[l][c] == -1) {
          flashes++;
          octopuses[l][c] = 0;
        }
      }
    }

    return flashes;
  }

  void inc(int l, int c) {
    if (l < 0 || c < 0 || l > size - 1 || c > size - 1 || octopuses[l][c] == -1) return;
    octopuses[l][c]++;
    if (octopuses[l][c] > 9) {
      octopuses[l][c] = -1;
      flashAround(l, c);
    }
  }

  void flashAround(int l, int c) {
    inc(l - 1, c - 1);
    inc(l - 1, c);
    inc(l - 1, c + 1);
    inc(l, c - 1);
    inc(l, c + 1);
    inc(l + 1, c - 1);
    inc(l + 1, c);
    inc(l + 1, c + 1);
  }

  void increaseEnergy() {
    for (int l = 0; l < size; l++) {
      for (int c = 0; c < size; c++) {
        octopuses[l][c]++;
      }
    }
  }

  @Override
  public String toString() {
    var b = new StringBuilder();
    for (int l = 0; l < size; l++) {
      for (int c = 0; c < size; c++) {
        b.append(octopuses[l][c]);
      }
      if (l < size - 1) {
        b.append('\n');
      }
    }
    return b.toString();
  }
}
