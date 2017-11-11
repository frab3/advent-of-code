package net.bassmann.adventofcode.year2015.day06;

/** Advanced light grid with brightness support - for part 2. */
class BrightnessLightGrid extends AbstractLightGrid {

  private final int[][] lightMatrix;

  BrightnessLightGrid(int size) {
    super(size);
    lightMatrix = new int[size][size];
  }

  @Override
  void turnOn(int x, int y) {
    lightMatrix[x][y]++;
  }

  @Override
  void turnOff(int x, int y) {
    if (lightMatrix[x][y] > 0) {
      lightMatrix[x][y]--;
    }
  }

  @Override
  void toggle(int x, int y) {
    lightMatrix[x][y] += 2;
  }

  @Override
  int getValue(int x, int y) {
    return lightMatrix[x][y];
  }
}
