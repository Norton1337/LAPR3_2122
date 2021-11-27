package lapr.project.controller;

import lapr.project.data.KDTree.KdTree;
import lapr.project.data.KDTree.Node;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Utils.convertCoordinates;

public class DataToKDTreeController {

    private KdTree<PortsAndWarehouses> portsTree;
    private List<Node<PortsAndWarehouses>> portsNodes;
    private List<PortsAndWarehouses> portsData;

    public DataToKDTreeController() {
        this.portsTree = new KdTree<>();
        this.portsNodes = new ArrayList<>();
        this.portsData = new ArrayList<>();
    }


    public void transformBeforeTree(List<PortsAndWarehouses> ports){

        for (PortsAndWarehouses elems: ports){
            String[] coordinates = convertCoordinates(elems.getCoordinates());
            double x = Double.parseDouble(coordinates[0]);
            double y = Double.parseDouble(coordinates[1]);

            portsNodes.add(new Node<>(elems,x, y));
        }
    }
    public void populateTree(List<PortsAndWarehouses> ports){

        transformBeforeTree(ports);
        portsTree.buildBalancedTree(portsNodes);
    }

    public KdTree<PortsAndWarehouses> getPortsTree() {
        return portsTree;
    }

    public List<Node<PortsAndWarehouses>> getPortsNodes() {
        return portsNodes;
    }
}
