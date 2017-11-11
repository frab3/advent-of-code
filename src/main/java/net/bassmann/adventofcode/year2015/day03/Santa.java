package net.bassmann.adventofcode.year2015.day03;

/** Santa (or RoboSanta) has a position and can be told to move in a direction. */
class Santa {

  private Coordinate position;

  Santa(Coordinate startPosition) {
    position = startPosition;
  }

  void moveInDirection(Direction direction) {
    position = position.moveTo(direction);
  }

  Coordinate getPosition() {
    return position;
  }
}
