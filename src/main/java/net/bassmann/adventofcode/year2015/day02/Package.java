package net.bassmann.adventofcode.year2015.day02;

import static java.lang.Integer.parseInt;

import java.util.stream.IntStream;

/** Immutable data class, that is the core to the solution. */
class Package {

  /** Area of bottom (and top). */
  private final int areaBottom;
  /** Area of the sides. */
  private final int areaSide;
  /** Area of front (and back). */
  private final int areaFront;

  /** Volume of the package */
  private final int volume;
  /** The smallest perimeter of any face. */
  private final int minPerimeter;

  Package(int width, int length, int height) {
    areaBottom = width * length;
    areaSide = length * height;
    areaFront = height * width;
    volume = width * length * height;
    minPerimeter = calculateMinPerimeter(width, length, height);
  }

  static Package fromString(String dimensions) {
    String[] d = dimensions.split("x");
    if (d.length != 3) {
      throw new IllegalArgumentException("Cannot parse dimensions string, wrong format");
    }
    return new Package(parseInt(d[0]), parseInt(d[1]), parseInt(d[2]));
  }

  /** Find the minimum combination of two sides and multiply it by two to get the perimeter. */
  private int calculateMinPerimeter(int width, int length, int height) {
    return 2 * IntStream.of((width + length), (length + height), (height + width)).min().orElse(0);
  }

  /** The area of the smallest side is the slack. */
  int getSlack() {
    return IntStream.of(areaBottom, areaSide, areaFront).min().orElse(0);
  }

  /** The surface is the sum of the areas of all sides. */
  int getSurface() {
    return (areaBottom + areaSide + areaFront) * 2;
  }

  /** This is the solution to part 1: the sum of surface and slack. */
  int getAreaOfPackingPaperNeeded() {
    return getSurface() + getSlack();
  }

  /** This is the solution to part 2: the sum of the volume and minimal perimeter. */
  int getLengthOfRibbonNeeded() {
    return volume + minPerimeter;
  }
}
