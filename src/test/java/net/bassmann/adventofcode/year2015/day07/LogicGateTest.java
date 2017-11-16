package net.bassmann.adventofcode.year2015.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LogicGateTest {

  private WireBundle getMockBundle() {
    return name -> {
      switch (name) {
        case "x":
          return 123;
        case "y":
          return 456;
        default:
          return 0;
      }
    };
  }

  @ParameterizedTest
  @CsvSource({"-1, 65535", "0, 0", "1, 1", "123, 123", "65535, 65535", "65536, 0", "65537, 1"})
  void setGate_returnsTheInitialValue_butCappedTo16Bit(String input, int expected) {
    WireBundle mockBundle = name -> Integer.parseInt(input);
    LogicGate a = new SetGate(mockBundle, "a", input);
    assertEquals(expected, a.getValue());
  }

  @Test
  void setGateTest() {
    final LogicGate a = new SetGate(getMockBundle(), "a", "y");
    assertEquals(456, a.getValue());
  }

  @Test
  void andGateTest() {
    final LogicGate d = new AndGate(getMockBundle(), "d", "x", "y");
    assertEquals(72, d.getValue());
  }

  @Test
  void orGateTest() {
    final LogicGate e = new OrGate(getMockBundle(), "e", "x", "y");
    assertEquals(507, e.getValue());
  }

  @Test
  void leftShiftGateTest() {
    final LogicGate f = new LeftShiftGate(getMockBundle(), "f", "x", 2);
    assertEquals(492, f.getValue());
  }

  @Test
  void rightShiftGateTest() {
    final LogicGate g = new RightShiftGate(getMockBundle(), "g", "y", 2);
    assertEquals(114, g.getValue());
  }

  @Test
  void notGateTest() {
    final LogicGate h = new NotGate(getMockBundle(), "h", "x");
    assertEquals(65412, h.getValue());

    final LogicGate i = new NotGate(getMockBundle(), "i", "y");
    assertEquals(65079, i.getValue());
  }
}
