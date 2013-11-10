package org.chapi.map.internal;

import com.google.api.client.util.Lists;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.chapi.map.Graph;
import org.chapi.map.Projector;
import org.chapi.map.spi.DefaultElementVisitor;
import org.chapi.map.spi.Element;
import org.chapi.map.spi.Mapping;

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
    ImmutableList<Element> elements = Elements.collectElements(graphs);
    List<Mapping> mappings = Lists.newArrayList();
    MappingCollector mappingCollector = new MappingCollector(mappings);
    for (Element element : elements) {
      element.acceptVisitor(mappingCollector);
    }
    return new ProjectorImpl(ImmutableList.copyOf(mappings));
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
