package org.chapi.map.internal;

import org.chapi.map.spi.EndpointMapping;
import org.chapi.map.spi.MappingProjection;
import org.chapi.map.spi.MappingSourceVisitor;
import org.chapi.map.spi.UnresolvedEndpointMapping;

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Matt on 09/11/13.
 */
public class EndpointMappingImpl extends MappingImpl implements EndpointMapping {
  private final Class<?> endpoint;
  private final Method endpointMethod;


  public EndpointMappingImpl(UnresolvedEndpointMapping unresolvedMapping, Method method) {
    this(unresolvedMapping.getSourcePath(), unresolvedMapping.getMappingProjection(),
         unresolvedMapping.getEndpoint(), method);
  }

  public EndpointMappingImpl(String sourcePath, MappingProjection mappingProjection, Class<?> endpoint,
                             Method endpointMethod) {
    super(sourcePath, mappingProjection);
    this.endpoint = checkNotNull(endpoint);
    this.endpointMethod = checkNotNull(endpointMethod);
  }

  @Override
  public Class<?> getEndpoint() {
    return endpoint;
  }

  @Override
  public Method getEndpointMethod() {
    return endpointMethod;
  }

  @Override
  public <T> T acceptSourceVisitor(MappingSourceVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
