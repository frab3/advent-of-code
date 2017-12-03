package net.bassmann.adventofcode.year2017.day03;

class SquareSpiral {

  private SquareSpiral() {
  }

  /**
   * A function to get the coordinates for the x-th step of the spiral.
   */
  static Coordinates numberToCoordinates(int number) {
    if (number == 1) {
      return new Coordinates(0, 0);
    }

    int maxDistance = (int) Math.ceil(Math.sqrt(number));
    if (maxDistance % 2 == 0) {
      maxDistance++;
    }
    final int lowerRight = maxDistance * maxDistance;
    final int lowerLeft = lowerRight - (maxDistance - 1);
    final int upperLeft = lowerLeft - (maxDistance - 1);
    final int upperRight = upperLeft - (maxDistance - 1);
    int half = maxDistance / 2;
    if (number > lowerLeft) {
      int x = number - lowerRight + half;
      int y = half;
      return new Coordinates(x, y);
    }
    if (number > upperLeft) {
      int x = -half;
      int y = number - lowerLeft + half;
      return new Coordinates(x, y);
    }
    if (number > upperRight) {
      int x = -(number - upperLeft + half);
      int y = -half;
      return new Coordinates(x, y);
    }
    int x = half;
    int y = -(number - upperRight + half);
    return new Coordinates(x, y);
  }
}
