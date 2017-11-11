package net.bassmann.adventofcode;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import net.bassmann.adventofcode.year2015.day01.Year2015Day01;
import net.bassmann.adventofcode.year2015.day02.Year2015Day02;

public class Main {

  private static final List<Day> days = List.of(new Year2015Day01(), new Year2015Day02());

  public static void main(String[] args) {
    Day currentDay = days.get(days.size() - 1);
    System.out.println("Part 1: " + currentDay.solvePart1());
    System.out.println("Part 2: " + currentDay.solvePart2());
  }
}
