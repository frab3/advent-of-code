package net.bassmann.riddleinput;

import java.io.IOException;
import java.time.LocalDate;

public class NoInputFileException extends IOException {

  NoInputFileException(LocalDate day) {
    super(
        String.format(
            "No input file found for day %d in year %d", day.getDayOfMonth(), day.getYear()));
  }
}
