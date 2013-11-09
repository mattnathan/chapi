package org.chapi.map.spi;

import org.chapi.map.Projection;

/**
 * Created by Matt on 09/11/13.
 */
public interface InstanceMappingProjection extends MappingProjection {
  Projection<?, ?> getInstance();
}
