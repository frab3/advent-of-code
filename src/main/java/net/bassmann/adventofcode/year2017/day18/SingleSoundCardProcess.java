package net.bassmann.adventofcode.year2017.day18;

import java.util.List;

/**
 * Single sound card process keeps track of the last frequency played and stops, as soon as we hit
 * the first time {@code rcv} where the provided value is not zero. Afterwards the played frequency
 * can be polled.
 */
class SingleSoundCardProcess extends AbstractSoundCardProcess {

  private long playedFrequency = -1;

  SingleSoundCardProcess(List<String> instructions) {
    super(instructions);
  }

  int getPlayedFrequency() {
    return (int) playedFrequency;
  }

  @Override
  void snd(String x) {
    playedFrequency = get(x);
  }

  @Override
  int rcv(String x) {
    long f = get(x);
    if (f != 0) {
      setWaiting(true);
      return 0;
    } else {
      return 1;
    }
  }
}
