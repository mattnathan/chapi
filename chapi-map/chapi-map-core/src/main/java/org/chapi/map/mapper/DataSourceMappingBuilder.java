package org.chapi.map.mapper;

/**
 * Created by Matt on 07/11/13.
 */
public interface DataSourceMappingBuilder {
  <T> T from(Class<T> endpointDescription);

  void fromConstant(String constant);
}
