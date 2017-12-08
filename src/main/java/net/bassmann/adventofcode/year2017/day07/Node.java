package net.bassmann.adventofcode.year2017.day07;

import java.util.List;

/**
 * Immutable Node Pojo; it is responsible to find out if its child nodes are balanced and what the
 * weight difference between unbalanced child nodes is.
 */
class Node {

  private final String name;
  private final int weight;
  private final List<Node> children;
  private final int childrenWeight;

  Node(String name, int weight, List<Node> children) {
    this.name = name;
    this.weight = weight;
    this.children = children;
    childrenWeight = children.stream().mapToInt(Node::getTotalWeight).sum();
  }

  String getName() {
    return name;
  }

  int getWeight() {
    return weight;
  }

  int getTotalWeight() {
    return weight + childrenWeight;
  }

  List<Node> getChildren() {
    return children;
  }

  boolean hasBalancedChildren() {
    return children.stream().mapToInt(Node::getTotalWeight).distinct().count() < 2;
  }

  Node getUnbalancedChild() {
    if (getChildren().isEmpty()) {
      return null;
    }

    final double average =
        getChildren()
            .stream()
            .mapToInt(Node::getTotalWeight)
            .average()
            .orElseThrow(IllegalStateException::new);

    return getChildren()
        .stream()
        .filter(n -> n.getTotalWeight() > average)
        .findFirst()
        .orElse(null);
  }

  int getChildrenWeightDifference() {
    int[] distinctWeights =
        getChildren().stream().mapToInt(Node::getTotalWeight).distinct().sorted().toArray();
    return distinctWeights[1] - distinctWeights[0];
  }
}
