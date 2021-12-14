package net.bassmann.adventofcode.year2021.day10;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day10 extends AbstractDay {

  Year2021Day10() {
    super(2021, 10);
  }

  @Override
  public String solvePart1() {
    var s =
        getRiddleInput()
            .lines()
            .map(Year2021Day10::findFirstCorrupted)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .mapToInt(Year2021Day10::getPoints)
            .sum();
    return String.valueOf(s);
  }

  @Override
  public String solvePart2() {
    var s =
        getRiddleInput()
            .lines()
            .mapToLong(Year2021Day10::generateMissing)
            .filter(i -> i != 0)
            .sorted()
            .toArray();

    System.out.println(s.length);
    System.out.println(Arrays.toString(s));
    return String.valueOf(s[s.length / 2]);
  }

  static Optional<Character> findFirstCorrupted(String line) {
    Deque<Character> stack = new LinkedList<>();
    for (char c : line.toCharArray()) {
      if (isOpening(c)) {
        stack.push(c);
      } else {
        char fromStack = stack.pop();
        if (!isBlock(fromStack, c)) {
          return Optional.of(c);
        }
      }
    }
    return Optional.empty();
  }

  static long generateMissing(String line) {
    Deque<Character> stack = new LinkedList<>();
    for (char c : line.toCharArray()) {
      if (isOpening(c)) {
        stack.push(c);
      } else {
        char fromStack = stack.pop();
        if (!isBlock(fromStack, c)) {
          return 0;
        }
      }
    }
    long total = 0;
    while (!stack.isEmpty()) {
      //System.out.println("Missing a bracket: "  + stack.peek());
      total *= 5;
      total += getPoints2(stack.pop());
    }
    return total;
  }

  static boolean isOpening(char c) {
    return c == '(' || c == '{' || c == '[' || c == '<';
  }

  static boolean isBlock(char open, char close) {
    return open == '<' && close == '>'
        || open == '(' && close == ')'
        || open == '[' && close == ']'
        || open == '{' && close == '}';
  }

  static int getPoints(char c) {
    switch (c) {
      case ')':
        return 3;
      case ']':
        return 57;
      case '}':
        return 1197;
      case '>':
        return 25137;
      default:
        throw new IllegalArgumentException();
    }
  }

  static int getPoints2(char c) {
    switch (c) {
      case '(':
        return 1;
      case '[':
        return 2;
      case '{':
        return 3;
      case '<':
        return 4;
      default:
        throw new IllegalStateException();
    }
  }

  public static void main(String[] args) {
    var today = new Year2021Day10();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
    //System.out.println(generateMissing("[({(<(())[]>[[{[]{<()<>>"));
  }
}
