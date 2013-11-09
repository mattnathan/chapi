package org.chapi.map.internal;

import org.chapi.map.spi.MappingProjection;
import org.chapi.map.spi.MappingSourceVisitor;
import org.chapi.map.spi.UnresolvedEndpointMapping;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Matt on 09/11/13.
 */
public class UnresolvedEndpointMappingImpl extends MappingImpl implements UnresolvedEndpointMapping {
  private final Class<?> endpoint;

  public UnresolvedEndpointMappingImpl(String sourcePath, MappingProjection mappingProjection, Class<?> endpoint) {
    super(sourcePath, mappingProjection);
    this.endpoint = checkNotNull(endpoint);
  }

  @Override
  public Class<?> getEndpoint() {
    return endpoint;
  }

  @Override
  public <T> T acceptSourceVisitor(MappingSourceVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
