package org.chapi.map.internal;

import com.google.api.client.util.Lists;
import org.chapi.map.Mapper;
import org.chapi.map.mapper.DataOriginMappingBuilder;
import org.chapi.map.spi.Element;
import org.chapi.map.spi.Message;

import java.util.List;

/**
 * Mapper instance that records interactions
 */
public class RecordingMapper implements Mapper {

  private final List<Element> elements = Lists.newArrayList();

  @Override
  public DataOriginMappingBuilder map(String path) {
    return new MappingBuilder(this, elements, path);
  }

  @Override
  public void addError(String message, Object... args) {
    elements.add(new Message(Errors.format(message, args)));
  }

  List<Element> getElements() {
    return elements;
  }
}
