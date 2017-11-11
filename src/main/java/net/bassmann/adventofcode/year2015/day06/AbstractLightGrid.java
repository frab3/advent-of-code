package net.bassmann.adventofcode.year2015.day06;

/**
 * The abstract light grid handles parsing of commands and with triggering the commands on areas on
 * the grid. Extending classes must implement how a command changes a single light.
 */
abstract class AbstractLightGrid implements LightGrid {

  private final int size;

  AbstractLightGrid(int size) {
    this.size = size;
  }

  abstract void turnOn(int x, int y);

  abstract void turnOff(int x, int y);

  abstract void toggle(int x, int y);

  abstract int getValue(int x, int y);

  @Override
  public final void executeCommand(String command) {
    final String[] token = command.split("[, ]");

    final Command c = Command.fromToken(token[0], token[1]);
    if (c == null) {
      return;
    }

    final int tokenLength = token.length;
    final int x0 = Integer.parseInt(token[tokenLength - 5]);
    final int y0 = Integer.parseInt(token[tokenLength - 4]);
    final int x1 = Integer.parseInt(token[tokenLength - 2]);
    final int y1 = Integer.parseInt(token[tokenLength - 1]);

    processRectangle(c, x0, y0, x1, y1);
  }

  private void processRectangle(Command c, int x0, int y0, int x1, int y1) {
    for (int row = x0; row <= x1; row++) {
      for (int col = y0; col <= y1; col++) {
        switch (c) {
          case ON:
            turnOn(row, col);
            break;
          case OFF:
            turnOff(row, col);
            break;
          case TOGGLE:
            toggle(row, col);
            break;
        }
      }
    }
  }

  @Override
  public final int count() {
    int sum = 0;
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        sum += getValue(row, col);
      }
    }
    return sum;
  }
}
