package org.chapi.map.internal;

import org.chapi.map.spi.ElementVisitor;
import org.chapi.map.spi.Mapping;
import org.chapi.map.spi.MappingProjectionVisitor;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Matt on 09/11/13.
 */
public abstract class MappingImpl implements Mapping {
  private final String sourcePath;

  public MappingImpl(String sourcePath) {
    this.sourcePath = checkNotNull(sourcePath);
  }

  @Override
  public String getSourcePath() {
    return sourcePath;
  }

  @Override
  public <T> T acceptVisitor(ElementVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public <T> T acceptProjectionVisitor(MappingProjectionVisitor<T> visitor) {
    return visitor.visitProjectionNotDefined();
  }
}
