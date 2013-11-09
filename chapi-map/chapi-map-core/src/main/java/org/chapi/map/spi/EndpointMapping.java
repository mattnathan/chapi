package org.chapi.map.spi;

import java.lang.reflect.Method;

/**
 * Created by Matt on 09/11/13.
 */
public interface EndpointMapping extends Mapping {
  Class<?> getEndpoint();

  Method getEndpointMethod();
}
