package net.bassmann.kodekalender.year2020.day02;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Julenissen skal levere pakker til alle snille barn. For å holde styr på pakkene merker alvene
 * hver pakke med et unikt tall. Den første pakken får tallet 0 (alvene nullindekserer selvsagt),
 * den andre pakken 1 osv. Julenissen drar alltid opp pakkene i numerisk rekkefølge fra sekken.
 *
 * <p>Julenissen hater av en eller annen grunn sifferet 7, og reagerer sterkt når han ser en pakke
 * med dette sifferet. Hans reaksjon er at han kaster pakken i søpla, og i ukontrollert sinne også
 * kaster de P neste pakkene, hvor P er nermeste primtall som er mindre eller lik tallet på pakken.
 *
 * <p>Oppgave
 *
 * <p>Julenissen skal levere pakker til alle de snille barna i Norge. Terskelen er ganske lav for
 * hva Julenissen anser som et snilt barn, vi er alle snille barn i Julenissens øyne. Dvs. hele
 * norges befolkning på 5433000, skal få en pakke levert. Hvor mange av disse pakkene vil faktisk
 * bli levert?
 *
 * <p>Eksempel
 *
 * <p>Når julenissen drar opp pakke nr. 27, vil julenissen kaste pakke nr 27 + de 23 neste pakkene.
 * Dvs. pakkene 27-50 vil aldri bli levert.
 *
 * <p>For 10 pakker vil 7 bli levert. Pakkene med nr 0, 1, 2, 3, 4, 5 og 6.
 *
 * <p>For 20 pakker vil 9 bli levert. Pakkene med nr 0, 1, 2, 3, 4, 5, 6, 15, 16.
 *
 * <p>For 10 000 Pakker vil 32 bli levert.
 */
public class Year2020Day02 {

  private static final int antallBefolkning = 5433000;
//  private static final int antallBefolkning = 10_000;
//  private static final int antallBefolkning = 20;

  public static void main(String[] args) {
    int i = 0;
    int countLevert = 0;
    while (i < antallBefolkning) {
      if (containsSeven(i)) {
        // kast bort pakkene
        int nextLowerPrime = getNextLowerPrime(i);
        i = i + 1 + nextLowerPrime;
      } else {
        // levere
        System.out.println("l: " + i);
        countLevert++;
        i++;
      }
    }
    System.out.println(Integer.toString(countLevert));
  }

  private static boolean containsSeven(int i) {
    return Integer.toString(i).indexOf('7') != -1;
  }

  private static int getNextLowerPrime(int input) {
    return IntStream.iterate(input, i -> i - 1).filter(i -> isPrime(i)).findFirst().orElse(-1);
  }

  private static boolean isPrime(long number) {
    return number > 1L
        && LongStream.rangeClosed(2, (long)Math.sqrt(number))
        .noneMatch(n -> (number % n == 0));
  }
}
