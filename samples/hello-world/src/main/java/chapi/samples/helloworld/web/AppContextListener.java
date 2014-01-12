package chapi.samples.helloworld.web;

import chapi.samples.helloworld.backend.BackendModule;
import chapi.samples.helloworld.frontend.FrontendModule;
import chapi.samples.helloworld.providers.ProvidersModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * Application entry point for the web application.
 */
public class AppContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        servletModule(),
        new ProvidersModule(),
        new FrontendModule(),
        new BackendModule()
    );
  }

  private Module servletModule() {
    return new JerseyServletModule() {
      @Override
      protected void configureServlets() {
        // serve all requests via jersey
        serve("/*").with(GuiceContainer.class);
      }
    };
  }
}
