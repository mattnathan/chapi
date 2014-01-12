package chapi.samples.helloworld.frontend;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import javax.inject.Singleton;

/**
 * Module for setting up the backend service.
 */
public class BackendServiceModule extends AbstractModule {

  private final String baseUrl;

  public BackendServiceModule(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Override
  protected void configure() {
    bind(HttpTransport.class).to(NetHttpTransport.class).in(Singleton.class);
    bind(String.class).annotatedWith(Names.named("backend.base.url")).toInstance(baseUrl);
  }

  @Provides
  HttpRequestFactory provideHttpRequestFactory(HttpTransport transport) {
    return transport.createRequestFactory();
  }
}
