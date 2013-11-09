package org.chapi.map.internal;

import org.chapi.map.spi.ElementVisitor;
import org.chapi.map.spi.Mapping;
import org.chapi.map.spi.MappingProjection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Matt on 09/11/13.
 */
public abstract class MappingImpl implements Mapping {
  private final String sourcePath;
  private final MappingProjection projection;

  protected MappingImpl(String sourcePath, MappingProjection projection) {
    this.sourcePath = checkNotNull(sourcePath);
    this.projection = checkNotNull(projection);
  }

  @Override
  public String getSourcePath() {
    return sourcePath;
  }

  @Override
  public MappingProjection getMappingProjection() {
    return projection;
  }

  @Override
  public <T> T acceptVisitor(ElementVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
