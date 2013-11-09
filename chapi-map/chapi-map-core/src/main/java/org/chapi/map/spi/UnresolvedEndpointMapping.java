package org.chapi.map.spi;

/**
 * Created by Matt on 09/11/13.
 */
public interface UnresolvedEndpointMapping extends Mapping {
  Class<?> getEndpoint();
}
