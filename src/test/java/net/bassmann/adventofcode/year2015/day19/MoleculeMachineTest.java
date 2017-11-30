package net.bassmann.adventofcode.year2015.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class MoleculeMachineTest {

  @Test
  void doOneReplacement() {
    MoleculeMachine mm = new MoleculeMachine(List.of(), "fooba");
    ReplacementRule r = new ReplacementRule("ba", "bar");
    assertEquals("foobar", mm.doOneReplacement(r, 3));

    ReplacementRule rule2 = new ReplacementRule("o", "OO");
    assertEquals("fOOoba", mm.doOneReplacement(rule2, 1));
    assertEquals("foOOba", mm.doOneReplacement(rule2, 2));
  }
}
