package org.chapi.map;

/**
 * Defines a projection from one type to another
 */
public interface Projection<F, T> {
  /**
   * Convert the given type to a target type.
   *
   * @param from The source object
   * @return The target object
   */
  T project(F from);
}
