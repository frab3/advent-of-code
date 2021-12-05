package net.bassmann.adventofcode.year2020.day06;

import java.util.ArrayList;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day06 extends AbstractDay {

  public Year2020Day06() {
    super(2020, 6);
  }

  @Override
  public String solvePart1() {
    List<List<String>> groups = findGroups(getRiddleInput().asList());
    int sum = groups.stream().mapToInt(this::countYesAnswers).sum();
    return Integer.toString(sum);
  }

  @Override
  public String solvePart2() {
    List<List<String>> groups = findGroups(getRiddleInput().asList());
    int sum = groups.stream().mapToInt(this::countEverbodyYesAnswers).sum();
    return Integer.toString(sum);
  }

  List<List<String>> findGroups(List<String> input) {
    List<List<String>> groups = new ArrayList<>();
    List<String> group = new ArrayList<>();
    for (String s : input) {
      if (s.isEmpty()) {
        groups.add(group);
        group = new ArrayList<>();
      } else {
        group.add(s);
      }
    }
    groups.add(group);
    return groups;
  }

  int countYesAnswers(List<String> group) {
    final boolean[] yes = new boolean[26];
    group.stream().flatMapToInt(String::chars).forEach(c -> yes[c - 'a'] = true);
    int c = 0;
    for (boolean y : yes) {
      if (y) c++;
    }
    return c;
  }

  int countEverbodyYesAnswers(List<String> group) {
    final int[] yes = new int[26];
    group.stream().flatMapToInt(String::chars).forEach(c -> yes[c - 'a']++);
    int c = 0;
    for (int y : yes) {
      if (y == group.size()) c++;
    }
    return c;
  }


  public static void main(String[] args) {
    Year2020Day06 today = new Year2020Day06();

    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
