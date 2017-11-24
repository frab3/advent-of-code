package net.bassmann.adventofcode.year2015.day23;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 23: Opening the Turing Lock.
 *
 * <p>Little Jane Marie just got her very first computer for Christmas from some unknown benefactor.
 * It comes with instructions and an example program, but the computer itself seems to be
 * malfunctioning. She's curious what the program does, and would like you to help her run it.
 *
 * <p>The manual explains that the computer supports two registers and six instructions (truly, it
 * goes on to remind the reader, a state-of-the-art technology). The registers are named a and b,
 * can hold any non-negative integer, and begin with a value of 0. The instructions are as follows:
 *
 * <ul>
 *   <li>hlf r sets register r to half its current value, then continues with the next instruction.
 *   <li>tpl r sets register r to triple its current value, then continues with the next
 *       instruction.
 *   <li>inc r increments register r, adding 1 to it, then continues with the next instruction.
 *   <li>jmp offset is a jump; it continues with the instruction offset away relative to itself.
 *   <li>jie r, offset is like jmp, but only jumps if register r is even ("jump if even").
 *   <li>jio r, offset is like jmp, but only jumps if register r is 1 ("jump if one", not odd).
 * </ul>
 *
 * All three jump instructions work with an offset relative to that instruction. The offset is
 * always written with a prefix + or - to indicate the direction of the jump (forward or backward,
 * respectively). For example, jmp +1 would simply continue with the next instruction, while jmp +0
 * would continuously jump back to itself forever.
 *
 * <p>The program exits when it tries to run an instruction beyond the ones defined.
 *
 * <p>For example, this program sets a to 2, because the jio instruction causes it to skip the tpl
 * instruction:
 *
 * <pre>
 * inc a
 * jio a, +2
 * tpl a
 * inc a
 * </pre>
 *
 * <h2>Part One</h2>
 *
 * What is the value in register b when the program in your puzzle input is finished executing?
 */
public class Year2015Day23 extends AbstractDay {

  public Year2015Day23() {
    super(2015, 23);
  }

  @Override
  public String solvePart1() {
    return null;
  }

  @Override
  public String solvePart2() {
    return null;
  }
}
