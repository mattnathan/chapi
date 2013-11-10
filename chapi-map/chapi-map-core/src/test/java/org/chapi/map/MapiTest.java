package org.chapi.map;

import com.google.api.client.util.Lists;
import com.google.common.collect.ImmutableList;
import org.chapi.map.spi.ConstantValueMapping;
import org.chapi.map.spi.DefaultMappingSourceVisitor;
import org.chapi.map.spi.Mapping;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for different types of graph mappings.
 */
public class MapiTest {
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
  public void testMappingsExist() throws Exception {
    ConstantMappingGraph graph = new ConstantMappingGraph();
    Projector projector = Mapi.createProjector(graph);
    graph.verifyExpectedMappings(projector);

  }

  private static class ConstantMappingGraph extends AbstractGraph {

    public void verifyExpectedMappings(Projector projector) {
      List<ConstantValueMapping> constantMappings = collectConstantMappings(projector);
      assertEquals(1, constantMappings.size());
      ConstantValueMapping mapping = constantMappings.get(0);
      assertEquals("/constants/string", mapping.getSourcePath());
      assertEquals("CONSTANT_STRING", mapping.getValue());
    }

    @Override
    protected void configure() {
      map("/constants/string").toConstant("CONSTANT_STRING");
    }

    private List<ConstantValueMapping> collectConstantMappings(Projector projector) {
      List<ConstantValueMapping> constantMappings = Lists.newArrayList();
      ConstantMappingPredicate visitor = new ConstantMappingPredicate();
      for (Mapping mapping : projector.getAllMappings()) {
        ConstantValueMapping valueMapping = mapping.acceptSourceVisitor(visitor);
        if (valueMapping != null) {
          constantMappings.add(valueMapping);
        }
      }
      return constantMappings;
    }

    private static class ConstantMappingPredicate extends DefaultMappingSourceVisitor<ConstantValueMapping> {
      @Override
      public ConstantValueMapping visit(ConstantValueMapping constantValueMapping) {
        return constantValueMapping;
      }
    }
  }
}
