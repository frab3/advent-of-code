package net.bassmann.adventofcode.year2015.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class DistanceMapTest {

  @Test
  void addFromStringTest() {
    DistanceMap distanceMap = new DistanceMap();

    Year2015Day09Test.TEST_INPUT.forEach(distanceMap::addFromString);

    List<String> destinations = distanceMap.getDestinations();
    assertEquals(3, destinations.size());
    assertTrue(destinations.contains("London"));
    assertTrue(destinations.contains("Dublin"));
    assertTrue(destinations.contains("Belfast"));

    assertEquals(141, distanceMap.getDistance("Dublin", "Belfast"));
    assertEquals(141, distanceMap.getDistance("Belfast", "Dublin"));
  }

  @Test
  void testMakeKey() {
    String k = DistanceMap.makeKey("Foo", "Bar");
    assertEquals("BarFoo", k);

    k = DistanceMap.makeKey("Bar", "Foo");
    assertEquals("BarFoo", k);

    k = DistanceMap.makeKey("foo", "Foo");
    assertEquals("Foofoo", k);

    k = DistanceMap.makeKey("Foo", "foo");
    assertEquals("Foofoo", k);
  }
}
