package org.chapi.map;

import org.chapi.map.mapper.DataOriginMappingBuilder;

/**
 * Created by Matt on 07/11/13.
 */
public interface Mapper {

  DataOriginMappingBuilder map(String path);
}
