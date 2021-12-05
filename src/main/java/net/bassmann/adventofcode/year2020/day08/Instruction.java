package net.bassmann.adventofcode.year2020.day08;

import java.util.Arrays;

public class Instruction {

  private final Operation operation;
  private final int argument;

  public Instruction(Operation operation, int argument) {
    this.operation = operation;
    this.argument = argument;
  }

  public Operation getOperation() {
    return operation;
  }

  public int getArgument() {
    return argument;
  }

  Instruction flip() {
    switch (operation) {
      case JMP:
        return new Instruction(Operation.NOP, argument);
      case NOP:
        return new Instruction(Operation.JMP, argument);
      default:
        throw new RuntimeException();
    }
  }

  static Instruction fromString(String instruction) {
    return new Instruction(
        Operation.fromString(instruction.substring(0, 3)),
        Integer.parseInt(instruction.substring(4)));
  }

  enum Operation {
    NOP,
    ACC,
    JMP;

    static Operation fromString(String operation) {
      return Arrays.stream(values())
          .filter(op -> op.name().equalsIgnoreCase(operation))
          .findFirst()
          .orElseThrow(RuntimeException::new);
    }
  }
}
