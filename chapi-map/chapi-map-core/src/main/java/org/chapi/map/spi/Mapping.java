package org.chapi.map.spi;

/**
 * Created by Matt on 09/11/13.
 */
public interface Mapping extends Element {
  String getSourcePath();

  MappingProjection getMappingProjection();

  <T> T acceptSourceVisitor(MappingSourceVisitor<T> visitor);
}
