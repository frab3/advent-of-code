package net.bassmann.adventofcode.year2017.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.common.Solution;

public class Year2017Day21 extends AbstractDay {

  private Solution solution = null;

  public Year2017Day21() {
    super(2017, 21);
  }

  @Override
  public String solvePart1() {
    return getSolution().getPartOne();
  }

  @Override
  public String solvePart2() {
    return getSolution().getPartTwo();
  }

  Solution getSolution() {
    if (solution == null) {
      solution = solve(getRiddleInput().asList(), 5, 18);
    }
    return solution;
  }

  static Solution solve(List<String> input, int steps1, int steps2) {

    Map<String, String> patternMap = makeExtendedInput(input);

    List<String> image = startImage();

    long count1 = -1;
    for (int i = 0; i < steps2; i++) {
      final List<String> squares = makeSquares(image);

      List<String> newSquares = new ArrayList<>();
      for (String oldSquare : squares) {
        String newSquare = patternMap.get(oldSquare);
        if (newSquare == null) {
          throw new IllegalStateException("new square should not be null");
        }
        newSquares.add(newSquare);
      }
      image = makeImage(newSquares);

      if (i == steps1 - 1) {
        count1 = image.stream().flatMapToInt(String::chars).filter(c -> (char) c == '#').count();
      }
    }

    // count on
    final long count2 =
        image.stream().flatMapToInt(String::chars).filter(c -> (char) c == '#').count();

    return new Solution(count1, count2);
  }

  private static List<String> makeImage(List<String> newSquares) {
    List<String> ret = new ArrayList<>();
    int perRow = (int) Math.sqrt(newSquares.size());
    int squareSize = newSquares.get(0).split("/")[0].length();
    for (int r = 0; r < perRow; r++) {
      for (int s = 0; s < squareSize; s++) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < perRow; c++) {
          String[] square = newSquares.get((r * perRow) + c).split("/");
          sb.append(square[s]);
        }
        ret.add(sb.toString());
      }
    }
    return ret;
  }

//  private static Map<String, String> makePatternMap(List<String> extendedInput) {
//    Map<String, String> map = new HashMap<>();
//    for (String line : extendedInput) {
//      String[] split = line.split(" => ");
//      if (!map.containsKey(split[0])) {
//        map.put(split[0], split[1]);
//      }
//    }
//    return map;
//  }

  private static Map<String, String> makeExtendedInput(List<String> input) {
    Map<String, String> map = new HashMap<>();
    for (String i : input) {
      String[] split = i.split(" => ");
      List<String> variants = makeVariants(split[0]);
      variants.forEach(v -> map.put(v, split[1]));
    }
    return map;
  }

  private static List<String> makeVariants(String input) {
    List<String> ret = new ArrayList<>(getAllRotations(input));
    if (input.length() == 11) {
      String flipped = flip(input);
      ret.addAll(getAllRotations(flipped));
    }
    return ret;
  }

  private static List<String> getAllRotations(String input) {
    List<String> ret = new ArrayList<>();
    String s = input;
    ret.add(s);
    for (int i = 0; i < 3; i++) {
      s = rotate(s);
      ret.add(s);
    }
    return ret;
  }

  private static String rotate(String input) {
    StringBuilder sb = new StringBuilder();
    if (input.length() == 5) {
      sb.append(input.charAt(3))
          .append(input.charAt(0))
          .append("/")
          .append(input.charAt(4))
          .append(input.charAt(1));
    }
    if (input.length() == 11) {
      sb.append(input.charAt(8))
          .append(input.charAt(4))
          .append(input.charAt(0))
          .append("/")
          .append(input.charAt(9))
          .append(input.charAt(5))
          .append(input.charAt(1))
          .append("/")
          .append(input.charAt(10))
          .append(input.charAt(6))
          .append(input.charAt(2));
    }
    return sb.toString();
  }

  private static String flip(String input) {
    return input.substring(8, 11) + input.substring(3, 8) + input.substring(0, 3);
  }

  private static List<String> startImage() {
    return List.of(".#.", "..#", "###");
  }

  private static List<String> makeSquares(List<String> image) {
    final List<String> ret = new ArrayList<>();
    final int size = image.size() % 2 == 0 ? 2 : 3;
    final int iterations = image.size() / size;
    for (int i = 0; i < iterations; i++) {
      for (int j = 0; j < iterations; j++) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
          final int rowOffset = i * size;
          final int colOffset = j * size;
          sb.append(image.get(rowOffset + row).substring(colOffset, colOffset + size));
          if (row < size - 1) {
            sb.append("/");
          }
        }
        ret.add(sb.toString());
      }
    }
    return ret;
  }



  public static void main(String[] args) {
    List<String> example = List.of("../.# => ##./#../...", ".#./..#/### => #..#/..../..../#..#");
    Year2017Day21 d = new Year2017Day21();
    //System.out.println((makeVariants(".#./..#/###")));
       System.out.println(solve(d.getRiddleInput().asList(), 5,18));
  }
}
