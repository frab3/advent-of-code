package net.bassmann.adventofcode.common;

import java.io.IOException;
import java.time.LocalDate;

class NoInputFileException extends IOException {

  NoInputFileException(LocalDate day) {
    super(
        String.format(
            "No input file found for day %d in year %d", day.getDayOfMonth(), day.getYear()));
  }
}
