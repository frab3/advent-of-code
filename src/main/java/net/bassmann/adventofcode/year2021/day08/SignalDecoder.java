package net.bassmann.adventofcode.year2021.day08;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class SignalDecoder {

  static enum SevenSegment {
    ZERO(5),
    ONE(2),
    TWO(5),
    THREE(5),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(3),
    EIGHT(7),
    NINE(6);

    public static final Set<SevenSegment> easy = EnumSet.of(ONE, FOUR, SEVEN, EIGHT);
    private final int segments;

    SevenSegment(int segments) {
      this.segments = segments;
    }

    static boolean isEasy(int i) {
      return i == 2 || i == 3 || i == 4 || i == 7;
    }
  }

  private static final Pattern pattern = Pattern.compile(" ");
  private static final Pattern inOutPattern = Pattern.compile(" \\| ");

  static int countEasyOutputValues(String line) {
    var out = inOutPattern.split(line)[1];
    return (int)
        pattern.splitAsStream(out).mapToInt(String::length).filter(SevenSegment::isEasy).count();
  }

  static int decode(String line) {
    var split = inOutPattern.split(line);
    var in = pattern.splitAsStream(split[0]).map(s -> sortChars(s)).toList();
    var out = pattern.splitAsStream(split[1]).map(s -> sortChars(s)).toList();

    final String zero, one, two, three, four, five, six, seven, eight, nine;

    one = in.stream().filter(s -> s.length() == 2).findFirst().orElseThrow();
    //    System.out.println("1 is " + one);
    seven = in.stream().filter(s -> s.length() == 3).findFirst().orElseThrow();
    //    System.out.println("7 is " + seven);
    four = in.stream().filter(s -> s.length() == 4).findFirst().orElseThrow();
    //    System.out.println("4 is " + four);
    var twoThreeFive = in.stream().filter(s -> s.length() == 5).toList();
    var zeroSixNine = in.stream().filter(s -> s.length() == 6).toList();
    eight = in.stream().filter(s -> s.length() == 7).findFirst().orElseThrow();

    final char t, tl, tr, m, bl, br, b;

    // figure out top, which is difference between one and seven:
    t =
        (char)
            seven
                .chars()
                .filter(c -> !one.contains(Character.toString(c)))
                .findFirst()
                .orElseThrow();
    //    System.out.println("t " + t);

    // figure out six, is the only with 5 segments when we substract 1 from (0,6,9).
    six =
        zeroSixNine.stream()
            .filter(s -> removeFrom(s, one).length() == 5)
            .findFirst()
            .orElseThrow();
    //    System.out.println("6 is " + six);

    // top right is one - six
    tr = removeFrom(one, six).charAt(0);
    //    System.out.println("tr is " + tr);

    // bottom right is one - top right
    br = removeFrom(one, tr).charAt(0);
    //    System.out.println("br is " + br);

    var fourPlusTop = add(four, t);
    //    System.out.println("4 + t is " + fourPlusTop);

    var zeroNine = zeroSixNine.stream().filter(s -> !six.equals(s)).toList();

    var maybeB = removeFrom(zeroNine.get(0), fourPlusTop);
    if (maybeB.length() == 1) {
      b = maybeB.charAt(0);
      nine = zeroNine.get(0);
      zero = zeroNine.get(1);
    } else {
      b = removeFrom(zeroNine.get(1), fourPlusTop).charAt(0);
      nine = zeroNine.get(1);
      zero = zeroNine.get(0);
    }
    //    System.out.println("b is " + b);
    //    System.out.println("9 is " + nine);
    //    System.out.println("0 is " + zero);

    m = removeFrom(four, zero).charAt(0);
    //    System.out.println("m is " + m);

    five =
        twoThreeFive.stream()
            .filter(s -> removeFrom(s, six).length() == 0)
            .findFirst()
            .orElseThrow();
    //    System.out.println("5 is " + five);
    // 0,6,9 -> size 6
    // 2,3,5 -> size 5

    three =
        Arrays.stream(new int[] {t, m, b, tr, br})
            .sorted()
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    //    System.out.println("3 is " + three);

    two =
        twoThreeFive.stream()
            .filter(s -> !s.equals(three))
            .filter(s -> !s.equals(five))
            .findFirst()
            .orElseThrow();
    //    System.out.println("2 is " + two);

    Map<String, Integer> intMap = new HashMap<>();
    intMap.put(zero, 0);
    intMap.put(one, 1);
    intMap.put(two, 2);
    intMap.put(three, 3);
    intMap.put(four, 4);
    intMap.put(five, 5);
    intMap.put(six, 6);
    intMap.put(seven, 7);
    intMap.put(eight, 8);
    intMap.put(nine, 9);

    int multi = 1000;
    int sum = 0;
    for (String s : out) {
      sum += multi * intMap.get(s);
      multi = multi / 10;
    }

    return sum;
  }

  private static String removeFrom(String s, String one) {
    return s.chars()
        .filter(c -> !one.contains(Character.toString(c)))
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  private static String removeFrom(String s, char ch) {
    return s.chars()
        .filter(c -> c != ch)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  static String sortChars(String s) {
    return s.chars()
        .sorted()
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  static String add(String s, char c) {
    if (s.contains(String.valueOf(c))) {
      return s;
    } else {
      return (s + c)
          .chars()
          .sorted()
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    }
  }

  //  static class SevenSegment {
  //    char top, tl, tr, middle, bl, br, bottom;
  //
  //    final char[] segArray = new char[7];
  //  }

  public static void main(String[] args) {
    System.out.println();
  }
}
