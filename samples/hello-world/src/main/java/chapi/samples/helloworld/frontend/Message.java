package chapi.samples.helloworld.frontend;

import com.google.api.client.util.Key;

/**
 * Represents a message
 */
public class Message {
  @Key
  public String displayText;
  @Key
  public String plainText;
  @Key
  public String name;

  public Message valuesFrom(Greeting greeting, String name) {
    displayText = greeting.formatted;
    plainText = greeting.plain;
    this.name = name;
    return this;
  }
}
