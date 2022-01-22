package lapr.project.controller.helper_classes;

import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.data.graph_files.EdgeAsDoubleGraphAlgorithms;
import lapr.project.model.locals.Locals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.iterateToList;

public class CircuitFinder {

    private final List<ArrayList<String>> circuits = new ArrayList<>();
    private final AdjacencyMatrixGraph<String, Double> graph = new AdjacencyMatrixGraph<>();

    public AdjacencyMatrixGraph<String, Double> getGraph() {
        return graph;
    }

    public AdjacencyMatrixGraph<String, Double> convertGraph(AdjacencyMatrixGraph<Locals,Double> graphLocals){
        /**
         * Insert all vertex
         */
        for(Locals elems : graphLocals.getVertices()){
            this.graph.insertVertex(elems.getName());
        }


        for(Locals elems : graphLocals.getVertices()){
            for(Locals edges : graphLocals.outgoingVertices(elems)){
                double weight = graphLocals.getEdge(elems, edges);
                this.graph.insertEdge(elems.getName(), edges.getName(), weight);
            }
        }

        return graph;
    }

    public ArrayList<String> mostEfficientCircuitWithMap(AdjacencyMatrixGraph<String,Double> g , String s) throws IOException {
        saveAllCircuits(g,s);
        ArrayList<String> min = new ArrayList<>();
        int minWeight=Integer.MAX_VALUE;


        circuits.sort((a1, a2) -> a2.size() - a1.size());
        for (int i = 1; i < circuits.size(); i++) {
            if (circuits.get(i).size()<circuits.get(0).size()){
                circuits.remove(i);
                i--;
            }
        }
        for (ArrayList<String> l:circuits) {
            int weight =0;
            for (int i = 0; i < l.size()-1; i++) {
                LinkedList<String> path = new LinkedList<>();
                weight += EdgeAsDoubleGraphAlgorithms.shortestPath(g, l.get(i), l.get(i + 1),path);
            }
            if (weight<minWeight){
                minWeight=weight;
                min=l;
            }
        }
        return min;
    }
    public void saveAllCircuits(AdjacencyMatrixGraph<String,Double> g, String s) throws IOException {

        boolean[] isVisited = new boolean[g.numVertices()];
        List<String> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);

        // Call recursive utility
        int c = 0;
        saveAllCircuitsUtil(g, s, s, isVisited, pathList, c);

        
        circuits.add(new ArrayList<>(iterateToList(g.outgoingVertices(s))));


    }
    private void saveAllCircuitsUtil(AdjacencyMatrixGraph<String,Double> g, String u, String d,
                                     boolean[] isVisited,
                                     List<String> localPathList, int c) throws IOException {

        if (u.equals(d) && c > 0) {
            save(localPathList);
            // if match found then no need to traverse more till depth
            return;
        }
        c++;

        // Mark the current node
        isVisited[g.getIntNum(u)] = true;


        // Recur for all the vertices
        // adjacent to current vertex
        for (String i : g.outgoingVertices(u)) {
            if (!isVisited[g.getIntNum(u)] || i.equals(d)) {
                // store current node
                // in path[]
                localPathList.add(i);


                saveAllCircuitsUtil(g, String.valueOf(i), d, isVisited, localPathList, c++);

                // remove current node
                // in path[]
                localPathList.remove(localPathList.size() - 1);
            }
        }

        // Mark the current node
        isVisited[g.getIntNum(u)] = false;

    }
    private void save(List<String> localPathList) {
        for (String s : localPathList) {
            //System.out.printf("%s, ", s);
        }
        //System.out.println("");
    }
}
