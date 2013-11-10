package org.chapi.map.internal;

import com.google.common.collect.ImmutableList;
import org.chapi.map.Graph;
import org.chapi.map.spi.Element;

/**
 * Created by Matt on 10/11/13.
 */
public class Elements {
  public static ImmutableList<Element> collectElements(Iterable<? extends Graph> graphs) {
    RecordingMapper mapper = new RecordingMapper();
    for (Graph graph : graphs) {
      graph.configure(mapper);
    }
    return ImmutableList.copyOf(mapper.getElements());
  }
}
