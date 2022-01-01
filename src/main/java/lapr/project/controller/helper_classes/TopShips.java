package lapr.project.controller.helper_classes;

import lapr.project.model.ships.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Norton
 */
public class TopShips {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();


    /**
     * Constructor that recieves 3 lists and saves them.
     *
     * @param shipLists     List of ships.
     * @param distancesList List of distances.
     * @param SOGList       List of average speeds.
     */
    public TopShips(List<Ship> shipLists, List<Double> distancesList, List<Double> sogList) {
        setListOfShip(shipLists);
        setListOfDistances(distancesList);
        setListOfSOG(sogList);
    }

    /**
     * Returns a list
     *
     * @return the list of ships
     */
    public List<Ship> getListOfShip() {
        return this.listOfShips;
    }

    /**
     * Set list of ships
     *
     * @param shipLists new list of ships
     */
    private void setListOfShip(List<Ship> shipLists) {
        this.listOfShips = shipLists;
    }

    /**
     * Returns a list
     *
     * @return the list of distances
     */
    public List<Double> getListOfDistances() {
        return this.listOfDistances;
    }

    /**
     * Set list of distances
     *
     * @param distancesList new list of distances
     */
    private void setListOfDistances(List<Double> distancesList) {
        this.listOfDistances = distancesList;
    }

    /**
     * Returns a list
     *
     * @return the list of average speeds
     */
    public List<Double> getListOfSOG() {
        return this.listOfSOG;
    }

    /**
     * Set list of average speeds
     *
     * @param sogList new list of average speeds
     */
    private void setListOfSOG(List<Double> sogList) {
        this.listOfSOG = sogList;
    }
}
