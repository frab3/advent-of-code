package net.bassmann.adventofcode.year2020.day08;

import java.util.List;
import java.util.stream.Collectors;

public class BootCodeLoader {

  private final List<Instruction> instructionList;

  public BootCodeLoader(List<Instruction> instructionList) {
    this.instructionList = instructionList;
  }

  Result run() {
    final boolean[] executed = new boolean[instructionList.size()];
    int pos = 0;
    int acc = 0;
    while (pos < instructionList.size() && !executed[pos]) {
      executed[pos] = true;
      Instruction instruction = instructionList.get(pos);
      switch (instruction.getOperation()) {
        case NOP:
          pos++;
          break;
        case ACC:
          acc += instruction.getArgument();
          pos++;
          break;
        case JMP:
          pos += instruction.getArgument();
          break;
      }
    }
    return new Result(acc, pos >= instructionList.size());
  }

  static BootCodeLoader fromCode(List<String> code) {
    return new BootCodeLoader(
        code.stream().map(Instruction::fromString).collect(Collectors.toList()));
  }

  public static void main(String[] args) {
    var input =
        List.of(
            "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1", "jmp -4",
            "acc +6");
    var bcl = BootCodeLoader.fromCode(input);
    System.out.println(bcl.run().getAccumulator());
  }

  static class Result {

    public int getAccumulator() {
      return accumulator;
    }

    public boolean isFinished() {
      return finished;
    }

    private final int accumulator;
    private final boolean finished;

    Result(int accumulator, boolean finished) {
      this.accumulator = accumulator;
      this.finished = finished;
    }
  }
}
