package net.bassmann.adventofcode.year2017.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A square spiral, that has 1 as the center value and at each step the sum of all existing
 * neighbouring fields.
 *
 * <p>Cf. "Square spiral of sums of selected preceding terms, starting at 1."
 * https://oeis.org/A141481
 */
class SquareSpiralWithNeighbourSums {

  enum Direction {
    LEFT(1, 0),
    UP(0, -1),
    RIGHT(-1, 0),
    DOWN(0, 1);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
      this.deltaX = deltaX;
      this.deltaY = deltaY;
    }

    int getDeltaX() {
      return deltaX;
    }

    int getDeltaY() {
      return deltaY;
    }

    Direction turn() {
      switch (this) {
        case LEFT:
          return UP;
        case UP:
          return RIGHT;
        case RIGHT:
          return DOWN;
        case DOWN:
          return LEFT;
      }
      throw new IllegalStateException();
    }
  }

  /**
   * Value cache.
   */
  private final Map<Coordinates, Integer> values = new HashMap<>();

  /**
   * Returns a list of all neighboring coordinates.
   *
   * @param c the center of the neighbourhood.
   * @return a list of all neighbours of the center.
   */
  static List<Coordinates> getNeighbours(Coordinates c) {
    final int x = c.getX();
    final int y = c.getY();
    return List.of(
        new Coordinates(x + 1, y + 1),
        new Coordinates(x + 1, y),
        new Coordinates(x + 1, y - 1),
        new Coordinates(x, y + 1),
        new Coordinates(x, y - 1),
        new Coordinates(x - 1, y + 1),
        new Coordinates(x - 1, y),
        new Coordinates(x - 1, y - 1));
  }

  private int sumNeighbours(Coordinates center) {
    return getNeighbours(center).stream().mapToInt(c -> values.getOrDefault(c, 0)).sum();
  }

  /**
   * Resets the cache and places a value for the center (the start) in the centered grid.
   *
   * @return the center coordinates.
   */
  private Coordinates initGridCenter() {
    values.clear();
    Coordinates c = new Coordinates(0, 0);
    values.put(c, 1);
    return c;
  }

  /**
   * Generates the sequence until a number greater than the given target is found and returned.
   *
   * @param target the target, which we need to overcome to stop.
   * @return the number in the sequence that is greater than the target.
   */
  int findNumberGreaterThan(int target) {
    initGridCenter();
    int value = 1;
    int number = 2;
    while (value < target) {
      Coordinates c = SquareSpiral.numberToCoordinates(number++);
      value = sumNeighbours(c);
      values.put(c, value);
    }
    return value;
  }

  /**
   * A different approach to generate the spirals coordinates, it does not need to get the
   * coordinates from the number. Instead we use the fact, that the spiral follows the pattern: 1
   * step, turn, 1 step, turn, 2 steps, turn, 2 steps, turn, 3 steps, turn, 3 steps turn, 4 steps,
   * turn, and so forth...
   *
   * @param target the target, which we need to overcome to stop.
   * @return the number in the sequence that is greater than the target.
   */
  int optimizedFindNumberGreaterThan(int target) {
    Coordinates c = initGridCenter();
    Direction d = Direction.LEFT;
    int steps = 1;
    int lastVal = 1;
    while (lastVal < target) {
      // using two for loops here, instead of getting the coordinates from the spiral.
      for (int k = 0; k < 2; k++) {
        for (int i = 0; i < steps; i++) {
          c = new Coordinates(c.getX() + d.getDeltaX(), c.getY() + d.getDeltaY());
          int val = sumNeighbours(c);
          if (val >= target) {
            return val;
          }
          values.put(c, val);
          lastVal = val;
        }
        d = d.turn();
      }
      steps++;
    }
    return -1;
  }
}
