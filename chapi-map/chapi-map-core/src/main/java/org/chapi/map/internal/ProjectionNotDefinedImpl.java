package org.chapi.map.internal;

import org.chapi.map.spi.MappingProjection;
import org.chapi.map.spi.MappingProjectionVisitor;

/**
 * Created by Matt on 09/11/13.
 */
public enum ProjectionNotDefinedImpl implements MappingProjection {
  INSTANCE;

  @Override
  public <T> T visitMappingProjectionVisitor(MappingProjectionVisitor<T> visitor) {
    return visitor.visitProjectionNotDefined();
  }
}
