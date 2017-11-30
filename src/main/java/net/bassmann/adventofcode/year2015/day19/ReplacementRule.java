package net.bassmann.adventofcode.year2015.day19;

class ReplacementRule {

  private final String original;
  private final String replacement;

  ReplacementRule(String original, String replacement) {
    this.original = original;
    this.replacement = replacement;
  }

  static ReplacementRule fromString(String input) {
    String[] split = input.split(" ");
    return new ReplacementRule(split[0], split[2]);
  }

  String getOriginal() {
    return original;
  }

  String getReplacement() {
    return replacement;
  }
}
