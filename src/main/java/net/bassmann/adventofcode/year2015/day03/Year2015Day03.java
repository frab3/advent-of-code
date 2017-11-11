package net.bassmann.adventofcode.year2015.day03;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 3: Perfectly Spherical Houses in a Vacuum.
 *
 * <p>Santa is delivering presents to an infinite two-dimensional grid of houses.
 *
 * <p>He begins by delivering a present to the house at his starting location, and then an elf at
 * the North Pole calls him via radio and tells him where to move next. Moves are always exactly one
 * house to the north (^), south (v), east (>), or west (<). After each move, he delivers another
 * present to the house at his new location.
 *
 * <p>However, the elf back at the north pole has had a little too much eggnog, and so his
 * directions are a little off, and Santa ends up visiting some houses more than once.
 *
 * <h2>Part 1</h2>
 *
 * How many houses receive at least one present?
 */
public class Year2015Day03 extends AbstractDay {

  public Year2015Day03() {
    super(2015, 3);
  }

  @Override
  public String solvePart1() {
    HouseGrid hg = new HouseGrid();
    hg.deliverPresents(getRiddleInput().firstLine());
    int housesVisited = hg.countHousesVisited();
    return Integer.toString(housesVisited);
  }

  @Override
  public String solvePart2() {
    HouseGrid hg = new HouseGrid();
    hg.deliverPresentsUsingRoboSanta(getRiddleInput().firstLine());
    int housesVisited = hg.countHousesVisited();
    return Integer.toString(housesVisited);
  }
}
