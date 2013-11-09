package org.chapi.map.spi;

/**
 * Created by Matt on 09/11/13.
 */
public interface MappingProjection {
  <T> T visitMappingProjectionVisitor(MappingProjectionVisitor<T> visitor);
}
