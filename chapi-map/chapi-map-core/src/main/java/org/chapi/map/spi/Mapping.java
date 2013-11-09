package org.chapi.map.spi;

/**
 * Created by Matt on 09/11/13.
 */
public interface Mapping extends Element {
  String getSourcePath();

  <T> T acceptSourceVisitor(MappingSourceVisitor<T> visitor);

  <T> T acceptProjectionVisitor(MappingProjectionVisitor<T> visitor);
}
