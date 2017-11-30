package net.bassmann.adventofcode.year2015.day19;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MoleculeMachine {

  private final List<ReplacementRule> rules;
  private final String initialMolecule;

  MoleculeMachine(List<ReplacementRule> rules, String initialMolecule) {
    this.rules = rules;
    this.initialMolecule = initialMolecule;
  }

  int countOneReplacements() {
    int count = 0;

    for (ReplacementRule r : rules) {
      count += countOccurrence(initialMolecule, r.getOriginal());
    }

    return count;
  }

  int countOneReplacements2() {
    return doAllOneReplacements().size();
  }

  private static int countOccurrence(String input, String search) {
    int lastIndex = 0;
    int count = 0;

    while (lastIndex != -1) {

      lastIndex = input.indexOf(search, lastIndex);

      if (lastIndex != -1) {
        count++;
        lastIndex += search.length();
      }
    }
    return count;
  }

  String doOneReplacement(ReplacementRule rule, int fromIndex) {
    return initialMolecule.substring(0, fromIndex)
        + rule.getReplacement()
        + initialMolecule.substring(fromIndex + rule.getOriginal().length());
  }

  private Set<String> doAllOneReplacements() {
    Set<String> allReplacements = new HashSet<>();
    for (ReplacementRule r : rules) {
      int lastIndex = 0;
      while (lastIndex != -1) {
        lastIndex = initialMolecule.indexOf(r.getOriginal(), lastIndex);

        if (lastIndex != -1) {
          allReplacements.add(doOneReplacement(r, lastIndex));
          lastIndex += r.getOriginal().length();
        }
      }
    }
    return allReplacements;
  }

  /* Below is a solution I implemented after reading Askalskis thread on reddit. Super smart. */

  private static long countTokens(String input) {
    return input.chars().filter(Character::isUpperCase).count();
  }

  private static long countParentheses(String input) {
    return countOccurrence(input, "Ar") + (long) countOccurrence(input, "Rn");
  }

  private static long countCommas(String input) {
    return input.chars().filter(c -> c == 'Y').count();
  }

  static long getAskalskisFormula(String input) {
    return countTokens(input) - countParentheses(input) - 2 * countCommas(input) - 1;
  }
}
