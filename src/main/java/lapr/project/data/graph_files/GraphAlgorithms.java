package lapr.project.data.graph_files;

import java.util.LinkedList;
import java.util.List;

public class GraphAlgorithms {

    /**
     * Performs depth-first search of the graph starting at vertex.Calls package
     * recursive version of the method.
     *
     * @param <V>
     * @param <E>
     * @param graph  Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if
     * vertex does not exist
     */
    public static <V, E> LinkedList<V> BFS(AdjacencyMatrixGraph<V, E> graph, V vertex) {
        int index = graph.toIndex(vertex);
        if (index == -1) {
            return null;
        }
        LinkedList<V> resultQueue = new LinkedList();
        LinkedList<Integer> auxQueue = new LinkedList();
        resultQueue.add(graph.vertices.get(index));
        auxQueue.add(index);
        while (!auxQueue.isEmpty()) {
            index = auxQueue.remove();
            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.edgeMatrix[index][i] != null) {
                    if (!resultQueue.contains(graph.vertices.get(i))) {
                        resultQueue.add(graph.vertices.get(i));
                        auxQueue.add(i);
                    }
                }
            }
        }
        return resultQueue;
    }

    /**
     * Performs depth-first search of the graph starting at vertex.Calls package
     * recursive version of the method.
     *
     * @param <V>
     * @param <E>
     * @param graph  Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (empty if none), null if vertex
     * does not exist
     */
    public static <V, E> LinkedList<V> DFS(AdjacencyMatrixGraph<V, E> graph, V vertex) {
        int index = graph.toIndex(vertex);
        if (index == -1) {
            return null;
        }
        LinkedList<V> qdfs = new LinkedList<>();
        boolean[] knownVertices = new boolean[graph.numVertices];
        DFS(graph, index, knownVertices, qdfs);
        return qdfs;
    }

    /**
     * Actual depth-first search of the graph starting at vertex. The method
     * adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param graph         Graph object
     * @param index         Index of vertex of graph that will be the source of the
     *                      search
     * @param verticesQueue queue of vertices found by search
     */
    static <V, E> void DFS(AdjacencyMatrixGraph<V, E> graph, int index, boolean[] knownVertices, LinkedList<V> verticesQueue) {
        V vOrig = graph.vertices.get(index);
        knownVertices[index] = true;  //visitado
        verticesQueue.add(vOrig);
        for (int i = 0; i < graph.numVertices; i++) {
            if (graph.edgeMatrix[index][i] != null && !knownVertices[i]) {
                DFS(graph, i, knownVertices, verticesQueue);
            }
        }
    }

    /**
     * Transforms a graph into its transitive closure uses the Floyd-Warshall
     * algorithm
     *
     * @param <V>
     * @param <E>
     * @param graph     Graph object
     * @param dummyEdge object to insert in the newly created edges
     * @return the new graph
     */
    public static <V, E> AdjacencyMatrixGraph<V, E> transitiveClosure(AdjacencyMatrixGraph<V, E> graph, E dummyEdge) {
        AdjacencyMatrixGraph<V, E> transitiveClosureGraph;
        transitiveClosureGraph = (AdjacencyMatrixGraph<V, E>) graph.clone();
        for (int k = 0; k < graph.numVertices; k++) {
            for (int i = 0; i < graph.numVertices; i++) {
                if (i != k && transitiveClosureGraph.edgeMatrix[i][k] != null) {
                    for (int j = 0; j < graph.numVertices; j++) {
                        if (i != j && k != j && transitiveClosureGraph.edgeMatrix[k][j] != null) {
                            if (transitiveClosureGraph.edgeMatrix[i][j] == null) {
                                transitiveClosureGraph.insertEdge(i, j, dummyEdge);
                            }
                        }
                    }
                }
            }
        }
        return transitiveClosureGraph;
    }


    static <V, E> void circuit(AdjacencyMatrixGraph<V, E> graph, V vertex, List<V> visited, List<V> verticesQueue, V orig) {
        int index = graph.getIntNum(vertex);

        if(!vertex.equals(orig)){
            visited.add(vertex);
            verticesQueue.add(vertex);
        }


        for(V elems : graph.outgoingVertices(vertex)){
            if(!visited.contains(elems) && !visited.contains(orig)){
                circuit(graph, elems, visited, verticesQueue, orig);
            }
        }
    }

    public static  <V, E> List<V> circuit(AdjacencyMatrixGraph<V, E> graph, V vertex){
        List<V> visited = new LinkedList<>();
        List<V> verticesQueue = new LinkedList<>();

        circuit(graph, vertex, visited, verticesQueue, vertex);

        System.out.println(verticesQueue);
        return verticesQueue;
    }
}
