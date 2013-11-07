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
      map("/data").toEndpoint("http://example.com/myendpoint");

      map("/user/").toEndpoint("http://users").fields("name,title,phone,address");
      map("/kind").toConstant("example#kind");
    }
  }
}
