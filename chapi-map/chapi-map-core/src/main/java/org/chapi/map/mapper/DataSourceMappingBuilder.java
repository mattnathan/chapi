package org.chapi.map.mapper;

/**
 * Created by Matt on 07/11/13.
 */
public interface DataSourceMappingBuilder {
  DataExtractMappingBuilder toEndpoint(String endpoint);

  <T> T from(Class<T> endpointDescription);

  void toConstant(String constant);
}
