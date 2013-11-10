package org.chapi.map;

import com.google.common.collect.ImmutableList;
import org.chapi.map.spi.Mapping;

/**
 * Represents a projection onto some target structure
 */
public interface Projector {
  ImmutableList<Mapping> getAllMappings();
}
