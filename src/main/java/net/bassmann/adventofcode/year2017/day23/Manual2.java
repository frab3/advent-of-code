package net.bassmann.adventofcode.year2017.day23;

public class Manual2 {

  public static void main(String[] args) {

    int a = 1;
    int b = 0;
    int c = 0;
//    int d = 0;
//    int e = 0;
    int f = 0;
    int g = 0;
    int h = 0;
    b = 79;
    c = b;

    if (a != 0) {
      b = b * 100 + 100000;
      c = b + 17000;
    }
    do {
      f = 1;
//      d = 2;
//      e = 2;
      for (int d = 2; d * d < b; d++) { // check if b is a prime
        // the assembly doesn't have a % operator,
        // so it does 2 for loops with d and e and checks if d*e==b.
        if ((b % d == 0)) {
          f = 0;
          break;
        }
      }
      if (f == 0) // not a prime
        h++;
      g = b - c;
      b += 17;
    } while (g != 0); //stop when b==c (1000 iterations)

    System.out.println(h);
//    printf("%d\n", h);
//    return 0;
  }
}



