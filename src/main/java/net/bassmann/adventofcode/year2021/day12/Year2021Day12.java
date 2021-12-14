package net.bassmann.adventofcode.year2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day12 extends AbstractDay {

  Year2021Day12() {
    super(2021, 12);
  }

  @Override
  public String solvePart1() {
    var map = createMap(getRiddleInput().asList());
    var startNode = map.getNode("start");
    var startPath = new Path(startNode);

    List<Path> allPaths = grow(startPath);

    return String.valueOf(allPaths.size());
  }

  @Override
  public String solvePart2() {
    var map = createMap(getRiddleInput().asList());
    var startNode = map.getNode("start");
    var startPath = new Path(startNode);

    List<Path> allPaths = grow2(startPath);

    return String.valueOf(allPaths.size());
  }

  static List<Path> grow(Path startPath) {
    //    System.out.println(startPath);
    List<Path> completePaths = new ArrayList<>();
    Set<Node> nodesToAppend = startPath.getLast().getConnections();
    for (Node candidate : nodesToAppend) {
      if (startPath.canAdd(candidate)) {
        Path p = startPath.copy().add(candidate);
        if (p.isComplete()) {
          completePaths.add(p);
        } else {
          completePaths.addAll(grow(p));
        }
      }
    }
    return completePaths;
  }

  static List<Path> grow2(Path startPath) {
    List<Path> completePaths = new ArrayList<>();
    Set<Node> nodesToAppend = startPath.getLast().getConnections();
    for (Node candidate : nodesToAppend) {
      //System.out.println("candidate " + candidate);
      if (startPath.canAddWithDuplicate(candidate)) {
        Path p = startPath.copy().add(candidate);
        if (p.isComplete()) {
          completePaths.add(p);
        } else {
          completePaths.addAll(grow2(p));
        }
      }
    }
    return completePaths;
  }

  static NodeMap createMap(List<String> input) {
    NodeMap map = new NodeMap();
    input.forEach(map::addNodesFromLine);
    return map;
  }

  public static void main(String[] args) {
    var today = new Year2021Day12();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
