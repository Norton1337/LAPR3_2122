package lapr.project.controller.HelperClasses;

import java.util.ArrayList;
import java.util.List;

import lapr.project.model.Ships.Ship;

/**
 * @author Paulo Norton
 */
public class TopShips {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();

    
    /**
     * Constructor that recieves 3 lists and saves them.
     * @param shipLists List of ships.
     * @param distancesList List of distances.
     * @param SOGList List of average speeds.
     */
    public TopShips(List<Ship> shipLists, List<Double> distancesList, List<Double> SOGList){
        this.listOfShips = shipLists;
        this.listOfDistances = distancesList;
        this.listOfSOG = SOGList;
    }
    
    /**
     * Returns a list
     * @return the list of ships
     */
    public List<Ship> getListOfShip() {
        return this.listOfShips;
    }

    /**
     * Returns a list
     * @return the list of distances
     */
    public List<Double> getListOfDistances() {
        return this.listOfDistances;
    }


    /**
     * Returns a list
     * @return the list of average speeds
     */
    public List<Double> getListOfSOG() {
        return this.listOfSOG;
    }


}
