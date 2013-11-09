package org.chapi.map.spi;

/**
 * Visitor over element types
 */
public interface ElementVisitor<T> {

  T visit(Mapping mapping);

  T visit(Message message);
}
