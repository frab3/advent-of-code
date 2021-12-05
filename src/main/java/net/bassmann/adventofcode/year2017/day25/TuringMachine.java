package net.bassmann.adventofcode.year2017.day25;

import static net.bassmann.adventofcode.year2017.day25.TuringMachine.Direction.LEFT;
import static net.bassmann.adventofcode.year2017.day25.TuringMachine.Direction.RIGHT;

import java.util.Map;

class TuringMachine {

  enum Direction {
    LEFT,
    RIGHT
  }

  private TuringMachineField cursor = new TuringMachineField();
  private String state = "A";
  private final Map<String, Map<Integer, Action>> actions =
      Map.of(
          "A", Map.of(0, new Action(1, RIGHT, "B"), 1, new Action(0, LEFT, "C")),
          "B", Map.of(0, new Action(1, LEFT, "A"), 1, new Action(1, LEFT, "D")),
          "C", Map.of(0, new Action(1, RIGHT, "D"), 1, new Action(0, RIGHT, "C")),
          "D", Map.of(0, new Action(0, LEFT, "B"), 1, new Action(0, RIGHT, "E")),
          "E", Map.of(0, new Action(1, RIGHT, "C"), 1, new Action(1, LEFT, "F")),
          "F", Map.of(0, new Action(1, LEFT, "E"), 1, new Action(1, RIGHT, "A")));

  void step() {
    cursor = actions.get(state).get(cursor.get()).apply(cursor);
  }

  int checksum() {
    return cursor.checksum();
  }

  private class Action {

    private final int write;
    private final Direction move;
    private final String nextState;

    private Action(int write, Direction move, String nextState) {
      this.write = write;
      this.move = move;
      this.nextState = nextState;
    }

    TuringMachineField apply(TuringMachineField cursor) {
      cursor.set(write);
      state = nextState;
      return move == LEFT ? cursor.left() : cursor.right();
    }
  }
}
