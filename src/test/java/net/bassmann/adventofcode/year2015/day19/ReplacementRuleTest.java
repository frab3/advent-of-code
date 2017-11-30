package net.bassmann.adventofcode.year2015.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReplacementRuleTest {

  @Test
  void fromStringTest() {
    ReplacementRule rule = ReplacementRule.fromString("H => HO");
    assertEquals("H", rule.getOriginal());
    assertEquals("HO", rule.getReplacement());
  }
}
