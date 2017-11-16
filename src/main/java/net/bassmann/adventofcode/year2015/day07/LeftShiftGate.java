package net.bassmann.adventofcode.year2015.day07;

class LeftShiftGate extends LogicGate {

  private final String input;
  private final int shift;

  LeftShiftGate(WireBundle wireBundle, String outputName, String input, int shift) {
    super(wireBundle, outputName);
    this.input = input;
    this.shift = shift;
  }

  @Override
  int doOp() {
    return getWireValue(input) << shift;
  }
}
