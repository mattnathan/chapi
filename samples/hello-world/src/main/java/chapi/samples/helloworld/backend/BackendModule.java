package chapi.samples.helloworld.backend;

import com.google.inject.AbstractModule;

/**
 * Created by Matt on 12/01/14.
 */
public class BackendModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(GreetingResource.class);
    bind(NameResource.class);
  }
}
