package net.bassmann.adventofcode.ocr;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum SmallLetter {
  A("""
      .##.
      #..#
      #..#
      ####
      #..#
      #..#"""),
  B("""
      """),
  C("""
      .##.
      #..#
      #...
      #...
      #..#
      .##.
      """),
  D("""
      """),
  ;

  private final String letterString;

  private final Map<String, String> map = Arrays.stream(values()).collect(Collectors.toMap(SmallLetter::getLetterString, SmallLetter::name));

  SmallLetter(String letterString) {
    this.letterString = letterString;
  }

  String getLetterString() {
    return letterString;
  }

  String recognise(String input) {
    return map.getOrDefault(input, "");
    }
}
