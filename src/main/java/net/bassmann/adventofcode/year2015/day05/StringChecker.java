package net.bassmann.adventofcode.year2015.day05;

/**
 * To implement this, we implement a look back matcher, that iterates one time through the String.
 */
class StringChecker {

  boolean isNice(String s) {
    int pairs = 0;

    final char[] charArray = s.toCharArray();
    char previous = charArray[0];
    int vowelCount = isVowel(previous) ? 1 : 0;
    for (int i = 1, charArrayLength = charArray.length; i < charArrayLength; i++) {
      final char c = charArray[i];

      if (isNaughty(previous, c)) {
        return false;
      }

      if (isVowel(c)) {
        vowelCount++;
      }

      if (previous == c) {
        pairs++;
      }

      previous = c;
    }
    return vowelCount >= 3 && pairs > 0;
  }

  private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

  private boolean isNaughty(char c, char d) {
    return c == 'a' && d == 'b'
        || c == 'c' && d == 'd'
        || c == 'p' && d == 'q'
        || c == 'x' && d == 'y';
  }
}
