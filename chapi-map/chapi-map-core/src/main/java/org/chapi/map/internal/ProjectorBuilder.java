package org.chapi.map.internal;

import com.google.api.client.util.Lists;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.chapi.map.Graph;
import org.chapi.map.Projector;
import org.chapi.map.spi.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by Matt on 10/11/13.
 */
public class ProjectorBuilder {
  private final Collection<Graph> graphs = Lists.newArrayList();

  public ProjectorBuilder withGraphs(Iterable<? extends Graph> graphs) {
    Iterables.addAll(this.graphs, graphs);
    return this;
  }

  public Projector build() {
    final Errors errors = new Errors();

    // do validation of the mappings and ultimately construct the Projector if we can

    // todo: handle existing messages

    ImmutableList<Element> elements = Elements.collectElements(graphs);
    ImmutableList<Mapping> mappings = collectMappings(elements);

    // check for unsourced mappings
    for (Mapping mapping : mappings) {
      mapping.acceptSourceVisitor(new DefaultMappingSourceVisitor<Void>() {
        @Override
        public Void visit(UnsourcedMapping unsourcedMapping) {
          errors.noSourceDefined(unsourcedMapping.getSourcePath());
          return null;
        }
      });
    }

    // throw any errors that we may need to throw
    errors.throwCreationExceptionIfErrorsExist();

    return new ProjectorImpl(mappings);
  }

  private ImmutableList<Mapping> collectMappings(ImmutableList<Element> elements) {
    List<Mapping> mappings = Lists.newArrayList();
    MappingCollector mappingCollector = new MappingCollector(mappings);
    for (Element element : elements) {
      element.acceptVisitor(mappingCollector);
    }
    return ImmutableList.copyOf(mappings);
  }

  private static class MappingCollector extends DefaultElementVisitor<Void> {
    private final Collection<Mapping> mappings;

    private MappingCollector(Collection<Mapping> mappings) {
      this.mappings = mappings;
    }

    @Override
    public Void visit(Mapping mapping) {
      mappings.add(mapping);
      return super.visit(mapping);
    }
  }
}
