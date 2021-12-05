package net.bassmann.adventofcode.year2017.day20;

class Particle {
  private final int number;
  private Vector pos;
  private Vector vel;
  private final Vector acc;

  /**
   * A particle with a constant acceleration that changes its velocity and therefore its position
   * over time.
   *
   * @param number a unique identifier for each particle.
   * @param pos initial position of the particle.
   * @param vel initial velocity of the particle.
   * @param acc the particle's constant acceleration.
   */
  Particle(int number, Vector pos, Vector vel, Vector acc) {
    this.number = number;
    this.pos = pos;
    this.vel = vel;
    this.acc = acc;
  }

  void tick() {
    vel = vel.add(acc);
    pos = pos.add(vel);
  }

  int getNumber() {
    return number;
  }

  Vector getPos() {
    return pos;
  }

  Vector getVel() {
    return vel;
  }

  Vector getAcc() {
    return acc;
  }

  @Override
  public String toString() {
    return String.format("P %4d: pos: %s  vel: %s  acc: %s", number, pos, vel, acc);
  }
}
