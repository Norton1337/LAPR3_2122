package lapr.project.data.kdtree;

import java.awt.geom.Point2D;

public class Node <T> {

    private Point2D.Double coordinates; // representar pela class point2d, representa ponto de duas dimensoes atraves das coordenadas x, y
    private T element;
    private Node<T> left;
    private Node<T> right;


    public Node(T element, double x, double y){
        this.coordinates = new Point2D.Double(x, y);
        this.element = element;
    }


    public Double getX(){
        return coordinates.getX();
    }

    public Double getY(){
        return coordinates.getY();
    }

    public Point2D.Double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D.Double coordinates) {
        this.coordinates = coordinates;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }



    @Override
    public String toString() {
        return "Node{" + element +
                " - Coordinates=" + coordinates.getX()+", "+coordinates.getY() +
                //", Left=" + left +
                //", Right=" + right +
                '}';

    }
}
