package lapr.project.controller;

import lapr.project.data.kdtree.KdTree;
import lapr.project.data.kdtree.Node;
import lapr.project.model.locals.Locals;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Utils.convertCoordinates;

public class DataToKDTreeController {

    private KdTree<Locals> portsTree;
    private List<Node<Locals>> portsNodes;

    public DataToKDTreeController() {
        this.portsTree = new KdTree<>();
        this.portsNodes = new ArrayList<>();
    }


    public void transformBeforeTree(List<Locals> ports) {

        for (Locals elems : ports) {
            String[] coordinates = convertCoordinates(elems.getCoordinates());
            double x = Double.parseDouble(coordinates[0]);
            double y = Double.parseDouble(coordinates[1]);

            portsNodes.add(new Node<>(elems, x, y));
        }
    }

    public void populateTree(List<Locals> ports) {

        transformBeforeTree(ports);
        portsTree.buildBalancedTree(portsNodes);
    }

    public KdTree<Locals> getPortsTree() {
        return portsTree;
    }

    public List<Node<Locals>> getPortsNodes() {
        return portsNodes;
    }
}
