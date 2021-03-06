package net.bassmann.adventofcode.common;

import java.time.LocalDate;

public abstract class AbstractDay implements Day {

  private final LocalDate localDate;
  private final RiddleInput riddleInput;

  protected AbstractDay(int year, int day) {
    localDate = LocalDate.of(year, 12, day);
    riddleInput = new RiddleInput(localDate);
  }

  public final LocalDate getDate() {
    return localDate;
  }

  protected final RiddleInput getRiddleInput() {
    return riddleInput;
  }
}
