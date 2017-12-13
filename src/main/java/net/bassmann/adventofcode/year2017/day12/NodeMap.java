package net.bassmann.adventofcode.year2017.day12;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class NodeMap {

  private static final int NO_GROUP = -1;
  private final List<Integer> nodesList;

  NodeMap(int size) {
    nodesList = IntStream.range(0, size).map(i -> NO_GROUP).boxed().collect(Collectors.toList());
  }

  void addNodesToGroup(Collection<Integer> nodes, int group) {
    nodes.forEach(n -> nodesList.set(n, group));
  }

  int getGroupOfNode(int nodeIndex) {
    return nodesList.get(nodeIndex);
  }

  List<Integer> getGroupsOfNodes(Collection<Integer> nodes) {
    return nodes
        .stream()
        .map(this::getGroupOfNode)
        .filter(this::isGroup)
        .sorted()
        .distinct()
        .collect(Collectors.toList());
  }

  private boolean isGroup(int group) {
    return group != NO_GROUP;
  }

  List<Integer> getNodesOfGroups(Collection<Integer> groups) {
    return groups.stream().flatMap(this::getNodesOfGroup).collect(Collectors.toList());
  }

  private Stream<Integer> getNodesOfGroup(int group) {
    return IntStream.range(0, nodesList.size()).filter(i -> getGroupOfNode(i) == group).boxed();
  }

  int countNodesInGroup(int group) {
    return (int) nodesList.stream().filter(g -> g.equals(group)).count();
  }

  int countGroups() {
    return (int) nodesList.stream().mapToInt(Integer::intValue).distinct().count();
  }
}
