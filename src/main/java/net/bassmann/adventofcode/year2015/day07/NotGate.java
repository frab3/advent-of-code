package net.bassmann.adventofcode.year2015.day07;

class NotGate extends LogicGate {

  private final String source;

  NotGate(WireBundle wireBundle, String outputName, String source) {
    super(wireBundle, outputName);
    this.source = source;
  }

  @Override
  int doOp() {
    return ~getWireValue(source);
  }
}
