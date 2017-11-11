package net.bassmann.adventofcode.year2015.day04;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CoinGeneratorTest {

  @ParameterizedTest
  @CsvSource({"abcdef, 609043, 000001dbbfa", "pqrstuv, 1048970, 000006136ef"})
  void CoinExamples(String secret, int input, String expected) {
    CoinGenerator cg = new CoinGenerator(secret);
    String actual = cg.getCoinHash(input);
    assertTrue(actual.startsWith(expected));
    assertTrue(cg.isAdventCoin(input));
  }
}