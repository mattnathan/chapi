package org.chapi.map.spi;

/**
 * Created by Matt on 10/11/13.
 */
public class DefaultElementVisitor<T> implements ElementVisitor<T> {

  public T visitOther(Element element) {
    return null;
  }

  @Override
  public T visit(Mapping mapping) {
    return visitOther(mapping);
  }

  @Override
  public T visit(Message message) {
    return visitOther(message);
  }
}
