package net.bassmann.adventofcode.year2017.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.bassmann.adventofcode.common.Day;
import org.junit.jupiter.api.Test;

class Year2017Day20Test {

  private final Day today = new Year2017Day20();

  @Test
  void solvePart1() {
    assertEquals("376", today.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals("574", today.solvePart2());
  }

  @Test
  void exampleOneTest() {
    List<String> example =
        List.of("p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>", "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>");
    List<Particle> particles = Year2017Day20.parseParticles(example);
    int actual = Year2017Day20.findParticleThatWillStayClosest(particles);
    assertEquals(0, actual);
  }

  @Test
  void exampleTwoTest() {
    List<String> example = List.of("p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
        "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
        "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
        "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>");
    List<Particle> particles = Year2017Day20.parseParticles(example);
    int actual = Year2017Day20.resolveAllCollisions(particles);
    assertEquals(1, actual);
  }
}
