package net.bassmann.adventofcode.year2015.day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 17: No Such Thing as Too Much.
 *
 * <p>The elves bought too much eggnog again - 150 liters this time. To fit it all into your
 * refrigerator, you'll need to move it into smaller containers. You take an inventory of the
 * capacities of the available containers.
 *
 * <p>For example, suppose you have containers of size 20, 15, 10, 5, and 5 liters. If you need to
 * store 25 liters, there are four ways to do it:
 *
 * <ul>
 *   <li>15 and 10
 *   <li>20 and 5 (the first 5)
 *   <li>20 and 5 (the second 5)
 *   <li>15, 5, and 5
 * </ul>
 *
 * <h2>Part One</h2>
 *
 * Filling all containers entirely, how many different combinations of containers can exactly fit
 * all 150 liters of eggnog?
 *
 * <h2>Part Two</h2>
 *
 * While playing with all the containers in the kitchen, another load of eggnog arrives! The
 * shipping and receiving department is requesting as many containers as you can spare.
 *
 * <p>Find the minimum number of containers that can exactly fit all 150 liters of eggnog. How many
 * different ways can you fill that number of containers and still hold exactly 150 litres?
 *
 * <p>In the example above, the minimum number of containers was two. There were three ways to use
 * that many containers, and so the answer there would be 3.
 */
public class Year2015Day17 extends AbstractDay {

  private static final int EGGNOG = 150;

  private List<Container> containers;

  public Year2015Day17() {
    super(2015, 17);
  }

  @Override
  public String solvePart1() {
    final List<Container> containerList = getContainers();
    int combinations = countPossibleCombinations(containerList, 0, EGGNOG);
    return Integer.toString(combinations);
  }

  @Override
  public String solvePart2() {
    final List<Container> containerList = getContainers();
    final List<Set<Container>> allCombinations =
        fillContainers(containerList, new HashSet<>(), 0, EGGNOG);
    final int min = getMinimum(allCombinations);
    final long minCount = countOfSize(allCombinations, min);
    return Long.toString(minCount);
  }

  private List<Container> getContainers() {
    if (containers == null) {
      containers =
          getRiddleInput()
              .lines()
              .mapToInt(Integer::parseInt)
              .mapToObj(Container::new)
              .collect(Collectors.toList());
    }
    return containers;
  }

  /**
   * Part one can be solved by simply counting the possible combinations recursively.
   *
   * @param containers the list of all available containers.
   * @param startIndex we skip the containers, we already looked at.
   * @param remainingEggnog if we already filled a container, we need to tell the recursion how much
   *     eggnog is left, that we need to fill.
   * @return {@code 1} if it found a combination of containers, that can take the specified eggnog,
   *     {@code 0} otherwise.
   */
  static int countPossibleCombinations(
      List<Container> containers, int startIndex, int remainingEggnog) {
    if (remainingEggnog == 0) {
      return 1;
    }
    if (remainingEggnog < 0 || startIndex >= containers.size()) {
      return 0;
    }
    Container c = containers.get(startIndex);
    return countPossibleCombinations(containers, startIndex + 1, remainingEggnog - c.getCapacity())
        + countPossibleCombinations(containers, startIndex + 1, remainingEggnog);
  }

  /**
   * This solves part two, by actually building a list of all possible container combinations. So we
   * can find the minimum amount of containers needed, but also count how often we can just fill the
   * minimum amount of containers.
   */
  static List<Set<Container>> fillContainers(
      List<Container> containers, Set<Container> currentSet, int startIndex, int remainingEggnog) {
    final List<Set<Container>> list = new ArrayList<>();
    if (remainingEggnog == 0) {
      list.add(currentSet);
      return list;
    }
    if (remainingEggnog < 0 || startIndex >= containers.size()) {
      return list;
    }
    Container c = containers.get(startIndex);
    Set<Container> newSet = new HashSet<>(currentSet);
    currentSet.add(c);
    List<Set<Container>> result =
        fillContainers(containers, currentSet, startIndex + 1, remainingEggnog - c.getCapacity());
    result.addAll(fillContainers(containers, newSet, startIndex + 1, remainingEggnog));
    return result;
  }

  static int getMinimum(List<Set<Container>> allCombinations) {
    return allCombinations.stream().mapToInt(Set::size).min().orElse(-1);
  }

  static long countOfSize(List<Set<Container>> allCombinations, int size) {
    return allCombinations.stream().filter(s -> s.size() == size).count();
  }
}
