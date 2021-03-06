package org.chapi.map.spi;

/**
 * Created by Matt on 09/11/13.
 */
public interface MappingProjectionVisitor<T> {
  T visitProjectionNotDefined();

  T visit(FieldsMappingProjection fieldsMappingProjection);

  T visit(InstanceMappingProjection instanceMappingProjection);
}
