package net.bassmann.adventofcode.year2021.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day04 extends AbstractDay {

  public Year2021Day04() {
    super(2021, 04);
  }

  @Override
  public String solvePart1() {
    List<String> input = getRiddleInput().asList();
    int[] draws = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

    List<BingoBoard> boards = createBoards(input);
    int r = play1(boards, draws);

    return String.valueOf(r);
  }

  @Override
  public String solvePart2() {
    List<String> input = getRiddleInput().asList();
    int[] draws = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

    List<BingoBoard> boards = createBoards(input);
    int r = play2(boards, draws);

    return String.valueOf(r);
  }

  static List<BingoBoard> createBoards(List<String> input) {
    int totalBoards = (input.size() - 2) / 6;
    List<BingoBoard> boards = new ArrayList<>(totalBoards);
    for (int b = 0; b <= totalBoards; b++) {
      boards.add(new BingoBoard(input.subList(2 + b * 6, 7 + b * 6)));
    }
    return boards;
  }

  static int play1(List<BingoBoard> boards, int[] draws) {
    for (int d = 0; d < draws.length; d++) {
      int winningNumber = draws[d];
      for (int b = 0; b < boards.size(); b++) {
        BingoBoard board = boards.get(b);
        if (board.checkForBingo(board.mark(winningNumber))) {
          System.out.println("winning number is " + winningNumber);
          System.out.println(board);
          return board.unmarkedSum() * winningNumber;
        }
      }
    }
    return -1;
  }

  static int play2(List<BingoBoard> boards, int[] draws) {
    BingoBoard lastWinning = null;
    for (int d = 0; d < draws.length; d++) {
      int winningNumber = draws[d];
      for (Iterator<BingoBoard> it = boards.iterator(); it.hasNext(); ) {
        BingoBoard board = it.next();
        if (board.checkForBingo(board.mark(winningNumber))) {
          lastWinning = board;
          it.remove();
        }
      }
      if (boards.size() == 0) {
        System.out.println("winning number: " + winningNumber);
        System.out.println(lastWinning);
        return lastWinning.unmarkedSum() * winningNumber;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    var today = new Year2021Day04();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
