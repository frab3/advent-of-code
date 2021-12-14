package net.bassmann.adventofcode.year2021.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Sheet {

  private final Set<Point> points = new HashSet<>();

  Sheet add(Point p) {
    points.add(p);
    return this;
  }

  int countPoints() {
    return points.size();
  }

  void foldAlongX(int x) {
    var it = points.iterator();
    List<Point> foldedPoints = new ArrayList<>();
    while (it.hasNext()) {
      Point p = it.next();
      if (p.getX() > x) {
        int diff = p.getX() - x;
        int newX = x - diff;
        it.remove();
        foldedPoints.add(new Point(newX, p.getY()));
      }
    }
    points.addAll(foldedPoints);
  }

  void foldAlongY(int y) {
    var it = points.iterator();
    List<Point> foldedPoints = new ArrayList<>();
    while (it.hasNext()) {
      Point p = it.next();
      if (p.getY() > y) {
        int diff = p.getY() - y;
        int newY = y - diff;
        it.remove();
        foldedPoints.add(new Point(p.getX(), newY));
      }
    }
    points.addAll(foldedPoints);
  }

  @Override
  public String toString() {
    int maxX = points.stream().mapToInt(Point::getX).max().orElse(-1);
    int maxY = points.stream().mapToInt(Point::getY).max().orElse(-1);
    List<StringBuilder> builders = new ArrayList<>(maxY);
    for (int y = 0; y <= maxY; y++) {
      var sb = new StringBuilder(maxX);
      for (int x = 0; x<= maxX; x++) {
        sb.append(' ');
      }
      builders.add(sb);
    }
    points.forEach(p -> builders.get(p.getY()).setCharAt(p.getX(), '#'));
    return builders.stream().map(b -> b.toString()).collect(Collectors.joining("\n"));
  }
}
