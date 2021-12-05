package net.bassmann.riddleinput;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

/** Utility functions to access the input from a resource file, given a day. */
public class RiddleInput {

  private final String packageName;
  private final LocalDate day;

  /** Lazy variable that points to the riddle input. */
  private Path path;

  /* Lazy vars */
  private List<String> list = null;
  private String firstLine = null;

  public RiddleInput(LocalDate day) {
    packageName = null;
    this.day = day;
  }

  public RiddleInput(String packageName, LocalDate day) {
    this.packageName = packageName;
    this.day = day;
  }

  Path getPath() throws NoInputFileException {
    if (path == null) {
      final String inputFilePath = getInputFilePath();
      final URL inputURL = getClass().getClassLoader().getResource(inputFilePath);

      if (inputURL == null) {
        throw new NoInputFileException(day);
      }

      try {
        path = Paths.get(inputURL.toURI());
      } catch (URISyntaxException e) {
        throw new AssertionError("Could not get URI from URL", e);
      }
    }
    return path;
  }

  String getInputFilePath() {
    return packageName != null
        ? String.format("%s/%d/day%02d.input", packageName, day.getYear(), day.getDayOfMonth())
        : String.format("%d/day%02d.input", day.getYear(), day.getDayOfMonth());
  }

  public Stream<String> lines() {
    try {
      return Files.lines(getPath());
    } catch (IOException e) {
      throw new RiddleInputAccessException(e);
    }
  }

  /**
   * Returns the days riddle input as an immutable list of String; each line in the input file is a
   * String in the list. The file is only read once and cached.
   *
   * @return days riddle input as a list of String
   */
  public List<String> asList() {
    if (list == null) {
      list = readList();
    }
    return list;
  }

  private List<String> readList() {
    try {
      return Files.readAllLines(getPath());
    } catch (IOException e) {
      throw new RiddleInputAccessException(e);
    }
  }

  public String firstLine() {
    if (firstLine == null) {
      firstLine = readFirstLine();
    }
    return firstLine;
  }

  private String readFirstLine() {
    try (Stream<String> linesStream = Files.lines(getPath())) {
      return linesStream.findFirst().orElse("");
    } catch (IOException e) {
      throw new RiddleInputAccessException(e);
    }
  }
}
