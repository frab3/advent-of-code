package net.bassmann.adventofcode.year2021.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SubmarineTest {

  @Test
  void example1Test() {
    List<String> input = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
    Submarine sub = new Submarine();
    input.forEach(sub::parseCommand);
    assertEquals(15, sub.position);
    assertEquals(10, sub.depth);
  }

  @Test
  void example2Test() {
    List<String> input = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
    Submarine sub = new Submarine();
    input.forEach(sub::parseCommand2);
    assertEquals(15, sub.position);
    assertEquals(60, sub.depth);
  }
}
