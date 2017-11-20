package net.bassmann.adventofcode.year2015.day09;

import java.util.List;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 9: All in a Single Night ---
 *
 * <p>Every year, Santa manages to deliver all of his presents in a single night.
 *
 * <p>This year, however, he has some new locations to visit; his elves have provided him the
 * distances between every pair of locations. He can start and end at any two (different) locations
 * he wants, but he must visit each location exactly once. What is the shortest distance he can
 * travel to achieve this?
 *
 * <p>For example, given the following distances:
 *
 * <pre>
 * London to Dublin = 464
 * London to Belfast = 518
 * Dublin to Belfast = 141
 * </pre>
 *
 * The possible routes are therefore:
 *
 * <pre>
 * Dublin -> London -> Belfast = 982
 * London -> Dublin -> Belfast = 605
 * London -> Belfast -> Dublin = 659
 * Dublin -> Belfast -> London = 659
 * Belfast -> Dublin -> London = 605
 * Belfast -> London -> Dublin = 982
 * </pre>
 *
 * The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this
 * example.
 *
 * <h2>Part One</h2>
 *
 * What is the distance of the shortest route?
 *
 * <h2>Part Two</h2>
 *
 * The next year, just to show off, Santa decides to take the route with the longest distance
 * instead.
 *
 * <p>He can still start and end at any two (different) locations he wants, and he still must visit
 * each location exactly once.
 *
 * <p>For example, given the distances above, the longest route would be 982 via (for example)
 * Dublin -> London -> Belfast.
 *
 * <p>What is the distance of the longest route?
 */
public class Year2015Day09 extends AbstractDay {

  private DistanceMap distanceMap;
  private int minDistance = 0;
  private int maxDistance = 0;

  public Year2015Day09() {
    super(2015, 9);
  }

  @Override
  public String solvePart1() {
    int min = getMinDistance();
    return Integer.toString(min);
  }

  @Override
  public String solvePart2() {
    int max = getMaxDistance();
    return Integer.toString(max);
  }

  private DistanceMap getDistanceMap() {
    if (distanceMap == null) {
      distanceMap = new DistanceMap();
      getRiddleInput().lines().forEach(distanceMap::addFromString);
    }
    return distanceMap;
  }

  int getMinDistance() {
    if (minDistance == 0) {
      calculate(getDistanceMap());
    }
    return minDistance;
  }

  int getMaxDistance() {
    if (maxDistance == 0) {
      calculate(getDistanceMap());
    }
    return maxDistance;
  }

  void calculate(DistanceMap map) {
    final List<String> destinations = map.getDestinations();
    final Permutator permutator = new Permutator(destinations.size());

    minDistance = Integer.MAX_VALUE;
    maxDistance = 0;

    while (permutator.hasNext()) {
      final List<Integer> p = permutator.next();

      int completeDist =
          IntStream.range(0, p.size() - 1)
              .map(i -> map.getDistance(destinations.get(p.get(i)), destinations.get(p.get(i + 1))))
              .sum();

      minDistance = Math.min(minDistance, completeDist);
      maxDistance = Math.max(maxDistance, completeDist);
    }
  }
}
