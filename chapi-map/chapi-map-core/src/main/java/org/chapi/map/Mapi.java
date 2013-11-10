package org.chapi.map;

import org.chapi.map.internal.ProjectorBuilder;

import java.util.Arrays;

/**
 * Entry point for the mapping tool set
 */
public class Mapi {
  public static Projector createProjector(Graph... graphs) {
    return createProjector(Arrays.asList(graphs));
  }

  public static Projector createProjector(Iterable<? extends Graph> graphs) {
    return new ProjectorBuilder()
        .withGraphs(graphs)
        .build();
  }

  private Mapi() {
  }
}
