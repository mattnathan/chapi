package chapi.samples.helloworld.frontend;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Represents the public api.
 */
@Path("/frontend/greet")
public class GreetingApiResource {
  private final BackendService backend;

  @Inject
  public GreetingApiResource(BackendService backend) {
    this.backend = backend;
  }

  @GET
  @Produces("application/json")
  public Message sayHello() throws IOException {
    String name = backend.name().generateName();
    Greeting backendResponse = backend.greeting().greet(name);
    return convertBackend(backendResponse, name);
  }

  private Message convertBackend(Greeting greeting, String name) {
    return new Message().valuesFrom(greeting, name);
  }
}
