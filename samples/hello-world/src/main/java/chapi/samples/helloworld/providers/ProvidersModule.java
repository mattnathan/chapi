package chapi.samples.helloworld.providers;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.inject.AbstractModule;

import javax.inject.Singleton;

/**
 * Bind JAX-RS providers.
 */
public class ProvidersModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(JsonFactory.class).to(JacksonFactory.class).in(Singleton.class);
    bind(KeyWriter.class).in(Singleton.class);
  }
}
