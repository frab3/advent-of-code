package net.bassmann.adventofcode.year2020.day02;

import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Day;

public class Year2020Day02 extends AbstractDay {

  public Year2020Day02() {
    super(2020, 2);
  }

  @Override
  public String solvePart1() {
    long count = getRiddleInput().lines().filter(s -> isValidPassword(s)).count();
    return Long.toString(count);
  }

  private boolean isValidPassword(String s) {
    var split = s.split(": ");
    var password = split[1];
    split = split[0].split(" ");
    var character = split[1].codePointAt(0);
    split = split[0].split("-");
    var min = Integer.parseInt(split[0]);
    var max = Integer.parseInt(split[1]);
    var count = (int) password.codePoints().filter(c -> c == character).count();
    return min <= count && count <= max;
  }

  @Override
  public String solvePart2() {
    long count = getRiddleInput().lines().filter(s -> isValidPassword2(s)).count();
    return Long.toString(count);
  }

  private boolean isValidPassword2(String s) {
    var split = s.split(": ");
    var password = split[1];
    split = split[0].split(" ");
    var character = split[1].codePointAt(0);
    split = split[0].split("-");
    var pos1 = Integer.parseInt(split[0]);
    var pos2 = Integer.parseInt(split[1]);
    var c1 = password.codePointAt(pos1 - 1);
    var c2 = password.codePointAt(pos2 - 1);
    return c1 == character && c2 != character || c1 != character && c2 == character;
  }

  public static void main(String[] args) {
    Day today = new Year2020Day02();
    System.out.println(today.solvePart2());
  }
}
