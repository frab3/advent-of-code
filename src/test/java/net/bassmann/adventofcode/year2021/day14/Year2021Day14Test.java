package net.bassmann.adventofcode.year2021.day14;

import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.count2;
import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.createCountMap;
import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.createLinkedList;
import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.createRules;
import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.createRules2;
import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.grow;
import static net.bassmann.adventofcode.year2021.day14.Year2021Day14.grow2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class Year2021Day14Test {

  @Test
  void example1() {
    var list = createLinkedList("NNCB");
    var rules =
        createRules(
            List.of(
                "CH -> B", "HH -> N", "CB -> H", "NH -> C", "HB -> C", "HC -> B", "HN -> C",
                "NN -> C", "BH -> H", "NC -> B", "NB -> B", "BN -> B", "BB -> N", "BC -> B",
                "CC -> N", "CN -> C"));

    assertEquals("NNCB", list.toString());
    grow(list, rules);
    assertEquals("NCNBCHB", list.toString());
    grow(list,rules);
    assertEquals("NBCCNBBBCBHCB", list.toString());
    for (int s = 3; s <= 10; s++) {
      grow(list,rules);
    }
    assertEquals(1749, list.max());
    assertEquals(161, list.min());
  }

  @Test
  void example1Method2() {

    var countMap = createCountMap("NNCB");
    var rules = createRules2(List.of(
        "CH -> B", "HH -> N", "CB -> H", "NH -> C", "HB -> C", "HC -> B", "HN -> C",
        "NN -> C", "BH -> H", "NC -> B", "NB -> B", "BN -> B", "BB -> N", "BC -> B",
        "CC -> N", "CN -> C"));

    var count = count2(countMap, 'N');
    assertEquals(2, count['N' - 'A']);
    assertEquals(1, count['C' - 'A']);
    assertEquals(1, count['B' - 'A']);

    // 1. iteration
    countMap = grow2(countMap, rules);
    count = count2(countMap, 'N');
    assertEquals(2, count['N' - 'A']);
    assertEquals(2, count['C' - 'A']);
    assertEquals(1, count['H' - 'A']);
    assertEquals(2, count['B' - 'A']);

    for (int s = 1; s <= 10; s++) {
      countMap = grow2(countMap, rules);
    }


    long min = Arrays.stream(count).filter(i -> i > 0).min().orElseThrow();
    long max = Arrays.stream(count).filter(i -> i > 0).max().orElseThrow();
    //return String.valueOf(max - min);
  }
}
