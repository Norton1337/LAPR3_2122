package lapr.project.controller.helper_classes;

import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.data.graph_files.EdgeAsDoubleGraphAlgorithms;
import lapr.project.model.locals.Locals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.mergeLists;
import static lapr.project.utils.Utils.printList;

public class CircuitFinder2 {

    private final LocalsController localsController;

    public CircuitFinder2(LocalsController localsController) {
        this.localsController = localsController;
    }


    public void findBiggestCircuit(AdjacencyMatrixGraph<Locals, Double> graph, String orig){

        Locals origLocal = localsController.getLocalWithName(orig);

        int biggest = Integer.MIN_VALUE;
        List<Locals> currentGoing = new ArrayList<>();

        for(Locals dest : graph.getVertices()){
            AdjacencyMatrixGraph<Locals, Double> graphClone = graph.clone();
            /**
             * Para ir
             */
            LinkedList<Locals> going = new LinkedList<>();
            EdgeAsDoubleGraphAlgorithms.shortestPath(graphClone, origLocal, dest, going);
            int sizePathGoing = going.size();


            /**
             * Remocao das edges para nao se repetir
             */

            for (Locals locals : going) {
                graphClone.removeEdge(origLocal, locals);
                origLocal = locals;
            }


            /**
             * Para vir
             */
            int sizePathComming = 0;
            LinkedList<Locals> comming = new LinkedList<>();

            if(!going.isEmpty()){
                double weight = EdgeAsDoubleGraphAlgorithms.shortestPath(graphClone, going.get(sizePathGoing-1), origLocal, comming);
                if(weight > 0){
                    sizePathComming = comming.size();
                }

            }



            if(going.size() != 0 && comming.size()!= 0 && sizePathGoing + sizePathComming > biggest){
                biggest = sizePathGoing + sizePathComming;
                currentGoing = mergeLists(going, comming);

                System.out.println("Ao ir: " + going);
                System.out.println("Ao vir: " + comming);

                System.out.println("\n\n");
            }
        }



        printList(currentGoing);
    }
}
