package lapr.project.data.KDTree;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KdTreeTest {

    KdTree kdTree = new KdTree();
    Node node = new Node("elem", 62.87, -21.5);



    @Test
    public void buildBalancedTreeTest(){

    }

    @Test
    public void insertTest(){
        int size = kdTree.size();
        kdTree.insert("elem", 62.87, -21.5);
        assertTrue(kdTree.size() == size+1);
    }

    @Test
    public void sizeTest(){
        //System.out.println("Initial size " + kdTree.size());
        kdTree.insert("elem", -85.14, -24.8);
        kdTree.insert("elem2", 78.2, 21.4);
        assertEquals(2, kdTree.size());
    }
    @Test
    public void getAllElementsTest(){

        int count = 0;
        kdTree.insert("elem", 54.4, 87.1);
        kdTree.insert("elem2", 63.7, -41.2);
        kdTree.insert("elem3", -36.8, 27.9);

        for (Object elems : kdTree.getAllElements()){
            count++;
            System.out.println(elems);
        }

        assertEquals(3, count);

    }

    @Test
    public void findTest(){

        kdTree.insert("elem", -58.14, 70.2);
        Point2D.Double coordinates = new Point2D.Double(-58.14, 70.2);

        boolean findElem = kdTree.find(coordinates);

        assertEquals(true, findElem);

    }

}