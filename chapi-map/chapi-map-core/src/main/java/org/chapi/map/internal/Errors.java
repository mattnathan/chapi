package org.chapi.map.internal;

/**
 * Represents errors.
 */
public class Errors {
  public static String format(String message, Object... values) {
    return String.format(message, values);
  }
}
