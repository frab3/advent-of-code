package net.bassmann.adventofcode.year2015.day13;

import java.util.HashMap;
import java.util.Map;

class PartyGuest {

  private final String name;
  private final Map<String, Integer> happinessMap = new HashMap<>();

  PartyGuest(String name) {
    this.name = name;
  }

  String getName() {
    return name;
  }

  void setHappiness(String name, int happiness) {
    happinessMap.put(name, happiness);
  }

  int getHappiness(String name) {
    return happinessMap.get(name);
  }
}
