package net.bassmann.adventofcode.year2017.day22;

enum NodeState {
  CLEAN,
  WEAKENED,
  INFECTED,
  FLAGGED;

  NodeState nextSimple() {
    switch (this) {
      case CLEAN:
        return INFECTED;
      case INFECTED:
        return CLEAN;
      default:
        throw new IllegalStateException();
    }
  }

  NodeState next() {
    switch (this) {
      case CLEAN:
        return WEAKENED;
      case WEAKENED:
        return INFECTED;
      case INFECTED:
        return FLAGGED;
      case FLAGGED:
        return CLEAN;
      default:
        throw new IllegalStateException();
    }
  }
}
