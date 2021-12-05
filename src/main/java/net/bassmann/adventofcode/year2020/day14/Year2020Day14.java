package net.bassmann.adventofcode.year2020.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day14 extends AbstractDay {

  public Year2020Day14() {
    super(2020, 14);
  }

  @Override
  public String solvePart1() {
    var bitmaskInit = new BitmaskInit();
    getRiddleInput().lines().forEach(bitmaskInit::processCommand);
    return Long.toString(bitmaskInit.getMomorySum());
  }

  @Override
  public String solvePart2() {
    var bitmaskInit = new BitmaskInit2();
    getRiddleInput().lines().forEach(bitmaskInit::processCommand);
    return Long.toString(bitmaskInit.getMomorySum());
  }

  static class BitmaskInit {
    private String mask;
    private final Map<Long, Long> memory = new HashMap<>();

    void processCommand(String command) {
      if (command.startsWith("mask")) {
        mask = command.substring(7);
      } else {
        String[] split = command.substring(4).split("] = ");
        long mempos = Long.parseLong(split[0]);
        String value = split[1];
        long masked = applyBitmask(value);
        System.out.println("mem pos: " + mempos + " value: " + value + " masked: " + masked);
        memory.put(mempos, masked);
      }
    }

    long applyBitmask(String input) {
      long l = Long.parseLong(input);
      char[] binaryOut = "000000000000000000000000000000000000".toCharArray();
      char[] binary = Long.toBinaryString(l).toCharArray();
      System.arraycopy(binary, 0, binaryOut, 36 - binary.length, binary.length);
      for (int i = 0; i <= 35; i++) {
        char m = mask.charAt(i);
        if (m != 'X') {
          binaryOut[i] = m;
        }
      }
      return Long.parseLong(String.valueOf(binaryOut), 2);
    }

    long getMomorySum() {
      return memory.values().stream().mapToLong(l -> l).sum();
    }
  }

  static class BitmaskInit2 {
    private String mask;
    private final Map<String, Long> memory = new HashMap<>();

    void processCommand(String command) {
      if (command.startsWith("mask")) {
        mask = command.substring(7);
      } else {
        String[] split = command.substring(4).split("] = ");
        char[] memPosBinary = makeBinary(split[0]);
        long value = Long.parseLong(split[1]);
        List<String> allMemPos = applyBitmask(memPosBinary, "");
        allMemPos.forEach(m -> memory.put(m, value));
      }
    }

    List<String> applyBitmask(char[] binary, String prefix) {
      if (prefix.length() == 36) {
        return List.of(prefix);
      }
      int pos = prefix.length();
      char m = mask.charAt(pos);
      switch (m) {
        case '0':
          return applyBitmask(binary, prefix + binary[pos]);
        case '1':
          return applyBitmask(binary, prefix + '1');
        case 'X':
          return Stream.concat(
                  applyBitmask(binary, prefix + '0').stream(),
                  applyBitmask(binary, prefix + '1').stream())
              .collect(Collectors.toList());
        default:
          return List.of();
      }
    }

    char[] makeBinary(String input) {
      long l = Long.parseLong(input);
      char[] binaryOut = "000000000000000000000000000000000000".toCharArray();
      char[] binary = Long.toBinaryString(l).toCharArray();
      System.arraycopy(binary, 0, binaryOut, 36 - binary.length, binary.length);
      return binaryOut;
    }

    long getMomorySum() {
      return memory.values().stream().mapToLong(l -> l).sum();
    }
  }

  public static void main(String[] args) {
    //    var bitmask = new BitmaskInit();
    //
    //    bitmask.processCommand("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
    //    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
    //    System.out.println(bitmask.mask);
    //    bitmask.processCommand("mem[8] = 11");
    //    bitmask.processCommand("mem[7] = 101");
    //    bitmask.processCommand("mem[8] = 0");
    //    System.out.println(bitmask.getMomorySum());

//    var bitmask = new BitmaskInit2();
//        bitmask.processCommand("mask = 000000000000000000000000000000X1001X");
//        bitmask.processCommand("mem[42] = 100");
//        bitmask.processCommand("mask = 00000000000000000000000000000000X0XX");
//        bitmask.processCommand("mem[26] = 1");
//        System.out.println(bitmask.getMomorySum());


    var today = new Year2020Day14();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
