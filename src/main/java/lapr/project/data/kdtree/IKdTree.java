package lapr.project.data.kdtree;

import java.util.List;
import java.util.Map;

public interface IKdTree<T> {


    boolean isEmpty();

    void insert(T element, double x, double y);

    void remove(T element);

    int size();

    int height();

    T smallestElement();

    Map<Integer, List<T>> nodesByLevel();

}
