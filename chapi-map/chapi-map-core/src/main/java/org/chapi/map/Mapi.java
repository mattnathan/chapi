package org.chapi.map;

import org.chapi.map.internal.ProjectorBuilder;

import java.util.Arrays;

/**
 * Entry point for the mapping tool set
 */
public class Mapi {
  /**
   * Create a new projector with mappings specified in the given graphs
   *
   * @param graphs The graphs to create mappings for
   * @return The projector
   * @throws CreationException if there are errors in the given graphs
   */
  public static Projector createProjector(Graph... graphs) {
    return createProjector(Arrays.asList(graphs));
  }

  /**
   * Create a new projector with mappings specified in the given graphs
   *
   * @param graphs The graphs to create mappings for
   * @return The projector
   * @throws CreationException if there are errors in the given graphs
   */
  public static Projector createProjector(Iterable<? extends Graph> graphs) {
    return new ProjectorBuilder()
        .withGraphs(graphs)
        .build();
  }

  private Mapi() {
  }
}
