package org.chapi.map.spi;

/**
 * An error message
 */
public class Message implements Element {
  private final String message;

  public Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return message;
  }

  @Override
  public <T> T acceptVisitor(ElementVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
