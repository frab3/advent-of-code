package net.bassmann.dass.year2020;

import java.io.IOException;
import java.net.URL;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Year2020Day02 {

  final static String inputFilePath = "dass/2020/pen_gwyn_greatest_hits.mid";
  final static URL inputURL = ClassLoader.getSystemClassLoader().getResource(inputFilePath);

  public static void main(String[] args) throws InvalidMidiDataException, IOException {
    Sequence sequence = MidiSystem.getSequence(inputURL);
    Track t = sequence.getTracks()[0];
    int events = t.size();
    for (int e = 0; e<events; e++) {
      MidiMessage mm = t.get(e).getMessage();
      if (mm instanceof ShortMessage) {
        ShortMessage m = (ShortMessage)mm;
        if (m.getCommand() == ShortMessage.NOTE_ON) {
          System.out.print(Character.toString((char)m.getData1()));
          // <=>?@ABCDEFGHIJKLMNOPQRSPST{BabyPenGwynDuhDuhDuhDuhDuhDuh}TSRQPONMLKJIHGFEDCBA@?>=<
        }
      }

    }
  }
}
