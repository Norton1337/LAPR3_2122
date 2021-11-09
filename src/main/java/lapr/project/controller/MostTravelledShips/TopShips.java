package lapr.project.controller.MostTravelledShips;

import java.util.ArrayList;
import java.util.List;

import lapr.project.model.Ships.Ship;

public class TopShips {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();

    
    /**
     * 
     * @param shipLists
     * @param distancesList
     * @param SOGList
     */
    public TopShips(List<Ship> shipLists, List<Double> distancesList, List<Double> SOGList){
        this.listOfShips = shipLists;
        this.listOfDistances = distancesList;
        this.listOfSOG = SOGList;
    }
    
    /**
     * 
     * @return
     */
    public List<Ship> getListOfShip() {
        return this.listOfShips;
    }

    /**
     * 
     * @return
     */
    public List<Double> getListOfDistances() {
        return this.listOfDistances;
    }


    /**
     * 
     * @return
     */
    public List<Double> getListOfSOG() {
        return this.listOfSOG;
    }


}
