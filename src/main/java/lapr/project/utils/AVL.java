package lapr.project.utils;

public class AVL<E extends Comparable<E>> extends BST<E> {

    private int balanceFactor(BST.Node<E> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    private BST.Node<E> rightRotation(BST.Node<E> node) {
        BST.Node<E> leftSon = node.getLeft();
        node.setLeft(leftSon.getRight());
        leftSon.setRight(node);
        node = leftSon;
        return node;
    }

    private BST.Node<E> leftRotation(BST.Node<E> node) {
        BST.Node<E> rightSon = node.getRight();
        node.setRight(rightSon.getLeft());
        rightSon.setLeft(node);
        node = rightSon;
        return node;
    }

    private BST.Node<E> twoRotations(BST.Node<E> node) {
        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;
    }

    private BST.Node<E> balanceNode(BST.Node<E> node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rightRotation(node);
            } else {
                node = twoRotations(node);
            }
        } else if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = leftRotation(node);
            } else {
                node = twoRotations(node);
            }
        }
        return node;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private BST.Node<E> insert(E element, BST.Node<E> node) {
        if (node == null) {
            return new BST.Node(element, null, null);
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(element, node.getLeft()));
            node = balanceNode(node);
        }
        if (element.compareTo(node.getElement()) > 0) {
            node.setRight(insert(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private BST.Node<E> remove(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.equals(node.getElement())) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            E smallElem = smallestElement(node.getRight());
            node.setElement(smallElem);
            node.setRight(remove(smallElem, node.getRight()));
            node = balanceNode(node);
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }
        AVL<E> second = (AVL<E>) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(BST.Node<E> root1, BST.Node<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
