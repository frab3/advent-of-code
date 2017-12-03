package net.bassmann.adventofcode.year2017.day03;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2017Day03 extends AbstractDay {

  public Year2017Day03() {
    super(2017, 3);
  }

  @Override
  public String solvePart1() {
    Coordinates c = SquareSpiral.numberToCoordinates(getInput());
    return Integer.toString(c.getManhattanDistance());
  }

  @Override
  public String solvePart2() {
    final SquareSpiralWithNeighbourSums spiral = new SquareSpiralWithNeighbourSums();
    int solution = spiral.optimizedFindNumberGreaterThan(getInput());
    return Integer.toString(solution);
  }

  private int getInput() {
    return Integer.parseInt(getRiddleInput().firstLine());
  }
}
