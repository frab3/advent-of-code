package net.bassmann.adventofcode.year2020.day11;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day11 extends AbstractDay {

  public Year2020Day11() {
    super(2020, 11);
  }

  @Override
  public String solvePart1() {
    WaitingArea wa = WaitingArea.fromString(getRiddleInput().asList());
    int count = wa.findStableStateAndCountOccupiedSeats();
    return Integer.toString(count);
  }

  @Override
  public String solvePart2() {
    WaitingArea wa = WaitingArea.fromString(getRiddleInput().asList());
    int count = wa.findStableStateAndCountOccupiedSeats2();
    return Integer.toString(count);
  }

  static class WaitingArea {
    private final int width;
    private final int height;
    private char[][] area;

    private WaitingArea(int width, int height) {
      this.width = width;
      this.height = height;
      area = new char[height][width];
    }

    static WaitingArea fromString(List<String> lines) {
      System.out.println(lines.size());
      int numRows = lines.size();
      int numSeats = lines.get(0).length();
      System.out.println("Dimension " + numRows + "x" + numSeats);
      WaitingArea wa = new WaitingArea(lines.get(0).length(), lines.size());

      for (int line = 0; line < lines.size(); line++) {
        wa.area[line] = lines.get(line).toCharArray();
      }

      return wa;
    }

    int findStableStateAndCountOccupiedSeats() {
      String oldState = null;
      String newState = toString();
      do {
        oldState = newState;
        doStep();
        newState = toString();
      } while (!oldState.equals(newState));
      return (int) oldState.chars().filter(c -> c == '#').count();
    }

    int findStableStateAndCountOccupiedSeats2() {
      String oldState = null;
      String newState = toString();
      do {
        oldState = newState;
        doStep2();
        newState = toString();
      } while (!oldState.equals(newState));
      return (int) oldState.chars().filter(c -> c == '#').count();
    }

    void doStep() {
      char[][] areaUpdate = new char[height][width];
      for (int row = 0; row < height; row++) {
        for (int seat = 0; seat < width; seat++) {
          areaUpdate[row][seat] = applyRule(row, seat);
        }
      }
      area = areaUpdate;
    }

    void doStep2() {
      char[][] areaUpdate = new char[height][width];
      for (int row = 0; row < height; row++) {
        for (int seat = 0; seat < width; seat++) {
          areaUpdate[row][seat] = applyRule2(row, seat);
        }
      }
      area = areaUpdate;
    }

    private char applyRule(int row, int seat) {
      char state = area[row][seat];
      if (state == '.') {
        return '.';
      } else {
        int occupied = countAdjacentOccupiedSeats(row, seat);
        if (occupied == 0) {
          return '#';
        } else if (occupied >= 4) {
          return 'L';
        } else {
          return state;
        }
      }
    }

    private char applyRule2(int row, int seat) {
      char state = area[row][seat];
      if (state == '.') {
        return '.';
      } else {
        int occupied = countOccupiedSeatsInAllDirections(row, seat);
        if (occupied == 0) {
          return '#';
        } else if (occupied >= 5) {
          return 'L';
        } else {
          return state;
        }
      }
    }

    private int countAdjacentOccupiedSeats(int row, int seat) {
      return (isOccupied(row - 1, seat - 1) ? 1 : 0)
          + (isOccupied(row - 1, seat) ? 1 : 0)
          + (isOccupied(row - 1, seat + 1) ? 1 : 0)
          + (isOccupied(row, seat - 1) ? 1 : 0)
          + (isOccupied(row, seat + 1) ? 1 : 0)
          + (isOccupied(row + 1, seat - 1) ? 1 : 0)
          + (isOccupied(row + 1, seat) ? 1 : 0)
          + (isOccupied(row + 1, seat + 1) ? 1 : 0);
    }

    private int countOccupiedSeatsInAllDirections(int row, int seat) {
      return (isOccupiedInDirection(row, seat, -1, -1) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, -1, 0) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, -1, 1) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, 0, -1) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, 0, 1) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, 1, -1) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, 1, 0) ? 1 : 0)
          + (isOccupiedInDirection(row, seat, 1, 1) ? 1 : 0);
    }

    private boolean isOccupied(int row, int seat) {
      return row >= 0 && row < height && seat >= 0 && seat < width && area[row][seat] == '#';
    }

    private boolean isOccupiedInDirection(int row, int seat, int rowDir, int seatDir) {
      int curRow = row + rowDir;
      int curSeat = seat + seatDir;
      while (curRow >= 0 && curRow < height && curSeat >= 0 && curSeat < width) {
        if (area[curRow][curSeat] == '#') return true;
        if (area[curRow][curSeat] == 'L') return false;
        curRow += rowDir;
        curSeat += seatDir;
      }
      return false;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();

      for (char[] row : area) {
        sb.append(row).append('\n');
      }
      sb.deleteCharAt(sb.length() - 1);

      return sb.toString();
    }
  }

  public static void main(String[] args) {
    var today = new Year2020Day11();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
