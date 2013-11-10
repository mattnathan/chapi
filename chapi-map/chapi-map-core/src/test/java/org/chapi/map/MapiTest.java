package org.chapi.map;

import com.google.api.client.util.Lists;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.chapi.map.spi.ConstantValueMapping;
import org.chapi.map.spi.DefaultMappingSourceVisitor;
import org.chapi.map.spi.Mapping;
import org.chapi.map.spi.Message;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.uniqueIndex;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for different types of graph mappings.
 */
public class MapiTest {

  private static final Function<Mapping, String> SOURCE_PATH_FUNCTION = new Function<Mapping, String>() {
    @Override
    public String apply(Mapping input) {
      return input.getSourcePath();
    }
  };

  @Test
  public void testNoGraphsGivesNoMappings() throws Exception {
    Projector projector = Mapi.createProjector();
    assertEquals(ImmutableList.<Mapping>of(), projector.getAllMappings());
  }

  @Test
  public void testEmptyGraphsGivesNoMappings() throws Exception {
    Projector projector = Mapi.createProjector(new AbstractGraph() {
      @Override
      protected void configure() {
        // no mappings here
      }
    });
    assertEquals(ImmutableList.<Mapping>of(), projector.getAllMappings());
  }

  @Test
  public void testExceptionWhenNoSource() throws Exception {
    try {
      Mapi.createProjector(new AbstractGraph() {
        @Override
        protected void configure() {
          map("/without/source");
        }
      });
      fail("Expecting CreationException");
    } catch (CreationException e) {
      ImmutableList<Message> messages = e.getMessages();
      assertEquals("No source defined for target path /without/source." +
                   " Did you forget to map to something? Consider toConstant or toEndpoint",
                   messages.get(0).getMessage());
    }
  }

  @Test
  public void testMappingsExist() throws Exception {
    ConstantMappingGraph graph = new ConstantMappingGraph();
    Projector projector = Mapi.createProjector(graph);
    graph.verifyExpectedMappings(projector);
  }

  private static class ConstantMappingGraph extends AbstractGraph {
    private final Map<String, Expected> expectedMappings = newHashMap();

    public void verifyExpectedMappings(Projector projector) {
      List<ConstantValueMapping> constantMappings = collectConstantMappings(projector);
      ImmutableMap<String, ConstantValueMapping> map = uniqueIndex(constantMappings, SOURCE_PATH_FUNCTION);

      assertEquals(expectedMappings.keySet(), map.keySet());
      for (Map.Entry<String, ConstantValueMapping> entry : map.entrySet()) {
        expectedMappings.get(entry.getKey()).validate(entry.getValue());
      }
    }

    @Override
    protected void configure() {
      map("/constants/string").toConstant("CONSTANT_STRING");
      expectedMappings.put("/constants/string", new Expected("CONSTANT_STRING"));
      map("/constants/string2").toConstant("CONSTANT_STRING2");
      expectedMappings.put("/constants/string2", new Expected("CONSTANT_STRING2"));
    }

    private List<ConstantValueMapping> collectConstantMappings(Projector projector) {
      List<ConstantValueMapping> constantMappings = Lists.newArrayList();
      ConstantMappingExtractor visitor = new ConstantMappingExtractor();
      for (Mapping mapping : projector.getAllMappings()) {
        ConstantValueMapping valueMapping = mapping.acceptSourceVisitor(visitor);
        if (valueMapping != null) {
          constantMappings.add(valueMapping);
        }
      }
      return constantMappings;
    }

    private static class Expected {
      private final String value;

      private Expected(String value) {
        this.value = value;
      }

      public void validate(ConstantValueMapping mapping) {
        assertEquals(value, mapping.getValue());
      }
    }

    private static class ConstantMappingExtractor extends DefaultMappingSourceVisitor<ConstantValueMapping> {
      @Override
      public ConstantValueMapping visit(ConstantValueMapping constantValueMapping) {
        return constantValueMapping;
      }
    }
  }
}
