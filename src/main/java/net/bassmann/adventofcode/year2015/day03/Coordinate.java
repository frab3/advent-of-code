package net.bassmann.adventofcode.year2015.day03;

/** Immutable data class to identify coordinates of houses that Santa has visited. */
class Coordinate {
  private final int x;
  private final int y;

  Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  Coordinate moveTo(Direction d) {
    return new Coordinate(x + d.getxIncrement(), y + d.getyIncrement());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Coordinate that = (Coordinate) o;

    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
