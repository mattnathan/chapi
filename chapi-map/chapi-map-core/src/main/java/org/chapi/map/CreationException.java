package org.chapi.map;

import com.google.common.collect.ImmutableList;
import org.chapi.map.spi.Message;

/**
 * Created by Matt on 10/11/13.
 */
public class CreationException extends RuntimeException {
  private final ImmutableList<Message> messages;

  public CreationException(ImmutableList<Message> messages) {
    this.messages = messages;
  }

  public ImmutableList<Message> getMessages() {
    return messages;
  }

  @Override
  public String getMessage() {
    return messages.toString();
  }
}
