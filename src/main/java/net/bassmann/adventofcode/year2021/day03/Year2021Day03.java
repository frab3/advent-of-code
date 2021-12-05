package net.bassmann.adventofcode.year2021.day03;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2021Day03 extends AbstractDay {

  public Year2021Day03() {
    super(2021, 3);
  }

  @Override
  public String solvePart1() {
    List<String> lines = getRiddleInput().asList();
    var l = solve1(lines);

    long mul = Integer.parseInt(l.get(0), 2) * Integer.parseInt(l.get(1), 2);
    return String.valueOf(mul);
  }

  @Override
  public String solvePart2() {
    List<String> lines = getRiddleInput().asList();
    var l = solve2(lines);

    long mul = Integer.parseInt(l.get(0), 2) * Integer.parseInt(l.get(1), 2);
    return String.valueOf(mul);
  }

  static List<String> solve1(List<String> input) {
    int totalLines = input.size();
    int totalBits = input.get(0).length();

    StringBuilder gamma = new StringBuilder();
    StringBuilder epsilon = new StringBuilder();

    for (int col = 0; col < totalBits; col++) {
      int ones = 0;
      int zeros = 0;

      for (int line = 0; line < totalLines; line++) {
        switch (input.get(line).charAt(col)) {
          case '0':
            zeros++;
            break;
          case '1':
            ones++;
            break;
        }
      }

      if (ones > zeros) {
        gamma.append("1");
        epsilon.append("0");
      } else {
        gamma.append("0");
        epsilon.append("1");
      }
    }
    return List.of(gamma.toString(), epsilon.toString());
  }

  static List<String> solve2(List<String> input) {
    var oPrefix = new StringBuilder();
    var co2Prefix = new StringBuilder();

    for (int col = 0; col < input.get(0).length(); col++) {
      var c = countZerosAndOnes(input, oPrefix.toString());
      if (c[2] > 1) {
        var v = c[1] >= c[0] ? "1" : "0";
        oPrefix.append(v);
      } else break;
    }

    for (int col = 0; col < input.get(0).length(); col++) {
      var c = countZerosAndOnes(input, co2Prefix.toString());
      if (c[2] > 1) {
        var v = c[0] <= c[1] ? "0" : "1";
        co2Prefix.append(v);
      } else break;
    }

    var oVal = input.stream().filter(s -> s.startsWith(oPrefix.toString())).findFirst();
    var co2Val = input.stream().filter(s -> s.startsWith(co2Prefix.toString())).findFirst();

    return List.of(oVal.orElse(""), co2Val.orElse(""));
  }

  static int[] countZerosAndOnes(List<String> input, String prefix) {
    var c = new int[3]; // zeros, ones, matches
    for (int line = 0; line < input.size(); line++) {
      var l = input.get(line);
      if (l.startsWith(prefix)) {
        c[2]++;
        switch (l.charAt(prefix.length())) {
          case '0':
            c[0]++;
            break;
          case '1':
            c[1]++;
            break;
        }
      }
    }
    return c;
  }

  static String mostCommon2(int[] c) {
    return c[1] >= c[0] ? "1" : "0";
  }

  public static void main(String[] args) {
    var today = new Year2021Day03();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
