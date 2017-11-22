package net.bassmann.adventofcode.year2015.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class ReindeerRace {

  private final List<Reindeer> reindeers;
  private final Map<String, Integer> points = new HashMap<>();

  ReindeerRace(List<Reindeer> reindeers) {
    this.reindeers = reindeers;
    reindeers.forEach(r -> points.put(r.getName(), 0));
  }

  /**
   * Check who is in lead after each second and awards points to reindeers in lead. Returns the most
   * points after the given seconds.
   */
  int getMaxPointsAfter(int second) {
    IntStream.rangeClosed(1, second)
        .mapToObj(this::getWinners)
        .flatMap(List::stream)
        .forEach(this::awardPoint);
    return points.values().stream().mapToInt(Integer::valueOf).max().orElse(0);
  }

  private void awardPoint(Reindeer reindeer) {
    points.put(reindeer.getName(), points.get(reindeer.getName()) + 1);
  }

  private List<Reindeer> getWinners(int second) {
    int maxDist = 0;
    List<Reindeer> winners = new ArrayList<>();
    for (Reindeer r : reindeers) {
      int dist = r.getDistanceAfterSeconds(second);
      if (dist == maxDist) {
        winners.add(r);
      } else if (dist > maxDist) {
        maxDist = dist;
        winners.clear();
        winners.add(r);
      }
    }
    return winners;
  }
}
