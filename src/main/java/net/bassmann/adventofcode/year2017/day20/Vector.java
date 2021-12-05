package net.bassmann.adventofcode.year2017.day20;

class Vector {
  private final long x;
  private final long y;
  private final long z;

  private Vector(long x, long y, long z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  Vector(String csv) {
    String[] split = csv.split(",");
    this.x = Long.parseLong(split[0]);
    this.y = Long.parseLong(split[1]);
    this.z = Long.parseLong(split[2]);
  }

  Vector add(Vector other) {
    return new Vector(x + other.x, y + other.y, z + other.z);
  }

  long getManhattanDistance() {
    return Math.abs(x) + Math.abs(y) + Math.abs(z);
  }

  double length() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  boolean samePos(Vector other) {
    return x == other.x && y == other.y && z == other.z;
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, %d)", x, y, z);
  }
}
