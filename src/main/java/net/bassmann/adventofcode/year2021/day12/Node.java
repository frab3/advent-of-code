package net.bassmann.adventofcode.year2021.day12;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

  private final String name;
  private final Set<Node> connections = new HashSet<>();
  private final boolean mulitNode;
  private final boolean startNode;
  private final boolean endNode;

  public Node(String name) {
    this.name = Objects.requireNonNull(name);
    mulitNode = Character.isUpperCase(name.toCharArray()[0]);
    startNode = "start".equals(name);
    endNode = "end".equals(name);
  }

  boolean isMulitNode() {
    return mulitNode;
  }

  boolean isStartNode() {
    return startNode;
  }

  boolean isEndNode() {
    return endNode;
  }

  public Set<Node> getConnections() {
    return connections;
  }

  public void addConnection(Node node) {
    connections.add(node);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Node node = (Node) o;

    return name.equals(node.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    return name;
  }
}
