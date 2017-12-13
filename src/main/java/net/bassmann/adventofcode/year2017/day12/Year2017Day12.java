package net.bassmann.adventofcode.year2017.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

/**
 * Day 12: Digital Plumber.
 *
 * <p>Walking along the memory banks of the stream, you find a small village that is experiencing a
 * little confusion: some programs can't communicate with each other.
 *
 * <p>Programs in this village communicate using a fixed system of <em>pipes</em>. Messages are
 * passed between programs using these pipes, but most programs aren't connected to each other
 * directly. Instead, programs pass messages between each other until the message reaches the
 * intended recipient.
 *
 * <p>For some reason, though, some of these messages aren't ever reaching their intended recipient,
 * and the programs suspect that some <span title="Yes, citizens, plumbing! It's the latest
 * invention to hit Rome!">pipes</span> are missing. They would like you to investigate.
 *
 * <p>You walk through the village and record the ID of each program and the IDs with which it can
 * communicate directly (your puzzle input). Each program has one or more programs with which it can
 * communicate, and these pipes are bidirectional; if <code>8</code> says it can communicate with
 * <code>11</code>, then <code>11</code> will say it can communicate with <code>8</code>.
 *
 * <p>You need to figure out how many programs are in the group that contains program ID <code>0
 * </code>.
 *
 * <p>For example, suppose you go door-to-door like a travelling salesman and record the following
 * list:
 *
 * <pre>
 * 0 &lt;-&gt; 2
 * 1 &lt;-&gt; 1
 * 2 &lt;-&gt; 0, 3, 4
 * 3 &lt;-&gt; 2, 4
 * 4 &lt;-&gt; 2, 3, 6
 * 5 &lt;-&gt; 6
 * 6 &lt;-&gt; 4, 5
 * </pre>
 *
 * <p>In this example, the following programs are in the group that contains program ID <code>0
 * </code>:
 *
 * <ul>
 * <li>Program <code>0</code> by definition.
 * <li>Program <code>2</code>, directly connected to program <code>0</code>.
 * <li>Program <code>3</code> via program <code>2</code>.
 * <li>Program <code>4</code> via program <code>2</code>.
 * <li>Program <code>5</code> via programs <code>6</code>, then <code>4</code>, then <code>2
 * </code>.
 * <li>Program <code>6</code> via programs <code>4</code>, then <code>2</code>.
 * </ul>
 *
 * <p>Therefore, a total of <code>6</code> programs are in this group; all but program <code>1
 * </code>, which has a pipe that connects it to itself.
 *
 * <h2>Part One</h2>
 *
 * <p><em>How many programs</em> are in the group that contains program ID <code>0</code>?
 *
 * <h2>Part Two</h2>
 *
 * <p>There are more programs than just the ones in the group containing program ID <code>0</code>.
 * The rest of them have no way of reaching that group, and still might have no way of reaching each
 * other.
 *
 * <p>A <em>group</em> is a collection of programs that can all communicate via pipes either
 * directly or indirectly. The programs you identified just a moment ago are all part of the same
 * group. Now, they would like you to determine the total number of groups.
 *
 * <p>In the example above, there were <code>2</code> groups: one consisting of programs <code>
 * 0,2,3,4,5,6</code>, and the other consisting solely of program <code>1</code>.
 *
 * <p><em>How many groups are there</em> in total?
 */
public class Year2017Day12 extends AbstractDay {

  private Solution solution;

  /**
   * Just want the numbers, so everything that is not a number is a separator.
   */
  private static final Pattern INPUT_SPLIT_PATTERN = Pattern.compile("\\D+");

  public Year2017Day12() {
    super(2017, 12);
  }

  @Override
  public String solvePart1() {
    return getSolution().getPartOne();
  }

  @Override
  public String solvePart2() {
    return getSolution().getPartTwo();
  }

  Solution getSolution() {
    if (solution == null) {
      solution = makeGroups(getRiddleInput().asList());
    }
    return solution;
  }

  static Solution makeGroups(List<String> input) {
    NodeMap nodeMap = new NodeMap(input.size());

    int groupNumber = 0;

    for (String line : input) {
      final List<Integer> nodes = parseLine(line);
      // shortcut nodes that just point to themselves:
      if (nodes.size() == 1) {
        nodeMap.addNodesToGroup(nodes, groupNumber++);
      } else {
        final List<Integer> groups = nodeMap.getGroupsOfNodes(nodes);
        final int group = groups.isEmpty() ? groupNumber++ : groups.get(0);
        if (groups.size() > 1) {
          nodes.addAll(nodeMap.getNodesOfGroups(groups.subList(1, groups.size())));
        }
        nodeMap.addNodesToGroup(nodes, group);
      }
    }

    final int zeroGroup = nodeMap.getGroupOfNode(0);
    return new Solution(nodeMap.countNodesInGroup(zeroGroup), nodeMap.countGroups());
  }

  static List<Integer> parseLine(String s) {
    return INPUT_SPLIT_PATTERN
        .splitAsStream(s)
        .mapToInt(Integer::parseInt)
        .sorted()
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
