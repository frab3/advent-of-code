package net.bassmann.adventofcode.year2017.day22;

enum Direction {
  NORTH(-1, 0),
  EAST(0, 1),
  SOUTH(1, 0),
  WEST(0, -1);

  final int dx;
  final int dy;

  Direction(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  Direction left() {
    switch (this) {
      case NORTH:
        return WEST;
      case WEST:
        return SOUTH;
      case SOUTH:
        return EAST;
      case EAST:
        return NORTH;
      default:
        throw new IllegalStateException();
    }
  }

  Direction right() {
    switch (this) {
      case NORTH:
        return EAST;
      case EAST:
        return SOUTH;
      case SOUTH:
        return WEST;
      case WEST:
        return NORTH;
      default:
        throw new IllegalArgumentException();
    }
  }

  Direction reverse() {
    switch (this) {
      case NORTH:
        return SOUTH;
      case SOUTH:
        return NORTH;
      case EAST:
        return WEST;
      case WEST:
        return EAST;
      default:
        throw new IllegalStateException();
    }
  }
}
