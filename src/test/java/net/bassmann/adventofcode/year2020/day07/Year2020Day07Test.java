package net.bassmann.adventofcode.year2020.day07;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import net.bassmann.adventofcode.year2020.day07.Year2020Day07.Rule;
import org.junit.jupiter.api.Test;

class Year2020Day07Test {

  Year2020Day07 today = new Year2020Day07();

  private final List<String> exampleInput =
      List.of(
          "light red bags contain 1 bright white bag, 2 muted yellow bags.",
          "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
          "bright white bags contain 1 shiny gold bag.",
          "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
          "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
          "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
          "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
          "faded blue bags contain no other bags.",
          "dotted black bags contain no other bags.");

  @Test
  void part1Test() {
    var rules = exampleInput.stream().map(Rule::fromLine).collect(toList());
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
        contains.stream().filter(newColor -> !containsIndirectly.contains(newColor)).forEach(containsIndirectly::add);
        //containsIndirectly.addAll(contains);
        checkedColors++;
      }
    }

    System.out.println(containsIndirectly);
    assertEquals(4, containsIndirectly.size());
  }
}
