package net.bassmann.adventofcode.year2021.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2021Day05Test {

  List<String> input =
      List.of(
          "0,9 -> 5,9",
          "8,0 -> 0,8",
          "9,4 -> 3,4",
          "2,2 -> 2,1",
          "7,0 -> 7,4",
          "6,4 -> 2,0",
          "0,9 -> 2,9",
          "3,4 -> 1,4",
          "0,0 -> 8,8",
          "5,5 -> 8,2");

  @Test
  void example1Test() {
    Grid g = new Grid(10);
    input.stream().map(Line::new).filter(Line::isNotDiagonal).forEach(g::add);
    var actual = g.count();
    assertEquals(5, actual);
  }

  @Test
  void example2Test() {
    Grid g = new Grid(10);
    input.stream()
        .map(Line::new)
        .forEach(
            line -> {
              System.out.println("Adding line: " + line);
              g.add(line);
              System.out.println(g);
            });
    var actual = g.count();
    assertEquals(12, actual);
  }
}
