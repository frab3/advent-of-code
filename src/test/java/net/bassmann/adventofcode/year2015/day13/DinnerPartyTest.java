package net.bassmann.adventofcode.year2015.day13;

import static net.bassmann.adventofcode.year2015.day13.Year2015Day13Test.TEST_INPUT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DinnerPartyTest {

  @Test
  void addGuestsTest() {
    DinnerParty p = new DinnerParty();
    TEST_INPUT.forEach(p::addGuest);
    assertEquals(4, p.size());
    assertEquals(44, p.getHappiness(0, 3));
  }
}
