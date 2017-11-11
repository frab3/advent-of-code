package net.bassmann.adventofcode.year2015.day06;

enum Command {
  ON,
  OFF,
  TOGGLE;

  static Command fromToken(String s, String t) {
    if ("toggle".equals(s)) {
      return TOGGLE;
    } else if ("on".equals(t)) {
      return ON;
    } else if ("off".equals(t)) {
      return OFF;
    }
    return null;
  }
}
