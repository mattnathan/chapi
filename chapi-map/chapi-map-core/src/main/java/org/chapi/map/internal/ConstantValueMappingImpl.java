package org.chapi.map.internal;

import org.chapi.map.spi.ConstantValueMapping;
import org.chapi.map.spi.MappingSourceVisitor;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Matt on 09/11/13.
 */
public class ConstantValueMappingImpl extends MappingImpl implements ConstantValueMapping {

  private final String value;

  public ConstantValueMappingImpl(String sourcePath, String value) {
    super(sourcePath);
    this.value = checkNotNull(value);
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public <T> T acceptSourceVisitor(MappingSourceVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
