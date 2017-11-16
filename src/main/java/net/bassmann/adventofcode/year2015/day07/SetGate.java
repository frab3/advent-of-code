package net.bassmann.adventofcode.year2015.day07;

class SetGate extends LogicGate {

  private final String source;

  SetGate(WireBundle wireBundle, String outputName, String source) {
    super(wireBundle, outputName);
    this.source = source;
  }

  @Override
  int doOp() {
    return getWireValue(source);
  }
}
