package net.bassmann.adventofcode.year2021.day13;

import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day13 extends AbstractDay {

  Year2021Day13() {
    super(2021, 13);
  }

  @Override
  public String solvePart1() {
    var input = getRiddleInput().asList();
    var sheet = new Sheet();
    input.stream()
        .filter(l -> !l.isEmpty())
        .filter(l -> !l.startsWith("f"))
        .map(Point::new)
        .forEach(sheet::add);

    var instruction = input.stream().filter(l -> l.startsWith("f")).findFirst().orElseThrow();

    applyIntruction(sheet, instruction);
    return String.valueOf(sheet.countPoints());
  }

  static void applyIntruction(Sheet s, String intstruction) {
    int sp = intstruction.indexOf('=');
    final int cord = Integer.parseInt(intstruction.substring(sp + 1));
    switch (intstruction.substring(0, sp)) {
      case "fold along x" -> s.foldAlongX(cord);
      case "fold along y" -> s.foldAlongY(cord);
      default -> throw new IllegalStateException();
    }
  }

  @Override
  public String solvePart2() {
    var input = getRiddleInput().asList();
    var sheet = new Sheet();
    input.stream()
        .filter(l -> !l.isEmpty())
        .filter(l -> !l.startsWith("f"))
        .map(Point::new)
        .forEach(sheet::add);


    input.stream().filter(l -> l.startsWith("f")).forEach(inst -> applyIntruction(sheet, inst));

    return sheet.toString();
  }

  public static void main(String[] args) {
    var today = new Year2021Day13();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
