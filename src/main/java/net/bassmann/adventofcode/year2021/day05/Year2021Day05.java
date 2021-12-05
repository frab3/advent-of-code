package net.bassmann.adventofcode.year2021.day05;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day05 extends AbstractDay {

  public Year2021Day05() {
    super(2021, 5);
  }

  @Override
  public String solvePart1() {
    var grid = new Grid(1000);
    getRiddleInput().lines().map(Line::new).filter(Line::isNotDiagonal).forEach(grid::add);
    var count = grid.count();
    return String.valueOf(count);
  }

  @Override
  public String solvePart2() {
    var grid = new Grid(1000);
    getRiddleInput().lines().map(Line::new).forEach(grid::add);
    var count = grid.count();
    return String.valueOf(count);
  }

  public static void main(String[] args) {
    var today = new Year2021Day05();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
