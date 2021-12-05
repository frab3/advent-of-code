package net.bassmann.adventofcode.year2020.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class Year2020Day06Test {

  private final List<String> testInput =
      List.of("abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "a", "", "b");

  private Year2020Day06 today = new Year2020Day06();

  @Test
  void findGroups() {
    List<List<String>> groups = today.findGroups(testInput);
    assertEquals(5, groups.size());

    int[] counts = groups.stream().mapToInt(today::countYesAnswers).toArray();
    System.out.println(Arrays.toString(counts));
  }

  @Test
  void countYesAnswers() {}
}
