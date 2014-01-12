package chapi.samples.helloworld.frontend;

import com.google.api.client.util.Key;

/**
 * This is a frontend clone of the data used in the backend greeting service
 */
public class Greeting {
  @Key
  public String plain;
  @Key
  public String formatted;
}
