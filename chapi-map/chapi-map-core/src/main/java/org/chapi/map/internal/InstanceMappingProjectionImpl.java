package org.chapi.map.internal;

import org.chapi.map.Projection;
import org.chapi.map.spi.InstanceMappingProjection;
import org.chapi.map.spi.MappingProjectionVisitor;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Matt on 09/11/13.
 */
public class InstanceMappingProjectionImpl implements InstanceMappingProjection {

  private final Projection<?, ?> instance;

  public InstanceMappingProjectionImpl(Projection<?, ?> instance) {
    this.instance = checkNotNull(instance);
  }

  @Override
  public Projection<?, ?> getInstance() {
    return instance;
  }

  @Override
  public <T> T visitMappingProjectionVisitor(MappingProjectionVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
