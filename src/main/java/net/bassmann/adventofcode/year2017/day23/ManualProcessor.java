package net.bassmann.adventofcode.year2017.day23;

import static java.lang.String.format;

public class ManualProcessor {

  public static void main(String[] args) {
    int breaker = 0;

    long a = 1; // debug only
//    long b;
    final long c;
    long d = 0;
//    long e = 0;
    long f = 0;
    long g = 0;
    long h = 0;

    // start values
//    b = 79;
//    c = b;
//    if (a != 0) {
      final long init = 79 * 100 + 100000;
      c = init + 17000;
//    }
    // debug
//    System.out.println(
//        format("a: %d, b: %d, c: %d, d: %d, e: %d, f: %d, g: %d, h:%d", a, b, c, d, e, f, g, h));
    // a: 1, b: 107900, c: 124900, d: 0, e: 0, f: 0, g: 0, h:0

    for (long b = init; b < c; b += 17) {

      f = 1;
      d = 2;

      do {
//        e = 2;
        for (long e = 2; e<=b; e++)
         {
          g = d * e - b;
          if (g != 0) {
            f = 0;
          }

//          e += 1;
//          g = e;
        } //while (g != b);

        d -= 1;
        g = d;
      } while (g != b);

      if (f != 0) {
        h -= 1;
      }

//      g = b;
//      g -= c;
//      if (g == c) {
//        break;
//      }
//      b += 17;
    } while (g != 0);

    System.out.println(h);
  }
}
