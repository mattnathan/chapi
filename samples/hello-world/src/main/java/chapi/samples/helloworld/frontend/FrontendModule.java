package chapi.samples.helloworld.frontend;

import com.google.inject.AbstractModule;

/**
 * Module that binds all frontend functionality.
 */
public class FrontendModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new BackendServiceModule("http://localhost:8080"));
    bind(GreetingApiResource.class);
  }
}
