package net.bassmann.adventofcode.year2015.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PackageTest {

  /**
   * A present with dimensions 2x3x4 requires 2*6 + 2*12 + 2*8 = 52 square feet of wrapping paper
   * plus 6 square feet of slack, for a total of 58 square feet.
   */
  @Test
  void Part1_test1() {
    Package p = new Package(2,3,4);
    assertEquals(52, p.getSurface());
    assertEquals(6, p.getSlack());
    assertEquals(58, p.getAreaOfPackingPaperNeeded());
  }

  @Test
  void Part1_test1_fromString() {
    Package p = Package.fromString("2x3x4");
    assertEquals(52, p.getSurface());
    assertEquals(6, p.getSlack());
    assertEquals(58, p.getAreaOfPackingPaperNeeded());
  }

  @Test
  void fromString_wrongArgument_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> Package.fromString(""));
    assertThrows(IllegalArgumentException.class, () -> Package.fromString("1"));
    assertThrows(IllegalArgumentException.class, () -> Package.fromString("1x2"));
    assertThrows(IllegalArgumentException.class, () -> Package.fromString("1xx2"));
    assertThrows(IllegalArgumentException.class, () -> Package.fromString("1x2x3x4"));

    assertThrows(IllegalArgumentException.class, () -> Package.fromString("axbxc"));
  }

  /**
   * A present with dimensions 1x1x10 requires 2*1 + 2*10 + 2*10 = 42 square feet of wrapping paper
   * plus 1 square foot of slack, for a total of 43 square feet.
   */
  @Test
  void Part1_test2() {
    Package p = new Package(1,1,10);
    assertEquals(42, p.getSurface());
    assertEquals(1, p.getSlack());
    assertEquals(43, p.getAreaOfPackingPaperNeeded());
  }

  @Test
  void getSurfaceWithSlack() {}

  @Test
  void getRibbonNeeded() {}
}
