package lapr.project.data.KDTree;

import lapr.project.data.kdtree.KdTree;
import lapr.project.data.kdtree.Node;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KdTreeTest {

    KdTree kdTree = new KdTree();
    Node node = new Node("elem", 62.87, -21.5);

    @Test
    void isEmptyTest() {
        assertEquals(0, kdTree.getAllElements().size());
    }

    @Test
    void heightTest() {
        kdTree.insert("elem4", 10.6, 50.8);
        kdTree.insert("elem5", 11.6, -43.2);
        kdTree.insert("elem6", 12.6, 24.9);
        assertEquals(2, kdTree.height());
    }


    /*
    @Test
    void buildBalancedTreeTest() {
        List<Node> nodeList = new ArrayList<>();

        nodeList.add(new Node("elem", 54.4, 87.1));
        nodeList.add(new Node("elem2", 63.7, -41.2));
        nodeList.add(new Node("elem3", -36.8, 27.9));
        nodeList.add(new Node("elem4", 10.6, 21.8));
        nodeList.add(new Node("elem5", 11.6, 22.2));
        nodeList.add(new Node("elem6", 12.6, 24.9));
        nodeList.add(new Node("elem7", 13.6, 25.9));
        nodeList.add(new Node("elem8", 14.6, 26.9));
        kdTree = new KdTree(nodeList);
        assertEquals(3, kdTree.height());

    }

     */


    @Test
    void insertDeleteSizeTest() {
        kdTree.insert("elem", -85.14, -24.8);
        kdTree.insert("elem2", 78.2, 21.4);
        assertEquals(2, kdTree.size());
        kdTree.delete("elem");
        assertEquals(1, kdTree.size());
    }

    @Test
    void getAllElementsTest() {

        kdTree.insert("elem", 54.4, 87.1);
        kdTree.insert("elem2", 63.7, -41.2);
        kdTree.insert("elem3", -36.8, 27.9);

        assertEquals(3, kdTree.getAllElements().size());

    }

    @Test
    void findTest() {

        Node node = kdTree.insert("elem", -58.14, 70.2);

        Node findElem = kdTree.find(new Point2D.Double(-58.14, 70.2));

        assertEquals(findElem, node);

    }

}