package net.bassmann.adventofcode.year2021.day13;

public class Point {

  private final int x;
  private final int y;

  Point(String input) {
    int s = input.indexOf(',');
    x = Integer.parseInt(input.substring(0, s));
    y = Integer.parseInt(input.substring(s + 1));
  }

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Point point = (Point) o;

    if (x != point.x) {
      return false;
    }
    return y == point.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
