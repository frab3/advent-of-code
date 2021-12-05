package net.bassmann.kodekalender.year2020.day01;

import java.time.LocalDate;
import net.bassmann.riddleinput.RiddleInput;

public class Year2020Day01 {

  private final RiddleInput input = new RiddleInput("kodekalender", LocalDate.of(2020, 12, 1));

  private final boolean[] check = new boolean[100_000];

  public static void main(String[] args) {
    var input = new RiddleInput("kodekalender", LocalDate.of(2020, 12, 1));
    var check = new boolean[100_001];

    System.out.println(check[0]);
    //var stringTokenizer = new StringTokenizer(input.firstLine(),",");
    //System.out.println(stringTokenizer.countTokens());
    //while (stringTokenizer.hasMoreElements()) {
    //  check[Integer.parseInt(stringTokenizer.nextToken())] = true;
    //}
    input.lines().mapToInt(Integer::parseInt).forEach(i -> check[i] = true);
    for (int i = 1; i < 100_001; i++) {
      if (!check[i]) {
        System.out.println("Found " + i);
        break;
      }
    }
  }
}
