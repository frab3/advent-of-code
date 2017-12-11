package net.bassmann.adventofcode.year2017.day11;

import java.util.stream.IntStream;

class HexGridCell {

  static final HexGridCell CENTER = new HexGridCell(0, 0, 0);

  private final int x;
  private final int y;
  private final int z;

  private HexGridCell(int x, int y, int z) {
    if ((x + y + z) != 0) {
      throw new IllegalArgumentException();
    }
    this.x = x;
    this.y = y;
    this.z = z;
  }

  HexGridCell move(String s) {
    switch (s) {
      case "n":
        return new HexGridCell(x, y + 1, z - 1);
      case "ne":
        return new HexGridCell(x + 1, y, z - 1);
      case "se":
        return new HexGridCell(x + 1, y - 1, z);
      case "s":
        return new HexGridCell(x, y - 1, z + 1);
      case "sw":
        return new HexGridCell(x - 1, y, z + 1);
      case "nw":
        return new HexGridCell(x - 1, y + 1, z);
      default:
        throw new IllegalArgumentException();
    }
  }

  int distance(HexGridCell other) {
    return IntStream.of(x - other.x, y - other.y, z - other.z).map(Math::abs).max().orElse(0);
  }

  int distanceToCenter() {
    return IntStream.of(x, y, z).map(Math::abs).max().orElse(0);
  }
}
