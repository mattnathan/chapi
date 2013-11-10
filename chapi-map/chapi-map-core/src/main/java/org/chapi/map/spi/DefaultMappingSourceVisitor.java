package org.chapi.map.spi;

/**
 * Created by Matt on 10/11/13.
 */
public class DefaultMappingSourceVisitor<T> implements MappingSourceVisitor<T> {

  public T visitOther(Mapping mapping) {
    return null;
  }

  @Override
  public T visit(UnsourcedMapping unsourcedMapping) {
    return visitOther(unsourcedMapping);
  }

  @Override
  public T visit(ConstantValueMapping constantValueMapping) {
    return visitOther(constantValueMapping);
  }

  @Override
  public T visit(UnresolvedEndpointMapping unresolvedEndpointMapping) {
    return visitOther(unresolvedEndpointMapping);
  }

  @Override
  public T visit(EndpointMapping endpointMapping) {
    return visitOther(endpointMapping);
  }
}
