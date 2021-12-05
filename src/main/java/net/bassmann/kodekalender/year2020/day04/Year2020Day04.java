package net.bassmann.kodekalender.year2020.day04;

import java.time.LocalDate;
import java.util.stream.LongStream;
import net.bassmann.riddleinput.RiddleInput;

public class Year2020Day04 {

  public static void main(String[] args) {
    var input = new RiddleInput("kodekalender", LocalDate.of(2020, 12, 4));

    long sukker = 0;
    long mel = 0;
    long melk = 0;
    long egg = 0;

    for (String line : input.asList()) {
      for (String levering : line.split(", ")) {
        String[] split = levering.split(": ");
        int antall = Integer.parseInt(split[1]);
        switch (split[0]) {
          case "sukker":
            sukker += antall;
            break;
          case "mel":
            mel += antall;
            break;
          case "melk":
            melk += antall;
            break;
          case "egg":
            egg += antall;
            break;
        }
      }
    }

    long kake = LongStream.of(sukker / 2, mel / 3, melk / 3, egg).min().orElse(-1);

    System.out.println(Long.toString(kake)); //1458014
  }
}
