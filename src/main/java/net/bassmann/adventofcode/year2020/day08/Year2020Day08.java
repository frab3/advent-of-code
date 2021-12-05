package net.bassmann.adventofcode.year2020.day08;

import static net.bassmann.adventofcode.year2020.day08.Instruction.Operation.ACC;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.year2020.day08.BootCodeLoader.Result;

public class Year2020Day08 extends AbstractDay {

  public Year2020Day08() {
    super(2020, 8);
  }

  @Override
  public String solvePart1() {
    BootCodeLoader bcl = BootCodeLoader.fromCode(getRiddleInput().asList());
    return Integer.toString(bcl.run().getAccumulator());
  }

  @Override
  public String solvePart2() {
    List<Instruction> instructionList =
        getRiddleInput().lines().map(Instruction::fromString).collect(Collectors.toList());

    Result result =
        IntStream.range(0, instructionList.size())
            .filter(i -> instructionList.get(i).getOperation() != ACC)
            .mapToObj(i -> copyWithFlip(instructionList, i))
            .map(BootCodeLoader::new)
            .map(BootCodeLoader::run)
            .filter(Result::isFinished)
            .findFirst()
            .orElse(null);

    return Integer.toString(result.getAccumulator());
  }

  List<Instruction> copyWithFlip(List<Instruction> instructions, int instructionToFlip) {
    return IntStream.range(0, instructions.size())
        .mapToObj(i -> i == instructionToFlip ? instructions.get(i).flip() : instructions.get(i))
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    var today = new Year2020Day08();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }
}
