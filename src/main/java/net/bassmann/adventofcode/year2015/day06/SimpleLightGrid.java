package net.bassmann.adventofcode.year2015.day06;

/** Simple light grid - for part 1. Just supports turning lights on and off. */
class SimpleLightGrid extends AbstractLightGrid {

  private final boolean[][] lightMatrix;

  SimpleLightGrid(int size) {
    super(size);
    lightMatrix = new boolean[size][size];
  }

  @Override
  void turnOn(int x, int y) {
    lightMatrix[x][y] = true;
  }

  @Override
  void turnOff(int x, int y) {
    lightMatrix[x][y] = false;
  }

  @Override
  void toggle(int x, int y) {
    lightMatrix[x][y] = !lightMatrix[x][y];
  }

  @Override
  int getValue(int x, int y) {
    return lightMatrix[x][y] ? 1 : 0;
  }
}
