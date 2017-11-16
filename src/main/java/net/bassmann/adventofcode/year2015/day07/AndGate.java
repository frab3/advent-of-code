package net.bassmann.adventofcode.year2015.day07;

class AndGate extends LogicGate {

  private final String inputA;
  private final String inputB;

  AndGate(WireBundle wireBundle, String outputName, String inputA, String inputB) {
    super(wireBundle, outputName);
    this.inputA = inputA;
    this.inputB = inputB;
  }

  @Override
  int doOp() {
    return getWireValue(inputA) & getWireValue(inputB);
  }
}
