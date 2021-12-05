package net.bassmann.adventofcode.year2017.day20;

import static java.util.Comparator.comparingLong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/** Day 20: Particle Swarm. */
public class Year2017Day20 extends AbstractDay {

  private List<Particle> particles = null;

  public Year2017Day20() {
    super(2017, 20);
  }

  @Override
  public String solvePart1() {
    List<Particle> p = getParticles();
    int number = findParticleThatWillStayClosest(p);
    return Integer.toString(number);
  }

  @Override
  public String solvePart2() {
    int particlesRemaining = resolveAllCollisions(getParticles());
    return Integer.toString(particlesRemaining);
  }

  private List<Particle> getParticles() {
    if (particles == null) {
      particles = parseParticles(getRiddleInput().asList());
    }
    return particles;
  }

  static List<Particle> parseParticles(List<String> input) {
    List<Particle> particles = new ArrayList<>(input.size());
    int n = 0;
    for (String line : input) {
      particles.add(parse(line, n++));
    }
    return particles;
  }

  private static Particle parse(String input, int n) {
    String[] split = input.split(", ");
    return new Particle(
        n,
        new Vector(split[0].substring(3, split[0].length() - 1)),
        new Vector(split[1].substring(3, split[1].length() - 1)),
        new Vector(split[2].substring(3, split[2].length() - 1)));
  }

  /**
   * This is the solution for part one. At least with my input I just had to look for the particle
   * with the smallest acceleration, since that would move slowest in the long run and therefore
   * stay closest to the center. My solution does not cover the case where there are more than one
   * particle with the same minimal acceleration.
   *
   * @return the number of the particle with the lowest acceleration.
   */
  static int findParticleThatWillStayClosest(List<Particle> particles) {
    Comparator<Particle> accComparator = comparingLong(p -> p.getAcc().getManhattanDistance());

    return particles.stream().min(accComparator).map(Particle::getNumber).orElse(-1);
  }

  /**
   * Solution for part 2. Resolves all collisions and the returns how many particles are still
   * active.
   *
   * @param particles
   * @return
   */
  static int resolveAllCollisions(List<Particle> particles) {

    int noCollision = 0;
    boolean[] collided = new boolean[particles.size()];
    while (noCollision < 30) {
      noCollision++;
      for (Particle p : particles) {
        if (collided[p.getNumber()]) {
          continue;
        }
        for (int q = p.getNumber() + 1; q < particles.size(); q++) {
          if (p.getPos().samePos(particles.get(q).getPos())) {
            collided[p.getNumber()] = true;
            collided[q] = true;
            noCollision = 0;
          }
        }
      }
      for (int p = 0; p < particles.size(); p++) {
        if (!collided[p]) {
          particles.get(p).tick();
        }
      }
    }

    return (int) IntStream.range(0, collided.length).filter(i -> !collided[i]).count();
  }

  public static void main(String[] args) {
    Year2017Day20 d = new Year2017Day20();
    List<String> example = List.of("");
    List<Particle> p = parseParticles(d.getRiddleInput().asList());
    int f = findParticleThatWillStayClosest(p);
    System.out.println(f);
    //    System.out.println(solve(example));
    //    parse("p=<-3770,-455,1749>, v=<-4,-77,53>, a=<11,7,-9>");

    Particle p90 = p.get(90);
    Particle p241 = p.get(241);
    Particle p340 = p.get(340);

    for (int i = 0; i < 3; i++) {
      System.out.println(
          p90
              + "\n   distance: "
              + p90.getPos().getManhattanDistance()
              + "  vel:"
              + p90.getVel().getManhattanDistance()
              + " -> "
              + p90.getVel().length());
      System.out.println(
          p241
              + "\n   distance: "
              + p241.getPos().getManhattanDistance()
              + "  vel:"
              + p241.getVel().getManhattanDistance()
              + " -> "
              + p241.getVel().length());
      System.out.println(
          p340
              + "\n   distance: "
              + p340.getPos().getManhattanDistance()
              + "  vel:"
              + p340.getVel().getManhattanDistance()
              + " -> "
              + p340.getVel().length());
      System.out.println("-------------");
      p90.tick();
      p241.tick();
      p340.tick();
    }

    for (int i = 0; i < 3000; i++) {
      p90.tick();
      p241.tick();
      p340.tick();
    }

    System.out.println(
        p90
            + "\n   distance: "
            + p90.getPos().getManhattanDistance()
            + "  vel:"
            + p90.getVel().getManhattanDistance()
            + " -> "
            + p90.getVel().length());
    System.out.println(
        p241
            + "\n   distance: "
            + p241.getPos().getManhattanDistance()
            + "  vel:"
            + p241.getVel().getManhattanDistance()
            + " -> "
            + p241.getVel().length());
    System.out.println(
        p340
            + "\n   distance: "
            + p340.getPos().getManhattanDistance()
            + "  vel:"
            + p340.getVel().getManhattanDistance()
            + " -> "
            + p340.getVel().length());

    // 90: pos: (-1500, -1042, -1729)  vel: (40, 8, 46)  acc: (0, 1, 0)
    // 241: pos: (219, 1243, -30)  vel: (-13, -80, -1)  acc: (0, 1, 0)
    // 340: pos: (1792, -968, 317)  vel: (-88, 37, -13)  acc: (0, 1, 0)
  }
}
