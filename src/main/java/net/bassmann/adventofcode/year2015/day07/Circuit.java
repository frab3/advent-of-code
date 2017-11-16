package net.bassmann.adventofcode.year2015.day07;

import java.util.HashMap;
import java.util.Map;

/**
 * Main structure where elements are parsed and connected. The idea is, that the logic gates fetch
 * the values from the wires the first time they are needed; and then cache it. So afterwards, they
 * can respond instantly.
 */
class Circuit implements WireBundle {

  private final Map<String, WireSource> wireSources = new HashMap<>();

  /**
   * Wires can be named, then the value can should be fetched from the wires sources. But logic
   * gates can also have direct inputs, then the "wirename" is a number and can be returned
   * directly.
   *
   * @param wire name of the wire or a numeric string.
   * @return and numeric value
   */
  public int getWireValue(String wire) {
    return isNumeric(wire) ? Integer.parseInt(wire) : wireSources.get(wire).getValue();
  }

  void addWireSourceFromInstruction(String instruction) {
    final WireSource wireSource = parseInstruction(instruction);
    wireSources.put(wireSource.getName(), wireSource);
  }

  WireSource parseInstruction(String instruction) {
    final String[] tokens = instruction.split(" ");

    final String outputName = tokens[tokens.length - 1];

    if ("NOT".equals(tokens[0])) {
      return new NotGate(this, outputName, tokens[1]);
    }

    switch (tokens[1]) {
      case "->":
        return new SetGate(this, outputName, tokens[0]);
      case "AND":
        return new AndGate(this, outputName, tokens[0], tokens[2]);
      case "OR":
        return new OrGate(this, outputName, tokens[0], tokens[2]);
      case "LSHIFT":
        return new LeftShiftGate(this, outputName, tokens[0], Integer.parseInt(tokens[2]));
      case "RSHIFT":
        return new RightShiftGate(this, outputName, tokens[0], Integer.parseInt(tokens[2]));
    }

    return null;
  }

  private boolean isNumeric(String token) {
    return Character.isDigit(token.charAt(0));
  }
}
