package net.bassmann.adventofcode.year2015.day09;

import static net.bassmann.adventofcode.year2015.day09.Permutator.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class PermutatorTest {

  @Test
  void completePermutationOfFourElements() {
    Permutator p = new Permutator(4);

    assertEquals("[0, 1, 2, 3]", p.next().toString());

    assertEquals("[0, 1, 3, 2]", p.next().toString());
    assertEquals("[0, 3, 1, 2]", p.next().toString());
    assertEquals("[3, 0, 1, 2]", p.next().toString());

    assertFalse(p.isMobile(3));
    assertEquals("[3, 0, 2, 1]", p.next().toString());
    assertTrue(p.isMobile(3));
    assertEquals(3 , p.findLargestMobileValue());
    assertEquals("[0, 3, 2, 1]", p.next().toString());
    assertEquals("[0, 2, 3, 1]", p.next().toString());
    assertEquals("[0, 2, 1, 3]", p.next().toString());

    assertEquals("[2, 0, 1, 3]", p.next().toString());
    assertEquals("[2, 0, 3, 1]", p.next().toString());
    assertEquals("[2, 3, 0, 1]", p.next().toString());
    assertEquals("[3, 2, 0, 1]", p.next().toString());

    assertEquals("[3, 2, 1, 0]", p.next().toString());
    assertEquals("[2, 3, 1, 0]", p.next().toString());
    assertEquals("[2, 1, 3, 0]", p.next().toString());
    assertEquals("[2, 1, 0, 3]", p.next().toString());

    assertEquals("[1, 2, 0, 3]", p.next().toString());
    assertEquals("[1, 2, 3, 0]", p.next().toString());
    assertEquals("[1, 3, 2, 0]", p.next().toString());
    assertEquals("[3, 1, 2, 0]", p.next().toString());

    assertEquals("[3, 1, 0, 2]", p.next().toString());
    assertEquals("[1, 3, 0, 2]", p.next().toString());
    assertEquals("[1, 0, 3, 2]", p.next().toString());
    assertEquals("[1, 0, 2, 3]", p.next().toString());

    assertFalse(p.hasNext());
    assertThrows(NoSuchElementException.class, p::next);
  }

  @Test
  void afterInit_allValuesAreMobile_exceptFirst() {
    Permutator p = new Permutator(4);

    assertFalse(p.isMobile(0));
    assertTrue(p.isMobile(1));
    assertTrue(p.isMobile(2));
    assertTrue(p.isMobile(3));
  }

  @Test
  void testFactorial() {
    assertEquals(1, factorial(1));
    assertEquals(2, factorial(2));
    assertEquals(6, factorial(3));
    assertEquals(24, factorial(4));
    assertEquals(120, factorial(5));
    assertEquals(720, factorial(6));
    assertEquals(40320, factorial(8));
  }
}
