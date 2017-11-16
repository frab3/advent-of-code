package net.bassmann.adventofcode.year2015.day07;

class OrGate extends LogicGate {

  private final String inputA;
  private final String inputB;

  OrGate(WireBundle wireBundle, String outputName, String inputA, String inputB) {
    super(wireBundle, outputName);
    this.inputA = inputA;
    this.inputB = inputB;
  }

  @Override
  int doOp() {
    return getWireValue(inputA) | getWireValue(inputB);
  }
}
