package net.bassmann.adventofcode.year2021.day12;

import java.util.HashMap;
import java.util.Map;

public class NodeMap {

  private final Map<String, Node> map = new HashMap<>();

  public void addNodesFromLine(String line) {
    int s = line.indexOf('-');
    String name1 = line.substring(0, s);
    String name2 = line.substring(s + 1);
    Node n1 = map.computeIfAbsent(name1, Node::new);
    Node n2 = map.computeIfAbsent(name2, Node::new);
    if (!(n1.isEndNode() || n2.isStartNode())) {
      n1.addConnection(n2);
    }
    if (!(n2.isEndNode() || n1.isStartNode())) {
      n2.addConnection(n1);
    }
  }

  Node getNode(String name) {
    return map.get(name);
  }
}
