package net.bassmann.adventofcode.year2020.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day16 extends AbstractDay {

  public Year2020Day16() {
    super(2020, 16);
  }

  @Override
  public String solvePart1() {
    List<String> input = getRiddleInput().asList();
    int i = 0;
    FieldValidator validator = new FieldValidator();
    while (!input.get(i).isEmpty()) {
      validator.addFieldRule(FieldRule.fromString(input.get(i)));
      i++;
    }
    i += 5;
    long errorRate =
        IntStream.range(i, input.size())
            .flatMap(line -> Arrays.stream(input.get(line).split(",")).mapToInt(Integer::parseInt))
            .filter(n -> !validator.isValid(n))
            .sum();
    return Long.toString(errorRate);
  }

  @Override
  public String solvePart2() {
    List<String> input = getRiddleInput().asList();
    int i = 0;
    FieldValidator validator = new FieldValidator();
    while (!input.get(i).isEmpty()) {
      validator.addFieldRule(FieldRule.fromString(input.get(i)));
      i++;
    }
    i += 2;
    Ticket myTicket = Ticket.fromString(input.get(i));
    i += 3;

    List<Ticket> validTickets =
        IntStream.range(i, input.size())
            .mapToObj(j -> Ticket.fromString(input.get(j)))
            .filter(validator::isValid)
            .collect(Collectors.toList());

    validTickets.add(myTicket);

    if (validator.getNumberOfRules() != myTicket.fieldCount()) {
      throw new RuntimeException("Number of rules and number of fields on ticket does not match");
    }

    int[][] validFieldCount = new int[validator.getNumberOfRules()][myTicket.fieldCount()];
    for (int ticketIndex = 0; ticketIndex < validTickets.size(); ticketIndex++) {
      Ticket ticket = validTickets.get(ticketIndex);
      for (int ruleIndex = 0; ruleIndex < validator.getNumberOfRules(); ruleIndex++) {
        FieldRule rule = validator.getRule(ruleIndex);
        for (int fieldIndex = 0; fieldIndex < ticket.fieldCount(); fieldIndex++) {
          if (rule.isValid(ticket.getValue(fieldIndex))) {
            validFieldCount[ruleIndex][fieldIndex]++;
          }
        }
      }
    }

    System.out.println(validTickets.size());
    System.out.println((Arrays.deepToString(validFieldCount)));

    List<RuleToField> mappings = new ArrayList<>();
    for (int ruleIndex = 0; ruleIndex < validator.getNumberOfRules(); ruleIndex++) {
      RuleToField mapping = new RuleToField(validator.getRule(ruleIndex));
      for (int fieldIndex = 0; fieldIndex < 20; fieldIndex++) {
        if (validFieldCount[ruleIndex][fieldIndex] == 191) {
          mapping.addField(fieldIndex);
        }
      }
      mappings.add(mapping);
    }

    mappings.sort(Comparator.comparingInt(RuleToField::numMatches));

    System.out.println();
    System.out.println(mappings);
    System.out.println();

    for (int m = 0; m < mappings.size(); m++) {
      int fi = mappings.get(m).getFieldIndex();
      for (int n = m + 1; n < mappings.size(); n++) {
        mappings.get(n).remove(fi);
      }
    }

    System.out.println();
    System.out.println(mappings);
    System.out.println();

    long mult =
        mappings.stream()
            .filter(ruleToField -> ruleToField.rule.getName().startsWith("departure"))
            .mapToInt(r -> r.getFieldIndex())
            .map(fieldIndex -> myTicket.getValue(fieldIndex))
            .mapToLong(ii -> ii)
            .peek(ii -> System.out.println(ii))
            .reduce(1, (ii, jj) -> ii * jj);
    // .orElse(-1);

    return Long.toString(mult);
  }

  static class RuleToField {
    private final FieldRule rule;
    private final List<Integer> fieldIndices = new ArrayList<>();

    RuleToField(FieldRule rule) {
      this.rule = rule;
    }

    void addField(int fieldIndex) {
      fieldIndices.add(fieldIndex);
    }

    void remove(int fieldIndex) {
      fieldIndices.remove(Integer.valueOf(fieldIndex));
    }

    int getFieldIndex() {
      if (fieldIndices.size() != 1) {
        throw new RuntimeException("Expected only one field index");
      }
      return fieldIndices.get(0);
    }

    int numMatches() {
      return fieldIndices.size();
    }

    @Override
    public String toString() {
      return "RuleToField{" + "rule=" + rule + ", fieldIndices=" + fieldIndices + '}';
    }
  }

  public static void main(String[] args) {
    var today = new Year2020Day16();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
