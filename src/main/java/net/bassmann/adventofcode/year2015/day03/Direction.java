package net.bassmann.adventofcode.year2015.day03;

/** Direction enum, with all four directions in 2D grid space. */
enum Direction {
  NORTH(+1, 0),
  EAST(0, +1),
  SOUTH(-1, 0),
  WEST(0, -1);

  private final int xIncrement;
  private final int yIncrement;

  Direction(int xIncrement, int yIncrement) {
    this.xIncrement = xIncrement;
    this.yIncrement = yIncrement;
  }

  int getxIncrement() {
    return xIncrement;
  }

  int getyIncrement() {
    return yIncrement;
  }

  static Direction fromChar(char c) {
    switch (c) {
      case '^':
        return NORTH;
      case '>':
        return EAST;
      case 'v':
        return SOUTH;
      case '<':
        return WEST;
      default:
        throw new IllegalArgumentException();
    }
  }
}
