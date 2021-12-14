package net.bassmann.adventofcode.year2021.day14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day14 extends AbstractDay {

  Year2021Day14() {
    super(2021, 14);
  }

  @Override
  public String solvePart1() {
    var input = getRiddleInput().asList();

    var list = createLinkedList(input.get(0));
    var rules = createRules(input.subList(2, input.size()));

    for (int s = 1; s <= 10; s++) {
      grow(list, rules);
    }

    return String.valueOf(list.max() - list.min());
  }

  static StringLinkedList createLinkedList(String input) {
    var ll = new StringLinkedList();
    for (int i = 0; i < input.length(); i++) {
      ll.add(input.substring(i, i + 1));
    }
    return ll;
  }

  static void grow(StringLinkedList list, Map<String, String> rules) {
    var e = list.getFirst();
    while (e.hasNext()) {
      StringBuilder pair = new StringBuilder();
      pair.append(e.getValue());
      e = e.next();
      pair.append(e.getValue());
      list.insertBefore(e, rules.get(pair.toString()));
    }
  }

  static Map<String, String> createRules(List<String> input) {
    Map<String, String> rules = new HashMap<>();
    input.forEach(line -> rules.put(line.substring(0, 2), line.substring(6, 7)));
    return rules;
  }

  @Override
  public String solvePart2() {
    var input = getRiddleInput().asList();

    var countMap = createCountMap(input.get(0));
    var rules = createRules2(input.subList(2, input.size()));

    for (int s = 1; s <= 40; s++) {
      countMap = grow2(countMap, rules);
    }

    var count = count2(countMap, input.get(0).charAt(0));
    long min = Arrays.stream(count).filter(i -> i > 0).min().orElseThrow();
    long max = Arrays.stream(count).filter(i -> i > 0).max().orElseThrow();
    return String.valueOf(max - min);
  }

  static long[] count2(Map<String, Long> countMap, char first) {
    long[] count = new long[26];
    count[first - 'A'] = 1; // init first char
    countMap.forEach((pair, c) -> count[pair.charAt(1) - 'A'] += c);
    return count;
  }

  static Map<String, Long> grow2(Map<String, Long> map, Map<String, List<String>> rules) {
    var newMap = new HashMap<String, Long>();
    map.forEach(
        (pair, c) -> {
          rules
              .get(pair)
              .forEach(
                  newPair -> {
                    var existing = newMap.getOrDefault(newPair, 0L);
                    newMap.put(newPair, existing + c);
                  });
        });
    return newMap;
  }

  static Map<String, Long> createCountMap(String input) {
    var map = new HashMap<String, Long>();
    for (int i = 0; i < input.length() - 1; i++) {
      var pair = input.substring(i, i + 2);
      var c = map.getOrDefault(pair, 0L) + 1;
      map.put(pair, c);
    }
    return map;
  }

  static Map<String, List<String>> createRules2(List<String> input) {
    Map<String, List<String>> rules = new HashMap<>();
    input.forEach(
        line ->
            rules.put(
                line.substring(0, 2),
                List.of(
                    line.substring(0, 1) + line.substring(6, 7),
                    line.substring(6, 7) + line.substring(1, 2))));
    return rules;
  }

  public static void main(String[] args) {
    var today = new Year2021Day14();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
