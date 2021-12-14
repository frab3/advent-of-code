package net.bassmann.adventofcode.year2021.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Year2021Day12Test {

  @Test
  void example1() {
    List<String> input = List.of("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end");

    var map = Year2021Day12.createMap(input);
    var startNode = map.getNode("start");
    var startPath = new Path(startNode);

    List<Path> allPaths = Year2021Day12.grow(startPath);

    assertEquals(10, allPaths.size());
  }

  @Test
  void example2() {
    List<String> input = List.of("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end");

    var map = Year2021Day12.createMap(input);
    var startNode = map.getNode("start");
    var startPath = new Path(startNode);

    List<Path> allPaths = Year2021Day12.grow2(startPath);

    assertEquals(36, allPaths.size());
  }
}
