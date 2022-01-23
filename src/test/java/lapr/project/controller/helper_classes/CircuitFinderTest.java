package lapr.project.controller.helper_classes;

import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.data.graph_files.CircuitFinder;
import lapr.project.model.locals.Locals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CircuitFinderTest {
    private final AdjacencyMatrixGraph<Locals, Double> t = new AdjacencyMatrixGraph<>();
    private final AdjacencyMatrixGraph<Locals, Double> t1 = new AdjacencyMatrixGraph<>();

    public CircuitFinderTest() {

        t.insertVertex(new Locals("Portugal", 123, "p1", "xyz"));
        t.insertVertex(new Locals("Alemanha", 125, "p2", "xyz"));

    }

    @Test
    void getGraph() {
        CircuitFinder circuitFinder = new CircuitFinder();
        assertEquals(0, circuitFinder.getGraph().getVertices().size());

        circuitFinder.convertGraph(t);
        assertNotNull(circuitFinder.getGraph());
    }

    @Test
    void convertGraph() {
        CircuitFinder circuitFinder = new CircuitFinder();
        circuitFinder.convertGraph(t);
        assertNotNull(circuitFinder.getGraph());
    }

    @Test
    void mostEfficientCircuitWithMap() throws IOException {
        CircuitFinder circuitFinder = new CircuitFinder();
        AdjacencyMatrixGraph<String, Double> result = circuitFinder.convertGraph(t1);

        assertEquals(circuitFinder.mostEfficientCircuitWithMap(result, ""), new ArrayList<>());
    }

    @Test
    void saveAllCircuits() {
    }
}