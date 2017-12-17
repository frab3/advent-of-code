package net.bassmann.adventofcode.year2016.day01;

enum Direction {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  Direction turn(char c) {
    final boolean left = c == 'L';
    switch (this) {
      case NORTH:
        return left ? WEST : EAST;
      case WEST:
        return left ? SOUTH : NORTH;
      case SOUTH:
        return left ? EAST : WEST;
      case EAST:
        return left ? NORTH : SOUTH;
      default:
        throw new IllegalArgumentException();
    }
  }
}
