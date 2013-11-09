package org.chapi.map.internal;

import com.google.common.reflect.Reflection;
import org.chapi.map.Mapper;
import org.chapi.map.Projection;
import org.chapi.map.mapper.DataOriginMappingBuilder;
import org.chapi.map.mapper.DataSourceMappingBuilder;
import org.chapi.map.spi.Element;
import org.chapi.map.spi.Mapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Builder of mappings
 */
public class MappingBuilder implements DataOriginMappingBuilder {

  private static final String NULL_CONSTANT = "Binding to a constant value is not allowed";
  private static final String ALREADY_SOURCED = "Mapping source set more than once";
  private static final String ALREADY_PROJECTED = "Mapping projection set more than once";
  private final List<Element> mappings;
  private final int position;
  private Mapping mapping;
  private Mapper mapper;

  public MappingBuilder(Mapper mapper, List<Element> mappings, String path) {
    this.mapper = checkNotNull(mapper);
    this.mappings = checkNotNull(mappings);
    // need position so we can add errors to the list and still replace the mapping instance
    this.position = mappings.size();
    this.mapping = new UnsourcedMappingImpl(path, ProjectionNotDefinedImpl.INSTANCE);
    this.mappings.add(position, mapping);
  }

  @Override
  public <T> T from(Class<T> endpointDescription) {
    checkNotSourced();

    if (endpointDescription != null) {
      if (!endpointDescription.isInterface()) {
        mapper.addError("Endpoint type is not an interface: %s", endpointDescription);
      }

      final UnresolvedEndpointMappingImpl unresolvedMapping =
          new UnresolvedEndpointMappingImpl(mapping.getSourcePath(), mapping.getMappingProjection(),
                                            endpointDescription);
      setMapping(unresolvedMapping);
      return Reflection.newProxy(endpointDescription, new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          if (args.length > 0) {
            mapper.addError("Arguments are not supported for endpoint method %s", method);
          }
          setMapping(new EndpointMappingImpl(unresolvedMapping, method));
          return null;
        }
      });
    } else {
      mapper.addError("Binding to a null endpoint descriptor class is not allowed");
      // can't do anything else
      return null;
    }
  }

  @Override
  public void toConstant(String constant) {
    checkNotSourced();
    if (constant == null) {
      mapper.addError(NULL_CONSTANT);
      constant = ""; // so we can continue
    }
    setMapping(new ConstantValueMappingImpl(mapping.getSourcePath(), constant));
  }

  @Override
  public DataSourceMappingBuilder toFields(String fields) {
    checkNotProjected();
    checkNotSourced(); // can't project if we have a source already, part of the dsl
    setMapping(new UnsourcedMappingImpl(mapping.getSourcePath(), new FieldsMappingProjectionImpl(fields)));
    return this;
  }

  @Override
  public <F, T> DataSourceMappingBuilder toProjection(Projection<F, T> projection) {
    checkNotProjected();
    checkNotSourced(); // can't project if we have a source already, part of the dsl
    return this;
  }

  private void setMapping(Mapping mapping) {
    this.mapping = mapping;
    mappings.set(position, mapping);
  }

  private void checkNotSourced() {
    if (!(mapping instanceof UnsourcedMappingImpl)) {
      mapper.addError(ALREADY_SOURCED);
    }
  }

  private void checkNotProjected() {
    if (mapping.getMappingProjection() != ProjectionNotDefinedImpl.INSTANCE) {
      mapper.addError(ALREADY_PROJECTED);
    }
  }
}
