package net.bassmann.adventofcode.year2020.day16;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Ticket {

  private final int[] fieldValues;

  private Ticket(int[] fieldValueArray) {
    this.fieldValues = fieldValueArray;
  }

  static Ticket fromString(String input) {
    return new Ticket(Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray());
  }

  IntStream values() {
    return Arrays.stream(fieldValues);
  }

  int getValue(int fieldIndex) {
    return fieldValues[fieldIndex];
  }

  int fieldCount() {
    return fieldValues.length;
  }
}
