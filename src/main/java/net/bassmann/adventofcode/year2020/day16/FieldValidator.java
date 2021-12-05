package net.bassmann.adventofcode.year2020.day16;

import java.util.ArrayList;
import java.util.List;

public class FieldValidator {

  private final List<FieldRule> fieldRules = new ArrayList<>();

  boolean isValid(int number) {
    return fieldRules.stream().anyMatch(field -> field.isValid(number));
  }

  boolean isValid(Ticket ticket) {
    return ticket.values().allMatch(this::isValid);
  }

  void addFieldRule(FieldRule rule) {
    fieldRules.add(rule);
  }

  FieldRule getRule(int index) {
    return fieldRules.get(index);
  }

  int getNumberOfRules() {
    return fieldRules.size();
  }
}
