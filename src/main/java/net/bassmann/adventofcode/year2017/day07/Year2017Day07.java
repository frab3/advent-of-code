package net.bassmann.adventofcode.year2017.day07;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 7: Recursive Circus.
 *
 * <p>Wandering further through the circuits of the computer, you come upon a tower of programs that
 * have gotten themselves into a bit of trouble. A recursive algorithm has gotten out of hand, and
 * now they're balanced precariously in a large tower.
 *
 * <p>One program at the bottom supports the entire tower. It's holding a large disc, and on the
 * disc are balanced several more sub-towers. At the bottom of these sub-towers, standing on the
 * bottom disc, are other programs, each holding their own disc, and so on. At the very tops of
 * these sub-sub-sub-...-towers, many programs stand simply keeping the disc below them balanced but
 * with no disc of their own.
 *
 * <p>You offer to help, but first you need to understand the structure of these towers. You ask
 * each program to yell out their name, their weight, and (if they're holding a disc) the names of
 * the programs immediately above them balancing on that disc. You write this information down (your
 * puzzle input). Unfortunately, in their panic, they don't do this in an orderly fashion; by the
 * time you're done, you're not sure which program gave which information.
 *
 * <p>For example, if your list is the following:
 *
 * <pre>
 * pbga (66)
 * xhth (57)
 * ebii (61)
 * havc (66)
 * ktlj (57)
 * fwft (72) -> ktlj, cntj, xhth
 * qoyq (66)
 * padx (45) -> pbga, havc, qoyq
 * tknk (41) -> ugml, padx, fwft
 * jptl (61)
 * ugml (68) -> gyxo, ebii, jptl
 * gyxo (61)
 * cntj (57)
 * </pre>
 *
 * ...then you would be able to recreate the structure of the towers that looks like this:
 *
 * <pre>
 * #                 gyxo
 * #               /
 * #          ugml - ebii
 * #        /      \
 * #       |         jptl
 * #       |
 * #       |         pbga
 * #      /        /
 * # tknk --- padx - havc
 * #      \        \
 * #       |         qoyq
 * #       |
 * #       |         ktlj
 * #        \      /
 * #          fwft - cntj
 * #               \
 * #                 xhth
 * </pre>
 *
 * In this example, tknk is at the bottom of the tower (the bottom program), and is holding up ugml,
 * padx, and fwft. Those programs are, in turn, holding up other programs; in this example, none of
 * those programs are holding up any other programs, and are all the tops of their own towers. (The
 * actual tower balancing in front of you is much larger.)
 *
 * <h2>Part One</h2>
 *
 * Before you're ready to help them, you need to make sure your information is correct. What is the
 * name of the bottom program?
 *
 * <h2>Part Two</h2>
 *
 * The programs explain the situation: they can't get down. Rather, they could get down, if they
 * weren't expending all of their energy trying to keep the tower balanced. Apparently, one program
 * has the wrong weight, and until it's fixed, they're stuck here.
 *
 * <p>For any program holding a disc, each program standing on that disc forms a sub-tower. Each of
 * those sub-towers are supposed to be the same weight, or the disc itself isn't balanced. The
 * weight of a tower is the sum of the weights of the programs in that tower.
 *
 * <p>In the example above, this means that for ugml's disc to be balanced, gyxo, ebii, and jptl
 * must all have the same weight, and they do: 61.
 *
 * <p>However, for tknk to be balanced, each of the programs standing on its disc and all programs
 * above it must each match. This means that the following sums must all be the same:
 *
 * <ul>
 * <li>ugml + (gyxo + ebii + jptl) = 68 + (61 + 61 + 61) = 251
 * <li>padx + (pbga + havc + qoyq) = 45 + (66 + 66 + 66) = 243
 * <li>fwft + (ktlj + cntj + xhth) = 72 + (57 + 57 + 57) = 243
 * </ul>
 *
 * As you can see, tknk's disc is unbalanced: ugml's stack is heavier than the other two. Even
 * though the nodes above ugml are balanced, ugml itself is too heavy: it needs to be 8 units
 * lighter for its stack to weigh 243 and keep the towers balanced. If this change were made, its
 * weight would be 60.
 *
 * <p>Given that exactly one program is the wrong weight, what would its weight need to be to
 * balance the entire tower?
 */
public class Year2017Day07 extends AbstractDay {

  private String rootName = null;

  public Year2017Day07() {
    super(2017, 7);
  }

  @Override
  public String solvePart1() {
    return getRootName();
  }

  @Override
  public String solvePart2() {
    int balancedWeight = buildTreeAndSolve(getRootName(), getRiddleInput().asList());
    return Integer.toString(balancedWeight);
  }

  private String getRootName() {
    if (rootName == null) {
      List<String> lines = getRiddleInput().asList();
      rootName = findRootNode(lines);
    }
    return rootName;
  }

  /**
   * Simple algorithm to find the root node. Simply record all node names. Also keep a set of all
   * names, that are child nodes. The named node, which is not in the set of child notes is the root
   * node.
   *
   * @param input the riddle input
   * @return the name of the root node.
   */
  static String findRootNode(List<String> input) {
    Set<String> rootNodes = new HashSet<>();
    Set<String> childNodes = new HashSet<>();

    input
        .stream()
        .filter(s -> s.contains(" -> "))
        .forEach(
            s -> {
              rootNodes.add(parseNodeName(s));
              childNodes.addAll(parseChildNames(s));
            });

    return rootNodes.stream().filter(root -> !childNodes.contains(root)).findAny().orElse("");
  }

  static int buildTreeAndSolve(String rootName, List<String> input) {
    Map<String, String> inputMap =
        input.stream().collect(toMap(Year2017Day07::parseNodeName, identity()));

    Node root = buildNodeWithSubtree(rootName, inputMap);

    return findUnbalancedChildAndReturnBalancedValue(root);
  }

  /**
   * Recursively build a tree, depth first: When building a node, recursively build its children
   * first.
   *
   * @param name name of the node to build.
   * @param inputMap a map of node names to input lines.
   * @return a node, which can have child nodes; the root of the subtree.
   */
  private static Node buildNodeWithSubtree(String name, Map<String, String> inputMap) {
    String line = inputMap.get(name);
    int weight = parseWeight(line);

    List<Node> children =
        parseChildNames(line)
            .stream()
            .map(n -> buildNodeWithSubtree(n, inputMap))
            .collect(Collectors.toList());

    return new Node(name, weight, children);
  }

  /**
   * Recursively search for the node, which is too heavy for the tree to be balanced and return the
   * value it should have to be balanced. This is kind of a breadth-first search approach.
   *
   * @param node the root of the current subtree we are looking at.
   * @return the value the unbalanced node should have to be balanced.
   */
  private static int findUnbalancedChildAndReturnBalancedValue(Node node) {
    Node unbalancedNode = node.getUnbalancedChild();

    if (unbalancedNode.hasBalancedChildren()) {
      int difference = node.getChildrenWeightDifference();
      return unbalancedNode.getWeight() - difference;
    } else {
      return findUnbalancedChildAndReturnBalancedValue(unbalancedNode);
    }
  }

  /* --------------------------------------------------------------------- *
   * Parser functions
   * --------------------------------------------------------------------- */
  static String parseNodeName(String line) {
    return line.split(" ")[0];
  }

  static int parseWeight(String line) {
    final int openBrace = line.indexOf('(');
    final int closeBrace = line.indexOf(')', openBrace);
    return Integer.parseInt(line.substring(openBrace + 1, closeBrace));
  }

  static List<String> parseChildNames(String line) {
    String[] split = line.split(" -> ");
    if (split.length == 1) {
      return Collections.emptyList();
    }
    String[] childNames = split[1].split(", ");
    return Arrays.asList(childNames);
  }
}
