package net.bassmann.adventofcode.year2021.day05;

public class Line {

  final int x1;
  final int y1;
  final int x2;
  final int y2;

  Line(String input) {
    var split = input.split(" -> ");
    var p1 = split[0].split(",");
    var p2 = split[1].split(",");
    x1 = Integer.parseInt(p1[0]);
    y1 = Integer.parseInt(p1[1]);
    x2 = Integer.parseInt(p2[0]);
    y2 = Integer.parseInt(p2[1]);
  }

  boolean isNotDiagonal() {
    return x1 == x2 || y1 == y2;
  }

  boolean isDiagonal() {
    return !isNotDiagonal();
  }

  @Override
  public String toString() {
    return x1 + "," + y1 + " => " + x2 + "," + y2;
  }
}
