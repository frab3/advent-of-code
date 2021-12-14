package net.bassmann.adventofcode.year2021.day08;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day08 extends AbstractDay {

  Year2021Day08() {
    super(2021, 8);
  }

  @Override
  public String solvePart1() {
    int sum = getRiddleInput().lines().mapToInt(SignalDecoder::countEasyOutputValues).sum();
    return String.valueOf(sum);
  }

  @Override
  public String solvePart2() {
    int sum = getRiddleInput().lines().mapToInt(SignalDecoder::decode).sum();
    return String.valueOf(sum);
  }

  public static void main(String[] args) {
    var today = new Year2021Day08();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
