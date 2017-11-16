package net.bassmann.adventofcode.year2015.day07;

/**
 * Base class, offers caching functionality. Extending classes need to implement the doOp method.
 */
abstract class LogicGate implements WireSource {

  private final WireBundle wireBundle;
  private final String outputName;

  private int cache = -1;

  LogicGate(WireBundle wireBundle, String outputName) {
    this.wireBundle = wireBundle;
    this.outputName = outputName;
  }

  abstract int doOp();

  int getWireValue(String wire) {
    return wireBundle.getWireValue(wire);
  }

  @Override
  public String getName() {
    return outputName;
  }

  public int getValue() {
    if (cache < 0) {
      cache = doOp() & 0xFFFF;
    }
    return cache;
  }
}
