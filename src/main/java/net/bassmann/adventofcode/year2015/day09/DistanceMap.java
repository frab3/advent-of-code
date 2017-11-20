package net.bassmann.adventofcode.year2015.day09;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class DistanceMap {

  private static final Comparator<String> PLACE_COMPARATOR = Comparator.comparing(s -> s);

  private final Map<String, Integer> map = new HashMap<>();
  private final Set<String> destinations = new HashSet<>();

  DistanceMap() {}

  void addFromString(String s) {
    final String[] split = s.split(" ");
    add(split[0], split[2], Integer.parseInt(split[4]));
  }

  void add(String placeA, String placeB, int distance) {
    destinations.add(placeA);
    destinations.add(placeB);
    map.put(makeKey(placeA, placeB), distance);
  }

  int getDistance(String placeA, String placeB) {
    return map.getOrDefault(makeKey(placeA, placeB), 0);
  }

  static String makeKey(String s1, String s2) {
    return (PLACE_COMPARATOR.compare(s1, s2) < 0) ? s1 + s2 : s2 + s1;
  }

  List<String> getDestinations() {
    return destinations.stream().sorted(PLACE_COMPARATOR).collect(Collectors.toList());
  }
}
