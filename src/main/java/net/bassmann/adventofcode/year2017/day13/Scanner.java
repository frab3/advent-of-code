package net.bassmann.adventofcode.year2017.day13;

class Scanner {

  private final int depth;
  private final int range;
  private final int severity;
  private final int periodLength;

  Scanner(String depth, String range) {
    this(Integer.parseInt(depth), Integer.parseInt(range));
  }

  Scanner(int depth, int range) {
    this.depth = depth;
    this.range = range;
    this.severity = depth * range;
    this.periodLength = (range - 1) * 2;
  }

  int getRange() {
    return range;
  }

  boolean isAtTop(int delay) {
    return ((delay + depth) % periodLength) == 0;
  }

  int getSeverity() {
    return severity;
  }
}
