package net.bassmann.adventofcode.year2015.day16;

import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;

class AuntSue {

  private final int number;
  private final Map<String, Integer> properties = new HashMap<>();

  AuntSue(int number) {
    this.number = number;
  }

  void addPropery(String property, int amount) {
    properties.put(property, amount);
  }

  int getNumber() {
    return number;
  }

  /**
   * Checks if this aunt sue matches a given property, also returns {@code true} if the property is
   * unknown for this aunt Sue.
   */
  boolean matches(String property, int amount) {
    return !properties.containsKey(property) || properties.get(property) == amount;
  }

  boolean matchesScan(Map<String, Integer> scan) {
    return properties
        .entrySet()
        .stream()
        .allMatch(prop -> prop.getValue().equals(scan.get(prop.getKey())));
  }

  boolean matchesScanRanges(Map<String, Integer> scan) {
    return properties
        .entrySet()
        .stream()
        .allMatch(prop -> rangeMatch(prop.getKey(), prop.getValue(), scan.get(prop.getKey())));
  }

  private boolean rangeMatch(String property, int propertyValue, int scanValue) {
    switch (property) {
      case "cats":
      case "trees":
        return propertyValue > scanValue;
      case "pomerians":
      case "goldfish":
        return propertyValue < scanValue;
      default:
        return propertyValue == scanValue;
    }
  }

  static AuntSue fromString(String input) {
    String[] split = input.split(" ");
    final int number = parseInt(dropLastChar(split[1]));
    AuntSue sue = new AuntSue(number);
    sue.addPropery(dropLastChar(split[2]), parseInt(dropLastChar(split[3])));
    sue.addPropery(dropLastChar(split[4]), parseInt(dropLastChar(split[5])));
    sue.addPropery(dropLastChar(split[6]), parseInt(split[7]));
    return sue;
  }

  private static String dropLastChar(String s) {
    return s.substring(0, s.length() - 1);
  }
}
