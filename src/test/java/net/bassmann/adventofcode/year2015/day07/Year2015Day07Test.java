package net.bassmann.adventofcode.year2015.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2015Day07Test {

  private final Day today = new Year2015Day07();

  @Test
  void solvePart1() {
    assertEquals("3176", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("14710", today.solvePart2());
  }

  @Test
  void shortTest() {
    short s = -1;
    int i = Short.toUnsignedInt(s);
    assertEquals("65535", Integer.toString(i));
    assertEquals("1111111111111111", shortToBinaryString(s));

    short t = Short.MAX_VALUE;
    int j = t & 0xffff;
    assertEquals("32767", Integer.toString(j));
    assertEquals("0111111111111111", shortToBinaryString(t));

    String s1 = String.format("%16s", Integer.toBinaryString(t & 0xFFFF)).replace(' ', '0');
    assertEquals("0111111111111111", s1);

    int k = 65535;
    short u = (short) k;
    assertEquals(-1, u);
    assertEquals("1111111111111111", shortToBinaryString(u));
  }

  @Test
  void bitOperationTest() {
    short s = 32;
    assertEquals("0000000000100000", shortToBinaryString(s));
    s <<= 2;
    assertEquals("0000000010000000", shortToBinaryString(s));

    s = (short) ~s;
    assertEquals("1111111101111111", shortToBinaryString(s));

    short u = (short) (s >> 2);
    short t = (short) (s >>> 2);
    assertEquals("1111111111011111", shortToBinaryString(u));
    assertEquals("1111111111011111", shortToBinaryString(t));
  }

  private String shortToBinaryString(short s) {
    return String.format("%16s", Integer.toBinaryString(s & 0xFFFF)).replace(' ', '0');
  }
}
