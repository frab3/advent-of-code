package net.bassmann.adventofcode.year2020.day15;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day15 extends AbstractDay {

  private final String input = "2,0,1,9,5,19";

  public Year2020Day15() {
    super(2020, 15);
  }

  @Override
  public String solvePart1() {
    NumbersGame game = new NumbersGame(input);
    while (game.currentTurn <= 2020) {
      game.play();
    }
    return Integer.toString(game.getLastNumberSpoken());
  }

  @Override
  public String solvePart2() {
    NumbersGame game = new NumbersGame(input);
    while (game.currentTurn <= 30000000) {
      game.play();
    }
    return Integer.toString(game.getLastNumberSpoken());
  }

  static class NumbersGame {

    private final int[] numbers;
    private int currentTurn = 1;
    private int lastNumberSpoken = -1;
    // number -> list of turns it was spoken
    Map<Integer, LinkedList<Integer>> mem = new HashMap<>();

    NumbersGame(String input) {
      String[] splitInput = input.split(",");
      numbers = new int[splitInput.length];
      for (String s : splitInput) {
        int number = Integer.parseInt(s);
        numbers[currentTurn - 1] = number;
        sayNumber(number);
        currentTurn++;
      }
    }

    void play() {
      List<Integer> spokenInTurn = getHistory(lastNumberSpoken);
      if (spokenInTurn.isEmpty() || spokenInTurn.size() == 1) {
        sayNumber(0);
      } else {
        sayNumber(
            spokenInTurn.get(spokenInTurn.size() - 1) - spokenInTurn.get(spokenInTurn.size() - 2));
      }
      currentTurn++;
    }

    LinkedList<Integer> getHistory(int number) {
      return mem.computeIfAbsent(number, n -> new LinkedList<>());
    }

    void sayNumber(int number) {
      lastNumberSpoken = number;
      LinkedList<Integer> history = getHistory(number);
      if (history.size() > 1) {
        history.removeFirst();
      }
      getHistory(number).add(currentTurn);
    }

    public int getCurrentTurn() {
      return currentTurn;
    }

    public int getLastNumberSpoken() {
      return lastNumberSpoken;
    }
  }

  public static void main(String[] args) {
//    var game = new NumbersGame("0,3,6");
//    System.out.println(game.currentTurn);
//    System.out.println(game.lastNumberSpoken);
//    game.play();
//    System.out.println(game.currentTurn);
//    System.out.println(game.lastNumberSpoken);
//    game.play();
//    System.out.println(game.currentTurn);
//    System.out.println(game.lastNumberSpoken);
//    while (game.currentTurn <= 10) {
//      game.play();
//      System.out.println(game.lastNumberSpoken);
//    }
//    while (game.currentTurn <= 2020) {
//      game.play();
//    }
//    System.out.println(game.lastNumberSpoken);

    var today = new Year2020Day15();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
