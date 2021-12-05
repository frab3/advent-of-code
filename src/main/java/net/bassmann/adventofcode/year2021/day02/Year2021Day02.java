package net.bassmann.adventofcode.year2021.day02;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day02 extends AbstractDay {

  protected Year2021Day02() {
    super(2021, 2);
  }

  @Override
  public String solvePart1() {
    Submarine sub = new Submarine();
    getRiddleInput().lines().forEach(sub::parseCommand);
    return String.valueOf(sub.depth * sub.position);
  }

  @Override
  public String solvePart2() {
    Submarine sub = new Submarine();
    getRiddleInput().lines().forEach(sub::parseCommand2);
    return String.valueOf(sub.depth * sub.position);  }

  public static void main(String[] args) {
    var today = new Year2021Day02();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
