package chapi.samples.helloworld.backend;

import com.google.api.client.util.Key;

/**
 * Represents a greeting.
 */
public class Greeting {
  @Key
  public String plain;
  @Key
  public String formatted;
}
