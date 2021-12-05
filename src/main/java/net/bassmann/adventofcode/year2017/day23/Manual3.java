package net.bassmann.adventofcode.year2017.day23;

public class Manual3 {

  public static void main(String[] args) {
    int count = 0;
//    for num in range(106700,123700 + 1, 17) {
    for (int num = 107900; num <= 124900; num += 17) {
      for (int i =2; i< num; i++) {
        if (num % i == 0 ) {
          count += 1;
          break;
        }
      }
    }
    System.out.println(count);
  }
}
