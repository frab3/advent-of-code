package net.bassmann.adventofcode.year2015.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CircuitTest {

  /*
   * or example, here is a simple circuit:

  123 -> x
  456 -> y
  x AND y -> d
  x OR y -> e
  x LSHIFT 2 -> f
  y RSHIFT 2 -> g
  NOT x -> h
  NOT y -> i
  After it is run, these are the signals on the wires:

  d: 72
  e: 507
  f: 492
  g: 114
  h: 65412
  i: 65079
  x: 123
  y: 456
     */
  @Test
  void parserTest() {
    Circuit c = new Circuit();

    WireSource actual = c.parseInstruction("123 -> x");
    Assertions.assertTrue(actual instanceof SetGate);

    actual = c.parseInstruction("y -> z");
    Assertions.assertTrue(actual instanceof SetGate);

    actual = c.parseInstruction("NOT x -> h");
    Assertions.assertTrue(actual instanceof NotGate);

    actual = c.parseInstruction("a AND b -> c");
    Assertions.assertTrue(actual instanceof AndGate);

    actual = c.parseInstruction("d OR e -> f");
    Assertions.assertTrue(actual instanceof OrGate);

    actual = c.parseInstruction("m LSHIFT 2 -> s");
    Assertions.assertTrue(actual instanceof LeftShiftGate);

    actual = c.parseInstruction("m RSHIFT 2 -> s");
    Assertions.assertTrue(actual instanceof RightShiftGate);
  }

  private final List<String> testInput =
      List.of(
          "123 -> x",
          "456 -> y",
          "x AND y -> d",
          "x OR y -> e",
          "x LSHIFT 2 -> f",
          "y RSHIFT 2 -> g",
          "NOT x -> h",
          "NOT y -> i");

  private final Map<String, Integer> expected =
      Map.of("d", 72, "e", 507, "f", 492, "g", 114, "h", 65412, "i", 65079, "x", 123, "y", 456);

  @Test
  void withTestInput() {
    final Circuit c = new Circuit();
    testInput.stream().forEachOrdered(c::addWireSourceFromInstruction);

    expected.forEach((wire, exp) -> assertEquals(exp.intValue(), c.getWireValue(wire)));
  }
}
