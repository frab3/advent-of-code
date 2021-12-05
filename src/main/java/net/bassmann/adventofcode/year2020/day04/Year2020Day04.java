package net.bassmann.adventofcode.year2020.day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day04 extends AbstractDay {

  private final List<Map<String, String>> passports;
  private final List<String> validEyeColors =
      List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

  public Year2020Day04() {
    super(2020, 4);
    passports = parsePassports(getRiddleInput().asList());
  }

  List<Map<String, String>> parsePassports(List<String> input) {
    List<Map<String, String>> list = new ArrayList<>();
    Map<String, String> passport = new HashMap<>();
    for (String line : input) {
      if (line.isEmpty()) {
        list.add(passport);
        passport = new HashMap<>();
      } else {
        for (String data : line.split(" ")) {
          String[] split = data.split(":");
          passport.put(split[0], split[1]);
        }
      }
    }
    if (!passport.isEmpty()) {
      list.add(passport);
    }
    return list;
  }

  @Override
  public String solvePart1() {
    return Integer.toString(countValidPassports(passports));
  }

  int countValidPassports(List<Map<String, String>> passports) {
    return (int)
        passports.stream()
            .filter(m -> m.containsKey("byr"))
            .filter(m -> m.containsKey("iyr"))
            .filter(m -> m.containsKey("eyr"))
            .filter(m -> m.containsKey("hgt"))
            .filter(m -> m.containsKey("hcl"))
            .filter(m -> m.containsKey("ecl"))
            .filter(m -> m.containsKey("pid"))
            .count();
  }

  @Override
  public String solvePart2() {
    return Integer.toString(countValidPassports2(passports));
  }

  int countValidPassports2(List<Map<String, String>> passports) {
    return (int)
        passports.stream()
            .filter(m -> m.containsKey("byr") && isValidYearBetween(m.get("byr"), 1920, 2002))
            .filter(m -> m.containsKey("iyr") && isValidYearBetween(m.get("iyr"), 2010, 2020))
            .filter(m -> m.containsKey("eyr") && isValidYearBetween(m.get("eyr"), 2020, 2030))
            .filter(m -> m.containsKey("hgt") && isValidHgt(m.get("hgt")))
            .filter(m -> m.containsKey("hcl") && isValidHcl(m.get("hcl")))
            .filter(m -> m.containsKey("ecl") && validEyeColors.contains(m.get("ecl")))
            .filter(m -> m.containsKey("pid") && isValidPid(m.get("pid")))
            .count();
  }

  private boolean isValidHcl(String hcl) {
    if (hcl.charAt(0) != '#') {
      return false;
    }
    try {
      int c = Integer.decode(hcl);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isValidYearBetween(String byr, int lower, int upper) {
    if (byr.length() != 4) return false;
    return isValidNumberBetweem(byr, lower, upper);
  }

  private boolean isValidNumberBetweem(String number, int lower, int upper) {
    try {
      int n = Integer.parseInt(number);
      return n >= lower && n <= upper;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isValidHgt(String hgt) {
    String value = hgt.substring(0, hgt.length() - 2);
    String suffix = hgt.substring(hgt.length() - 2);
    switch (suffix) {
      case "cm":
        return isValidNumberBetweem(value, 150, 193);
      case "in":
        return isValidNumberBetweem(value, 59, 76);
      default:
        return false;
    }
  }

  private boolean isValidPid(String pid) {
    if (pid.length() != 9) return false;
    try {
      int n = Integer.parseUnsignedInt(pid);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static void main(String[] args) {
    Year2020Day04 today = new Year2020Day04();

    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
