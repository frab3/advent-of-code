package net.bassmann.adventofcode.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class RiddleInputTest {

  @Test
  void getInputFilePath() {
    RiddleInput sut = new RiddleInput(LocalDate.of(2015, 12, 1));

    String actual = sut.getInputFilePath();

    assertEquals("2015/day01.input", actual);
  }

  @Test
  void getPathTest() throws IOException {
    RiddleInput sut = new RiddleInput(LocalDate.of(1234, 12, 23));

    Path actual = sut.getPath();

    assertNotNull(actual);
  }

  @Test
  void getPath_throwsNoInputFileException() throws IOException {
    RiddleInput sut = new RiddleInput(LocalDate.of(1234, 12, 22));

    assertThrows(NoInputFileException.class, sut::getPath);
  }

  @Test
  void getFirstLine() {
    RiddleInput sut = new RiddleInput(LocalDate.of(1234, 12, 23));

    String actual = sut.firstLine();

    assertEquals("line 1", actual);
  }

  @Test
  void getFirstLine_throwsRiddleInoutAccessException() {
    RiddleInput sut = new RiddleInput(LocalDate.of(1234, 12, 22));

    assertThrows(RiddleInputAccessException.class, sut::firstLine);
  }
}
