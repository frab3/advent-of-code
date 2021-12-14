package net.bassmann.adventofcode.year2021.day12;

import java.util.ArrayList;
import java.util.List;

public class Path {

  private final List<Node> nodesInPath = new ArrayList<>();
  private boolean hasDuplicate = false;

  public Path(Node startNode) {
    nodesInPath.add(startNode);
  }

  private Path(Path other) {
    nodesInPath.addAll(other.nodesInPath);
    this.hasDuplicate = other.hasDuplicate;
  }

  boolean contains(Node n) {
    return nodesInPath.contains(n);
  }

  boolean canAdd(Node n) {
    return n.isMulitNode() || !contains(n);
  }

  boolean canAddWithDuplicate(Node n) {
    return n.isMulitNode() || !hasDuplicate || !contains(n);
  }

  Path add(Node n) {
    if (contains(n) && !n.isMulitNode()) {
      //System.out.println("Setting duplicate for " + this + " + " + n);
      hasDuplicate = true;
    }
    nodesInPath.add(n);
    return this;
  }

  Node getLast() {
    return nodesInPath.get(nodesInPath.size() - 1);
  }

  boolean isComplete() {
    return !nodesInPath.isEmpty() && getLast().isEndNode();
  }

  Path copy() {
    return new Path(this);
  }

  @Override
  public String toString() {
    return "Path{" + "nodesInPath=" + nodesInPath + '}';
  }
}
