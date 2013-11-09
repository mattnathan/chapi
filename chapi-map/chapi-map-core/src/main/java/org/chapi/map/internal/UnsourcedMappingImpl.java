package org.chapi.map.internal;

import org.chapi.map.spi.MappingSourceVisitor;
import org.chapi.map.spi.UnsourcedMapping;

/**
 * Created by Matt on 09/11/13.
 */
public class UnsourcedMappingImpl extends MappingImpl implements UnsourcedMapping {

  public UnsourcedMappingImpl(String sourcePath) {
    super(sourcePath);
  }

  @Override
  public <T> T acceptSourceVisitor(MappingSourceVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
