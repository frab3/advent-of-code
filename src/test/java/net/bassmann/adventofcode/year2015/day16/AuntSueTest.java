package net.bassmann.adventofcode.year2015.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class AuntSueTest {

  @Test
  void auntieMatchesTest() {
    AuntSue sue = new AuntSue(1);

    sue.addPropery("cats", 2);

    assertTrue(sue.matches("cats", 2));
    assertFalse(sue.matches("cats", 1));

    assertTrue(sue.matches("dogs", 1));
  }

  @Test
  void auntieCreationFromStringTest() {
    AuntSue sue = AuntSue.fromString("Sue 1: children: 1, cars: 8, vizslas: 7");

    assertEquals(1, sue.getNumber());

    assertTrue(sue.matches("children", 1));
    assertFalse(sue.matches("children", 2));

    assertTrue(sue.matches("cars", 8));
    assertFalse(sue.matches("cars", 7));

    assertTrue(sue.matches("vizslas", 7));
    assertFalse(sue.matches("vizslas", 6));

    assertTrue(sue.matches("foo", 42));
  }

  @Test
  void auntieMatchesScanTest() {
    final Map<String, Integer> scan =
        Map.of(
            "cats", 1,
            "dogs", 2,
            "children", 3,
            "perfumes", 4);

    final AuntSue matchingSue = AuntSue.fromString("Sue 1: cats: 1, dogs: 2, children: 3");
    assertTrue(matchingSue.matchesScan(scan));

    final AuntSue wrongSue = AuntSue.fromString("Sue 1: cats: 1, dogs: 2, children: 0");
    assertFalse(wrongSue.matchesScan(scan));
  }
}
