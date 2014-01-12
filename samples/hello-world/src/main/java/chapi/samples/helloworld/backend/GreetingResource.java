package chapi.samples.helloworld.backend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Simple echo service that returns what you give it.
 */
@Path("/backend/greeting")
public class GreetingResource {
  @GET
  @Path("/{name}")
  @Produces("application/json")
  public Greeting generateGreeting(@PathParam("name") String name) {
    Greeting greeting = new Greeting();
    greeting.plain = "Hello, " + name + "!";
    greeting.formatted = "Hello, <em>" + name + "</em>!";
    return greeting;
  }
}
