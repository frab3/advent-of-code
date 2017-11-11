package net.bassmann.adventofcode.year2015.day03;

import java.util.HashSet;
import java.util.Set;

/** The 2D infinite house grid where Santa will deliver presents. */
class HouseGrid {

  /** We only need to keep track of all unique houses Santa visits. */
  private final Set<Coordinate> housesVisited = new HashSet<>();

  private final Santa santa;
  private final Santa roboSanta;

  HouseGrid() {
    Coordinate startPosition = new Coordinate(0, 0);
    santa = new Santa(startPosition);
    roboSanta = new Santa(startPosition);
    deliverPresent(startPosition);
  }

  /** Solution to part 1, only use Santa */
  void deliverPresents(String instructions) {
    deliverPresents(instructions, false);
  }

  void deliverPresentsUsingRoboSanta(String instructions) {
    deliverPresents(instructions, true);
  }

  /**
   * The solution is the present delivery algorithm: For each direction in the instructions the
   * santa whose turn it is moves and delivers a present.
   *
   * @param instructions the instructions, as read by the eggnogged elf.
   * @param useRoboSanta if true Santa and RoboSanta take turns in following the directions.
   */
  private void deliverPresents(String instructions, boolean useRoboSanta) {
    final char[] charArray = instructions.toCharArray();
    for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
      final char c = charArray[i];
      Direction d = Direction.fromChar(c);
      if (useRoboSanta && isRoboSantasTurn(i)) {
        moveInDirectionAndDeliver(roboSanta, d);
      } else {
        moveInDirectionAndDeliver(santa, d);
      }
    }
  }

  private boolean isRoboSantasTurn(int i) {
    return i % 2 == 0;
  }

  private void moveInDirectionAndDeliver(Santa santa, Direction d) {
    santa.moveInDirection(d);
    deliverPresent(santa.getPosition());
  }

  private void deliverPresent(Coordinate coordinate) {
    housesVisited.add(coordinate);
  }

  int countHousesVisited() {
    return housesVisited.size();
  }
}
