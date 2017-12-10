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
import net.bassmann.adventofcode.year2015.day09.Year2015Day09;
import net.bassmann.adventofcode.year2015.day10.Year2015Day10;
import net.bassmann.adventofcode.year2015.day11.Year2015Day11;
import net.bassmann.adventofcode.year2015.day12.Year2015Day12;
import net.bassmann.adventofcode.year2015.day13.Year2015Day13;
import net.bassmann.adventofcode.year2015.day14.Year2015Day14;
import net.bassmann.adventofcode.year2015.day15.Year2015Day15;
import net.bassmann.adventofcode.year2015.day16.Year2015Day16;
import net.bassmann.adventofcode.year2015.day17.Year2015Day17;
import net.bassmann.adventofcode.year2015.day18.Year2015Day18;
import net.bassmann.adventofcode.year2015.day19.Year2015Day19;
import net.bassmann.adventofcode.year2017.day01.Year2017Day01;
import net.bassmann.adventofcode.year2017.day02.Year2017Day02;
import net.bassmann.adventofcode.year2017.day03.Year2017Day03;
import net.bassmann.adventofcode.year2017.day04.Year2017Day04;
import net.bassmann.adventofcode.year2017.day05.Year2017Day05;
import net.bassmann.adventofcode.year2017.day06.Year2017Day06;
import net.bassmann.adventofcode.year2017.day07.Year2017Day07;
import net.bassmann.adventofcode.year2017.day08.Year2017Day08;
import net.bassmann.adventofcode.year2017.day09.Year2017Day09;
import net.bassmann.adventofcode.year2017.day10.Year2017Day10;

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
          new Year2015Day08(),
          new Year2015Day09(),
          new Year2015Day10(),
          new Year2015Day11(),
          new Year2015Day12(),
          new Year2015Day13(),
          new Year2015Day14(),
          new Year2015Day15(),
          new Year2015Day16(),
          new Year2015Day17(),
          new Year2015Day18(),
          new Year2015Day19(),
          new Year2017Day01(),
          new Year2017Day02(),
          new Year2017Day03(),
          new Year2017Day04(),
          new Year2017Day05(),
          new Year2017Day06(),
          new Year2017Day07(),
          new Year2017Day08(),
          new Year2017Day09(),
          new Year2017Day10());

  public static void main(String[] args) {
    Day currentDay = days.get(days.size() - 1);
    System.out.println("Solution for " + currentDay.getDate().toString());
    System.out.println("Part 1: " + currentDay.solvePart1());
    System.out.println("Part 2: " + currentDay.solvePart2());
  }
}
