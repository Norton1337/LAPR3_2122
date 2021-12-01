package lapr.project.data.kdtree;//Tree;


import java.awt.geom.Point2D;
import java.util.*;

public class KdTree <T extends Comparable<T>>  {


    protected Node<T> root; // root of the tree

    public KdTree(){
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<T> root(){
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }



    private final Comparator<Node<T>> compX = new Comparator<Node<T>>() {
        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getCoordinates().getX(), p2.getCoordinates().getX());
        }
    };

    private final Comparator<Node<T>> compY = new Comparator<Node<T>>() {
        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

    // TO COMPARE COORDINATES
    private final Comparator<Point2D> coordX = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D c1, Point2D c2) {
            return Double.compare(c1.getX(), c2.getX());
        }
    };

    private final Comparator<Point2D> coordY = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D c1, Point2D c2) {
            return Double.compare(c1.getY(), c2.getY());
        }
    };




    public KdTree(List<Node<T>> nodes) {
        buildBalancedTree(nodes);
    }


    public void buildBalancedTree(List<Node<T>> nodes) {
        root = new Object() {
            Node<T> buildTree(boolean divX, List<Node<T>> nodes) {
                if (nodes == null || nodes.isEmpty())
                    return null;

                Collections.sort(nodes, divX ? compX : compY);
                int mid = ((nodes.size()-1) >> 1) ; //(nodes.size() >> 1) (PREVIOUS)


                Node<T> node = new Node<>(nodes.get(mid).getElement(),nodes.get(mid).getX(), nodes.get(mid).getY());


                node.setLeft(buildTree(!divX, nodes.subList(0, mid)));

                if (mid + 1 < nodes.size() ) // if (mid + 1 < nodes.size() - 1) (PREVIOUS)
                    node.setRight( buildTree(!divX, nodes.subList(mid +1, nodes.size())));


                return node;


            }
        }.buildTree(true, nodes);
    }



    /*
     * Inserts an element in the tree.
     */
    public Node<T> insert(T element, double x, double y) {

        Node<T> node = new Node<>(element, x, y);

        if (root == null){
            root = node;
        }else {
            insert(root, node, true);
        }
        return node;
    }



    private void insert(Node<T> currentNode, Node<T> node, boolean divX){

        if (node == null){
            return;
        }

        if (node.getCoordinates().equals(currentNode.getCoordinates())){
            return;
        }

        int compResult = (divX ? compX : compY).compare(node, currentNode);

        if (compResult == -1){

            if (currentNode.getLeft() == null){
                currentNode.setLeft(node);
            }
            else {
                insert(currentNode.getLeft(), node, !divX);
            }
        }
        else {

            if (currentNode.getRight() == null){
                currentNode.setRight(node);
            }
            else {
                insert(currentNode.getRight(), node, !divX);
            }
        }

    }


    public int size(){

        return new Object() {
            int cnt = 0;
            int getSize(Node<T> node) {
                if (node == null)
                    return cnt - 1;
                if (node.getLeft() != null) {
                    cnt++;
                    getSize(node.getLeft());
                }
                if (node.getRight() != null) {
                    cnt++;
                    getSize(node.getRight());
                }
                return cnt;
            }
        }.getSize(root) + 1;

    }



    public List<T> getAllElements() {

        return getAllElements(root);
    }

    private List<T> getAllElements(Node<T> root){

        final List<T> list = new LinkedList<>();

        new Object() {
            void getElements(Node<T> node) {
                if(node == null){
                    return;
                }
                list.add(node.getElement());
                if(node.getLeft() != null)
                    getElements(node.getLeft());
                if(node.getRight() != null)
                    getElements(node.getRight());
            }
        }.getElements(root);
        return list;
    }



    // Find element through coordinates
    public Node<T> find(Point2D coordinates){
        return find(root, coordinates, true);
    }

    private Node<T> find(Node<T> node, Point2D coordinates, boolean divX){

        if (node == null){
            return null;
        }
        if (node.getCoordinates().equals(coordinates)){
            return node;
        }


        int compCoords = (divX ? coordX : coordY).compare(node.getCoordinates(), coordinates);

        if (compCoords >0){
            return find(node.getLeft(), coordinates, !divX);
        }

        return find(node.getRight(), coordinates,!divX);
    }



    private Node<T> findMin(Node<T> node, boolean divX) {
        if (node == null)
            return null;
        if (divX) {
            if (node.getLeft() == null)
                return node;
            else
                return findMin(node.getLeft(), false);
        }
        else {
            List<Node<T>> list = new LinkedList<>();
            list.add(findMin(node, true));

            if(node.getLeft() != null)
                list.add(findMin(node.getLeft(), true));

            if(node.getRight() != null)
                list.add(findMin(node.getRight(), true));

            Collections.sort(list, compY);

            return list.get(0);

        }
    }


    public Node<T> delete(final T element) {
        root = new Object() {
            Node<T> delete(T elem, Node<T> node, boolean divX) {
                if(node == null)
                    return null;
                if (elem.equals(node.getElement())) {
                    if(node.getRight() != null) {
                        Node<T> minNode = findMin(node.getRight(), !divX);
                        node.setElement( minNode.getElement());
                        node.setCoordinates( minNode.getCoordinates());
                        node.setRight(delete(node.getElement(), node.getRight(), !divX));
                    }
                    else if (node.getLeft() != null) {
                        Node<T> minNode = findMin(node.getLeft(), !divX);
                        node.setElement( minNode.getElement());
                        node.setCoordinates( minNode.getCoordinates());
                        node.setLeft(delete(node.getElement(), node.getLeft(), !divX));
                    }
                    else
                        node = null;
                }
                else {
                    node.setLeft(delete(elem, node.getLeft(), !divX));
                    node.setRight(delete(elem, node.getRight(), !divX));
                }
                return node;
            }
        }.delete(element, root, true);

        return root;
    }



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
    protected int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        int nl = height(node.getLeft());
        int nr = height(node.getRight());
        return 1 + Math.max(nl, nr);
    }




    public String print(){
        return print(root);
    }

    private String print(Node<T> node){
        if (node == null){
            return "";
        }
        if (node.getRight() == null && node.getLeft() == null){
            return "";
        }

        System.out.println("Node: " + node.getElement()+ " [" + node.getX()+" ,"+ node.getY() +"]"+
                "\nLeft: "+node.getLeft() + "\nRight: "+node.getRight());
        System.out.println("\n");

        print(node.getLeft());
        print(node.getRight());
        return "";
    }



    public int balanceFactor(){
        return balanceFactor(root);
    }

    private int balanceFactor(Node<T> node) {
        if (node == null){
            return 0;
        }

        int val = height(node.getRight()) - height(node.getLeft());

        if (val< -1 || val > 1){
            return 0;
        }
        balanceFactor(node.getRight());
        balanceFactor(node.getLeft());
        System.out.println(" [" + node.getX()+" ,"+ node.getY() +"]" + ": Balance Factor: " + (height(node.getRight()) - height(node.getLeft())));
        return 0;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<T> root, int level, StringBuilder sb) {
        if (root == null) {
            return;
        }
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                sb.append("|\t");
            }
            sb.append("|-------" + root.getCoordinates() + "\n");
        } else {
            sb.append(root.getCoordinates() + "\n");
        }
        toStringRec(root.getLeft(), level + 1, sb);
    }


/*
    @Override
    public String toString() {
        return "KdTree{" +
                "root=" + root +
                '}';
    }

 */

}


