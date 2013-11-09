package org.chapi.map.mapper;

import org.chapi.map.Projection;

/**
 * Created by Matt on 07/11/13.
 */
public interface DataExtractMappingBuilder {
  DataSourceMappingBuilder toFields(String fields);

  <F, T> DataSourceMappingBuilder toProjection(Projection<F, T> projection);
}
