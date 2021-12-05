package net.bassmann.adventofcode.year2020.day07;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day07 extends AbstractDay {

  public Year2020Day07() {
    super(2020, 7);
  }

  @Override
  public String solvePart1() {
    var rules = getRiddleInput().lines().map(Rule::fromLine).collect(toList());
    var containsShinyDirectly =
        rules.stream()
            .filter(r -> r.inner.contains("shiny gold bag"))
            .map(r -> r.outer)
            .collect(toList());

    int checkedColors = 0;
    final List<String> containsIndirectly = new ArrayList<>(containsShinyDirectly);
    while (checkedColors != containsIndirectly.size()) {
      final int size = containsIndirectly.size();
      for (int i = checkedColors; i < size; i++) {
        String colorToCheck = containsIndirectly.get(i);
        var contains =
            rules.stream()
                .filter(r -> r.inner.contains(colorToCheck))
                .map(r -> r.outer)
                .filter(s -> !s.equals("no other bags"))
                .collect(toList());
        // containsIndirectly.addAll(contains);
        contains.stream()
            .filter(newColor -> !containsIndirectly.contains(newColor))
            .forEach(containsIndirectly::add);
        checkedColors++;
      }
    }

    return Integer.toString(containsIndirectly.size());
  }

  @Override
  public String solvePart2() {
    var rules = getRiddleInput().lines().map(Rule::fromLine).collect(toList());

    int count = countRecursive(rules, "shiny gold");
    return Integer.toString(count);
  }

  int countRecursive(List<Rule> rules, String color) {
    if (color.equals("no other")) return 0;
    var rule = rules.stream().filter(r -> r.outer.equals(color)).findFirst().orElse(null);
    if (rule == null) return 0;

    var colors = rule.inner.split(", ");
    int sum = 0;
    for (String c : colors) {
      var cSplit = c.split(" ");
      if (cSplit[0].equals("no")) continue;
      int count = Integer.parseInt(cSplit[0]);
      sum += count + count * countRecursive(rules, cSplit[1] + " " + cSplit[2]);
    }
    return sum;
  }

  public static void main(String[] args) {
    var today = new Year2020Day07();

    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }

  static class Rule {
    final String outer;
    final String inner;

    private Rule(String outer, String inner) {
      this.outer = outer;
      this.inner = inner;
    }

    @Override
    public String toString() {
      return "Rule{" + "outer='" + outer + '\'' + ", inner='" + inner + '\'' + '}';
    }

    static Rule fromLine(String line) {
      var split = line.split(" bags contain ");
      return new Rule(split[0], split[1].substring(0, split[1].length() - 1));
    }
  }
}
