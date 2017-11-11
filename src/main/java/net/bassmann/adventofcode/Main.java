package net.bassmann.adventofcode;

import net.bassmann.adventofcode.common.Day;
import net.bassmann.adventofcode.year2015.day01.Year2015Day01;

public class Main {

  public static void main(String[] args) {
    Day day = new Year2015Day01();
    System.out.println("Part 1: " + day.solvePart1());
    System.out.println("Part 2: " + day.solvePart2());
  }
}
