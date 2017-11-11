package net.bassmann.adventofcode.year2015.day04;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class CoinGenerator {

  private final String secretKey;
  private final MessageDigest md;

  CoinGenerator(String secretKey) {
    this.secretKey = secretKey;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("Somehow there is no MD5 available", e);
    }
  }

  String getCoinHash(int input) {
    byte[] digest = getDigest(input);
    return toString(digest);
  }

  boolean isAdventCoin(int input) {
    byte[] digest = getDigest(input);
    return hasFiveLeadingZeros(digest);
  }

  boolean isImprovedAdventCoin(int input) {
    byte[] digest = getDigest(input);
    return hasSixLeadingZeros(digest);
  }

  private byte[] getDigest(int input) {
    String eatMe = secretKey + input;
    return md.digest(eatMe.getBytes());
  }

  private boolean hasFiveLeadingZeros(byte[] digest) {
    return digest[0] == 0 && digest[1] == 0 && digest[2] >= 0 && digest[2] < 16;
  }

  private boolean hasSixLeadingZeros(byte[] digest) {
    return digest[0] == 0 && digest[1] == 0 && digest[2] == 0;
  }

  /**
   * Transform the byte array of the md5 hash to a String representation.
   *
   * <p>Looking at StackOverflow, there are more solutions on how to convert a byte array to a hex
   * string, but I liked this one, because it is short. If the byte array may vary in length, or if
   * it is converted more often, then a different solution might be better.
   */
  private String toString(byte[] digest) {
    return String.format("%032x", new BigInteger(1, digest));
  }
}
