package chapi.samples.helloworld.backend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Matt on 12/01/14.
 */
@Path("/backend/name")
public class NameResource {
  @GET
  @Produces("text/plain")
  public String generateName() {
    return "World";
  }
}
