package org.chapi.map.spi;

/**
 * Defines an element in the graph
 */
public interface Element {
  <T> T acceptVisitor(ElementVisitor<T> visitor);
}
