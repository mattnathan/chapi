package org.chapi.map.spi;

/**
 * Visit the different strategies for finding the source of a mapping
 */
public interface MappingSourceVisitor<T> {
  T visit(UnsourcedMapping unsourcedMapping);

  T visit(ConstantValueMapping constantValueMapping);

  T visit(UnresolvedEndpointMapping unresolvedEndpointMapping);

  T visit(EndpointMapping endpointMapping);
}
