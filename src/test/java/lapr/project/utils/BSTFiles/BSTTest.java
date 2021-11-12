package lapr.project.utils.BSTFiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    BST newBst = new BST();
    List<Integer> testElements = new ArrayList<>();

    @BeforeEach
    void populateBST(){

       testElements.add(3);
       testElements.add(5);
       testElements.add(2);
       testElements.add(8);
       testElements.add(12);
       testElements.add(9);


       for(Integer elems : testElements){
           newBst.insert(elems);
       }

    }

    @Test
    void insert() {
        newBst.insert(43243);
        Iterable<Integer> list = newBst.preOrder();
        boolean flag = false;

        for(Integer elems : list){
            if(elems == 43243){
                flag = true;
            }
        }

        assertTrue(flag);
    }

    @Test
    void remove() {
        newBst.insert(9999);
        newBst.remove(9999);

        Iterable<Integer> list = newBst.preOrder();
        boolean flag = true;

        for(Integer elems : list){
            if(elems == 43243){
                flag = false;
            }
        }
        assertTrue(flag);

    }

    @Test
    void size() {
        assertEquals(newBst.size(), 6);
    }

    @Test
    void smallestElement() {
        assertEquals(newBst.smallestElement(), 2);
        assertNull(newBst.smallestElement(null));
    }

    @Test
    void findByRange() {
        List<Integer> list = new ArrayList<>();

        newBst.findByRange(3,12,list);

        assertEquals(list.size(), 5);
    }


    @Test
    void preOrder() {

        System.out.println(newBst.inOrder());
    }

    @Test
    void posOrder() {
        for(Integer elems : testElements){
            assertTrue(testElements.indexOf(elems) != -1);
        }
    }


    @Test
    void inOrder() {
        List<Integer> copy = testElements;
        Collections.sort(copy);

        assertEquals(newBst.inOrder(), copy);
    }


    @Test
    void nodesByLevel() {
        assertNotNull(newBst.nodesByLevel());
    }

    @Test
    void testToString() {
        String bst = newBst.toString();

        for(Integer elems : testElements){
            assertTrue(bst.contains(String.valueOf(elems)));
        }
    }

    @Test
    void isComplete() {
        assertFalse(newBst.isComplete());
    }
}