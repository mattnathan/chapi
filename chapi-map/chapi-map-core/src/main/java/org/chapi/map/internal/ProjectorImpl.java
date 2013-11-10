package org.chapi.map.internal;

import com.google.common.collect.ImmutableList;
import org.chapi.map.Projector;
import org.chapi.map.spi.Mapping;

/**
 * Created by Matt on 10/11/13.
 */
public class ProjectorImpl implements Projector {
  private final ImmutableList<Mapping> mappings;

  public ProjectorImpl(ImmutableList<Mapping> mappings) {
    this.mappings = mappings;
  }

  @Override
  public ImmutableList<Mapping> getAllMappings() {
    return mappings;
  }
}
