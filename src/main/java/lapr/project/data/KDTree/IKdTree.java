package lapr.project.data.KDTree;

import java.util.List;
import java.util.Map;

public interface IKdTree<T> {


    public boolean isEmpty();
    public void insert(T element, double x, double y);
    public void remove(T element);

    public int size();
    public int height();

    public T smallestElement();
    public Map<Integer, List<T>> nodesByLevel();

}
