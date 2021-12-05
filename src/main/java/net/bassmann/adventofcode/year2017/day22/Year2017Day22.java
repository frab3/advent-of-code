package net.bassmann.adventofcode.year2017.day22;

import static net.bassmann.adventofcode.year2017.day22.Direction.NORTH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2017Day22 extends AbstractDay {

  public Year2017Day22() {
    super(2017, 22);
  }

  @Override
  public String solvePart1() {
    long l = solve1(getRiddleInput().asList(), 10_000);
    return Long.toString(l);
  }

  @Override
  public String solvePart2() {
    long l = solve2(getRiddleInput().asList(), 10_000_000);
    return Long.toString(l);
  }

  static long solve1(List<String> input, int steps) {
    return solve(input, steps, true);
  }

  static long solve2(List<String> input, int steps) {
    return solve(input, steps, false);
  }

  static long solve(List<String> input, int steps, boolean useSimple) {
    final Map<String, NodeState> grid = parseInput(input);

    // The virus carrier begins in the middle of the map facing up.
    int x = input.size() / 2;
    int y = x;
    Direction d = NORTH;

    int count = 0;

    for (int i = 0; i < steps; i++) {
      final String key = makeKey(x, y);
      final NodeState state = grid.getOrDefault(key, NodeState.CLEAN);

      // 1. Switch direction based on state of current position.
      switch (state) {
        case CLEAN:
          d = d.left();
          break;
        case WEAKENED: /* go straight */
          break;
        case INFECTED:
          d = d.right();
          break;
        case FLAGGED:
          d = d.reverse();
          break;
      }
      // 2. Modify state at current position, based on previous state.
      final NodeState nextState = useSimple ? state.nextSimple() : state.next();
      if (nextState == NodeState.INFECTED) {
        count++;
      }
      grid.put(key, nextState);

      // 3. Go a step in the direction facing.
      x += d.dx;
      y += d.dy;
    }

    return count;
  }

  private static Map<String, NodeState> parseInput(List<String> input) {
    Map<String, NodeState> grid = new HashMap<>();

    final int size = input.size();

    for (int row = 0; row < size; row++) {
      String line = input.get(row);
      for (int col = 0; col < size; col++) {
        boolean infected = line.charAt(col) == '#';
        String key = makeKey(row, col);
        grid.put(key, infected ? NodeState.INFECTED : NodeState.CLEAN);
      }
    }
    return grid;
  }

  private static String makeKey(int x, int y) {
    return x + "|" + y;
  }
}
