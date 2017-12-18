package net.bassmann.adventofcode.year2017.day18;

import java.util.List;
import java.util.Queue;

/**
 * This sound card process can communicate via pipes - or better to say queues. One read queue to
 * take values from and one send queue to push values to. It keeps track of how many items have been
 * pushed to the queue.
 */
class DoubleSoundCardProcess extends AbstractSoundCardProcess {

  private final Queue<Long> readQueue;
  private final Queue<Long> sendQueue;

  private int sentCount = 0;

  DoubleSoundCardProcess(List<String> instructions, Queue<Long> readQueue, Queue<Long> sendQueue) {
    super(instructions);
    this.readQueue = readQueue;
    this.sendQueue = sendQueue;
  }

  int getSentCount() {
    return sentCount;
  }

  void snd(String x) {
    long xVal = get(x);
    sendQueue.offer(xVal);
    sentCount += 1;
  }

  int rcv(String x) {
    if (readQueue.isEmpty()) {
      setWaiting(true);
      return 0;
    } else {
      setWaiting(false);
      set(x, readQueue.remove());
      return 1;
    }
  }
}
