package net.bassmann.adventofcode.year2016.day01;

import java.util.Objects;

class Coordinates {

  private final int x;
  private final int y;

  Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  Coordinates go(Direction d) {
    switch (d) {
      case NORTH:
        return new Coordinates(x, y + 1);
      case EAST:
        return new Coordinates(x + 1, y);
      case SOUTH:
        return new Coordinates(x, y - 1);
      case WEST:
        return new Coordinates(x - 1, y);
      default:
        throw new IllegalArgumentException();
    }
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  int getManhattanDistance() {
    return Math.abs(x) + Math.abs(y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Coordinates that = (Coordinates) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
