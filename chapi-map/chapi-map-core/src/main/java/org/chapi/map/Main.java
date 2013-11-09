package org.chapi.map;

/**
 * Example file for testing out some DSL ideas
 */
public class Main {
  public static void main(String[] args) {
    Projector projector = Mapi.createProjector(new FirstGraph());
  }

  private static class FirstGraph extends AbstractGraph {
    @Override
    protected void configure() {
      // map the target path /data to the result of the endpoint given by the url
      map("/data")
              .from(TestEndpoints.class).urlShortener();

      // map specific fields from a named endpoint
      map("/user/")
              .toFields("name,title,phone,address")
              .from(TestEndpoints.class).urlShortener();

      // map to a constant
      map("/kind").toConstant("example#kind");
    }
  }

  private static interface TestEndpoints {
    void urlShortener();
  }
}
