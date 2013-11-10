package org.chapi.map.internal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.chapi.map.CreationException;
import org.chapi.map.spi.Message;

import java.util.List;

/**
 * Represents errors.
 */
public class Errors {
  public static String format(String message, Object... values) {
    return String.format(message, values);
  }

  private final List<Message> messages = Lists.newArrayList();

  public void noSourceDefined(String target) {
    addMessage("No source defined for target path %s." +
               " Did you forget to map to something? Consider toConstant or toEndpoint",
               target);
  }

  public void addMessage(String message, Object... values) {
    addMessage(new Message(format(message, values)));
  }

  public void addMessage(Message message) {
    messages.add(message);
  }

  public void throwCreationExceptionIfErrorsExist() {
    if (!messages.isEmpty()) {
      throw new CreationException(ImmutableList.copyOf(messages));
    }
  }
}
