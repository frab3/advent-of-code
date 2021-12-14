package net.bassmann.adventofcode.year2021.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SheetTest {

  @Test
  void example1() {
    List<String> input =
        List.of(
            "6,10", "0,14", "9,10", "0,3", "10,4", "4,11", "6,0", "6,12", "4,1", "0,13", "10,12",
            "3,4", "3,0", "8,4", "1,10", "2,14", "8,10", "9,0");
    var sheet = new Sheet();
    input.stream().map(Point::new).forEach(sheet::add);

    sheet.foldAlongY(7);
    //sheet.foldAlongX(5);

    assertEquals(17, sheet.countPoints());
  }
}
