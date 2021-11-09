package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author DEI-ESINF
 * @param <E>
 */
public class TREE<E extends Comparable<E>> extends BST<E> {

    /*
   * @param element A valid element within the tree
   * @return true if the element exists in tree false otherwise
     */
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        Node<E> node = find(root, element);
        return node != null;
    }

    public boolean isLeaf(E element) {
        if (element == null) {
            return false;
        }
        Node<E> node = find(root, element);
        if (node == null) {
            return false;

        }
        return (node.getLeft() == null && node.getRight() == null);
    }

    /*
   * build a list with all elements of the tree. The elements in the 
   * left subtree in ascending order and the elements in the right subtree 
   * in descending order. 
   *
   * @return    returns a list with the elements of the left subtree 
   * in ascending order and the elements in the right subtree is descending order.
     */
    public Iterable<E> ascdes() {
        List<E> list = new ArrayList<>();
        if (root() != null) {
            ascSubtree(root.getLeft(), list);
            list.add(root().getElement());
            desSubtree(root.getRight(), list);
        }
        return list;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        ascSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getRight(), snapshot);
    }

    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        desSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
        desSubtree(node.getLeft(), snapshot);
    }

    /**
     * Returns the tree without leaves.
     *
     * @return tree without leaves
     */
    public BST<E> autumnTree() {
        TREE<E> autumn = new TREE<>();
        autumn.root = copyRec(root());
        return autumn;
    }

    private Node<E> copyRec(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        }
        return new Node(node.getElement(), copyRec(node.getLeft()), copyRec(node.getRight()));
    }

    /**
     * @return the the number of nodes by level.
     */
    public int[] numNodesByLevel() {
        int dim = height() + 1;
        int[] numNodes = new int[dim];

        numNodesByLevel(root(), numNodes, 0);
        return numNodes;
    }

    private void numNodesByLevel(Node<E> node, int[] result, int level) {
        if (node == null) {
            return;
        }
        result[level] += 1;
        numNodesByLevel(node.getLeft(), result, level + 1);
        numNodesByLevel(node.getRight(), result, level + 1);
    }

//    public boolean perfectBalanced() {
//        return perfectBalanced(root());
//    }
//
//    private boolean perfectBalanced(Node<E> node) {
//        
//    }
}
