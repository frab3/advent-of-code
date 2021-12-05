package net.bassmann.adventofcode.year2020.day16;

public class FieldRule {

  private final String name;
  private final Interval range1;
  private final Interval rande2;

  private FieldRule(String name, Interval range1, Interval rande2) {
    this.name = name;
    this.range1 = range1;
    this.rande2 = rande2;
  }

  boolean isValid(int number) {
    return range1.contains(number) || rande2.contains(number);
  }

  String getName() {
    return name;
  }

  static FieldRule fromString(String line) {
    String[] s = line.split(": | or ");
    return new FieldRule(s[0], Interval.fromString(s[1]), Interval.fromString(s[2]));
  }

  static class Interval {
    private final int lowerInclusive;
    private final int upperInclusive;

    Interval(int lowerInclusive, int upperInclusive) {
      this.lowerInclusive = lowerInclusive;
      this.upperInclusive = upperInclusive;
    }

    boolean contains(int n) {
      return lowerInclusive <= n && n <= upperInclusive;
    }

    static Interval fromString(String range) {
      String[] split = range.split("-");
      return new Interval(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
  }
}
