package net.bassmann.adventofcode.year2017.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import net.bassmann.adventofcode.common.Solution;
import org.junit.jupiter.api.Test;

class Year2017Day13Test {

  private static final List<String> EXAMPLE = List.of("0: 3", "1: 2", "4: 4", "6: 4");

  private final Day today = new Year2017Day13();

  @Test
  void solvePart1() {
    assertEquals("1900", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("3966414", today.solvePart2());
  }

  @Test
  void exampleTest() {
    final Solution solution = Year2017Day13.solve(EXAMPLE);
    assertEquals("24", solution.getPartOne());
    assertEquals("10", solution.getPartTwo());
  }

  @Test
  void firewallTest() {
    Firewall f = new Firewall(EXAMPLE);
    assertEquals(24, f.getSeverity(0));
    assertTrue(f.getCaught(0));
    assertEquals(0, f.getSeverity(10));
    assertFalse(f.getCaught(10));
  }

  @Test
  void ScannerTest() {
    Scanner s = new Scanner(0, 3);
    assertTrue(s.isAtTop(0));
    assertTrue(s.isAtTop(4));
    assertTrue(s.isAtTop(8));

    assertFalse(s.isAtTop(1));
    assertFalse(s.isAtTop(2));
    assertFalse(s.isAtTop(3));
    assertFalse(s.isAtTop(5));
    assertFalse(s.isAtTop(6));
    assertFalse(s.isAtTop(7));
    assertFalse(s.isAtTop(9));
  }
}
