package org.chapi.map;

import org.chapi.map.mapper.DataOriginMappingBuilder;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Abstract base class for easing the creation of graphs
 */
public abstract class AbstractGraph implements Graph {
  // todo: write a generator that will create this class from Mappers public methods.
  private Mapper mapper;

  @Override
  public final void configure(Mapper mapper) {
    checkState(this.mapper == null, "Re-entry not allowed");
    this.mapper = checkNotNull(mapper, "mapper");
    try {
      configure();
    } finally {
      this.mapper = null;
    }
  }

  protected abstract void configure();

  protected Mapper mapper() {
    return mapper;
  }

  protected DataOriginMappingBuilder map(String path) {
    return mapper.map(path);
  }
}
