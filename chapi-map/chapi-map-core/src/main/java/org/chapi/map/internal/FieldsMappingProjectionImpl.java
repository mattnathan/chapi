package org.chapi.map.internal;

import org.chapi.map.spi.FieldsMappingProjection;
import org.chapi.map.spi.MappingProjectionVisitor;

/**
 * Created by Matt on 09/11/13.
 */
public class FieldsMappingProjectionImpl implements FieldsMappingProjection {

  private final String fields;

  public FieldsMappingProjectionImpl(String fields) {
    this.fields = fields;
  }

  @Override
  public String getFields() {
    return fields;
  }

  @Override
  public <T> T visitMappingProjectionVisitor(MappingProjectionVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
