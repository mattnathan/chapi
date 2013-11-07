package org.chapi.map;

import org.chapi.map.mapper.DataSourceMappingBuilder;

/**
 * Created by Matt on 07/11/13.
 */
public interface Mapper {

  DataSourceMappingBuilder map(String path);
}
