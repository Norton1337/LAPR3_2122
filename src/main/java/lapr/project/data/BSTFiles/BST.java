package lapr.project.data.BSTFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BST<E extends Comparable<E>> implements BSTInterface<E> {

    /**
     * Nested static class for a binary search tree node.
     */
    public static class Node<E> {

        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e the element to be stored
         * @param leftChild reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public E getElement() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(E e) {
            element = e;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    //----------- end of nested Node class -----------
    protected Node<E> root = null;     // root of the tree

    /* Constructs an empty binary search tree. */
    public BST() {
        root = null;
    }

    /*
    * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
    }

    /*
    * Verifies if the tree is empty
    * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /*
    * Inserts an element in the tree.
     */
    @Override
    public void insert(E element) {
        root = insert(element, root());
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(element, node.getLeft()));
        }
        if (element.compareTo(node.getElement()) > 0) {
            node.setRight(insert(element, node.getRight()));
        }
        return node;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary
     * Search Tree.
     *
     * @param element
     */
    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
        } else {
            node.setRight(remove(element, node.getRight()));
        }
        return node;
    }

    /*
    * Returns the number of nodes in the tree.
    * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node<E> node) {
        Map<Integer, List<E>> levels = new HashMap<>();
        ArrayList<E> allValuesSub = new ArrayList<>();
        if (root != null) {
            processBstByLevel(root, levels, 0);
        }
        for (Integer elem : levels.keySet()) {
            for (E values : levels.get(elem)) {
                allValuesSub.add(values);
            }
        }
        return allValuesSub.size();
    }

    /*
    * Returns the height of the tree
    * @return height 
     */
    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return height(root);
    }

    /*
    * Returns the height of the subtree rooted at Node node.
    * @param node A valid Node within the tree
    * @return height 
     */
    protected int height(Node<E> node) {
        if (node == null) {
            return -1;
        }
        int nl = height(node.getLeft());
        int nr = height(node.getRight());
        return 1 + Math.max(nl, nr);
    }

    /**
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    @Override
    public E smallestElement() {
        return smallestElement(root);
    }

    protected E smallestElement(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> nodeTemp = node;
        while (nodeTemp.getLeft() != null) {
            nodeTemp = nodeTemp.getLeft();
        }
        return nodeTemp.getElement();
    }

    public E biggestElement() {
        return biggestElement(root);
    }

    protected E biggestElement(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> nodeTemp = node;
        while (nodeTemp.getRight() != null) {
            nodeTemp = nodeTemp.getRight();
        }
        return nodeTemp.getElement();
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     *
     * This method despite not being essential is very useful. It is written
     * here in order to be used by this class and its subclasses avoiding
     * recoding. So its access level is protected
     */
    public Node<E> find(E element) {
        return find(root(), element);
    }

    public void findByRange(E min, E max, List<Node<E>> list) {
        findByRange(list, root(), min, max);
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param node
     * @param element
     * @pa
     * @return the Node that contains the Element, or null otherwise
     *
     * This method despite not being essential is very useful. It is written
     * here in order to be used by this class and its subclasses avoiding
     * recoding. So its access level is protected
     */
    protected Node<E> find(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) {
            return node;
        }
        if (element.compareTo(node.getElement()) < 0) {
            return find(node.getLeft(), element);
        }
        return find(node.getRight(), element);
    }

    protected void findByRange(List<Node<E>> list, Node<E> node, E min, E max) {
        if (node == null) {
            return;
        }
        if (min.compareTo(node.getElement()) <= 0 && max.compareTo(node.getElement()) >= 0) {
            list.add(node);
        }
        if (min.compareTo(node.getElement()) < 0) {
            findByRange(list, node.getLeft(), min, max);
        }
        if (max.compareTo(node.getElement()) > 0) {
            findByRange(list, node.getRight(), min, max);
        }
    }


    /*
   * Returns an iterable collection of elements of the tree, reported in in-order.
   * @return iterable collection of the tree's elements reported in in-order
     */
    @Override
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an in-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    @Override
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an pre-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    @Override
    public Iterable<E> posOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            posOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given snapshot
     * using an post-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void posOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        posOrderSubtree(node.getLeft(), snapshot);
        posOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
    }

    /*
    * Returns a map with a list of nodes by each tree level.
    * @return a map with a list of nodes by each tree level
     */
    @Override
    public Map<Integer, List<E>> nodesByLevel() {
        Map<Integer, List<E>> levels = new HashMap<>();
        if (root != null) {
            processBstByLevel(root, levels, 0);
        }
        return levels;
    }

    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        if (node == null) {
            return;
        }
        List<E> list = result.get(level);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(node.getElement());
        result.put(level, list);
        processBstByLevel(node.getLeft(), result, level + 1);
        processBstByLevel(node.getRight(), result, level + 1);
    }

//#########################################################################
    /**
     * Returns a string representation of the tree. Draw the tree horizontally
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null) {
            return;
        }
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                sb.append("|\t");
            }
            sb.append("|-------" + root.getElement() + "\n");
        } else {
            sb.append(root.getElement() + "\n");
        }
        toStringRec(root.getLeft(), level + 1, sb);
    }

    public boolean isComplete() {
        return isComplete(root, 0, size());
    }

    private boolean isComplete(Node root, int index, int number_nodes) {
        if (root == null) {
            return true;
        }
        if (index >= number_nodes) {
            return false;
        }
        return (isComplete(root.getLeft(), 2 * index + 1, number_nodes)
                && isComplete(root.getRight(), 2 * index + 1, number_nodes));
    }
} //----------- end of BST class -----------
