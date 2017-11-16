package net.bassmann.adventofcode;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import net.bassmann.adventofcode.year2015.day01.Year2015Day01;
import net.bassmann.adventofcode.year2015.day02.Year2015Day02;
import net.bassmann.adventofcode.year2015.day03.Year2015Day03;
import net.bassmann.adventofcode.year2015.day04.Year2015Day04;
import net.bassmann.adventofcode.year2015.day05.Year2015Day05;
import net.bassmann.adventofcode.year2015.day06.Year2015Day06;
import net.bassmann.adventofcode.year2015.day07.Year2015Day07;
import net.bassmann.adventofcode.year2015.day08.Year2015Day08;

public class Main {

  private static final List<Day> days =
      List.of(
          new Year2015Day01(),
          new Year2015Day02(),
          new Year2015Day03(),
          new Year2015Day04(),
          new Year2015Day05(),
          new Year2015Day06(),
          new Year2015Day07(),
          new Year2015Day08());

  public static void main(String[] args) {
    Day currentDay = days.get(days.size() - 1);
    System.out.println("Solution for " + currentDay.getDate().toString());
    System.out.println("Part 1: " + currentDay.solvePart1());
    System.out.println("Part 2: " + currentDay.solvePart2());
  }
}
