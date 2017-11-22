package net.bassmann.adventofcode.year2015.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class DinnerParty {

  private final Map<String, PartyGuest> guests = new HashMap<>();
  private final List<String> guestNames = new ArrayList<>();

  DinnerParty() {}

  void addGuest(String s) {
    final String[] parts = s.split(" ");
    final String name = parts[0];
    final String modifier = parts[2];
    final String happiness = parts[3];
    final String other = parts[10];

    PartyGuest g = guests.computeIfAbsent(name, this::createNewPartyGuest);

    int value = (modifier.equals("lose") ? -1 : 1) * Integer.parseInt(happiness);

    g.setHappiness(other.substring(0, other.length() - 1), value);
  }

  private PartyGuest createNewPartyGuest(String name) {
    guestNames.add(name);
    return new PartyGuest(name);
  }

  /**
   * A ring with n elements has only (n-1)! permutations, so basically we keep the last element just
   * permutate the rest and set in the last element again to close the circle.
   *
   * @param order the current permutation, should have this.size() - 1 elements.
   */
  int getHappiness(List<Integer> order) {
    int sum =
        IntStream.range(0, order.size() - 1)
            .map(i -> getHappiness(order.get(i), order.get(i + 1)))
            .sum();
    sum += getHappiness(order.get(0), order.size());
    sum += getHappiness(order.get(order.size() - 1), order.size());
    return sum;
  }

  /**
   * This includes "me" as closing elements, so we need to have all n! permutations with "me" to
   * close the circle.
   *
   * @param order the current permutation, should have this.size() elements.
   */
  int getHappinessIncludingMe(List<Integer> order) {
    return IntStream.range(0, order.size() - 1)
        .map(i -> getHappiness(order.get(i), order.get(i + 1)))
        .sum();
  }

  int getHappiness(int i, int j) {
    String name1 = guestNames.get(i);
    String name2 = guestNames.get(j);
    return guests.get(name1).getHappiness(name2) + guests.get(name2).getHappiness(name1);
  }

  int size() {
    return guestNames.size();
  }
}
