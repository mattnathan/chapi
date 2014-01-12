package chapi.samples.helloworld.providers;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.util.Key;
import com.google.common.base.Charsets;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Writes any class that has at least one @Key annotation out as json.
 */
@Provider
@Produces("application/json")
@Singleton
public class KeyWriter implements MessageBodyWriter<Object> {

  private final JsonFactory jsonFactory;

  @Inject
  public KeyWriter(JsonFactory jsonFactory) {
    this.jsonFactory = jsonFactory;
  }

  @Override
  public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
    // check if any fields have @Key on them
    for (Field field : aClass.getDeclaredFields()) {
      if (field.getAnnotation(Key.class) != null) {
        return true;
      }
    }

    return false;
  }

  @Override
  public long getSize(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
    return -1;
  }

  @Override
  public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType,
                      MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws
      IOException, WebApplicationException {
    JsonGenerator generator = jsonFactory.createJsonGenerator(outputStream, Charsets.UTF_8);
    generator.serialize(o);
    generator.flush();
  }
}
