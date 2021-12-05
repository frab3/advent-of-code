package net.bassmann.adventofcode.year2020.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class Year2020Day04Test {

  private final Year2020Day04 today = new Year2020Day04();

  private final List<String> testdata =
      List.of(
          "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
          "byr:1937 iyr:2017 cid:147 hgt:183cm",
          "",
          "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
          "hcl:#cfa07d byr:1929",
          "",
          "hcl:#ae17e1 iyr:2013",
          "eyr:2024",
          "ecl:brn pid:760753108 byr:1931",
          "hgt:179cm",
          "",
          "hcl:#cfa07d eyr:2025 pid:166559648",
          "iyr:2011 ecl:brn hgt:59in");

  @Test
  void parsePassportsTest() {
    List<Map<String, String>> passports = today.parsePassports(testdata);
    assertEquals(4, passports.size());

    int valid = today.countValidPassports(passports);
    assertEquals(2, valid);
  }


}
