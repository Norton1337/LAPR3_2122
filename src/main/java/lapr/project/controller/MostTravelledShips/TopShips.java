package lapr.project.controller.MostTravelledShips;

import java.util.ArrayList;
import java.util.List;

import lapr.project.model.Ships.Ship;

public class TopShips {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();

    

    public TopShips(List<Ship> shipLists, List<Double> distancesList, List<Double> SOGList){
        this.listOfShips = shipLists;
        this.listOfDistances = distancesList;
        this.listOfSOG = SOGList;
    }

    public String listOfShipToString(){
        return listOfShips.toString();
    }

    public String listOfDistancesToString(){
        return listOfDistances.toString();
    }

    public String listOfSOGToString(){
        return listOfSOG.toString();
    }
    
    public List<Ship> getListOfShip() {
        return this.listOfShips;
    }

    public void setListOfShips(List<Ship> listOfShip) {
        this.listOfShips = listOfShip;
    }

    public List<Double> getListOfDistances() {
        return this.listOfDistances;
    }

    public void setListOfDistances(List<Double> listOfDistances) {
        this.listOfDistances = listOfDistances;
    }

    public List<Double> getListOfSOG() {
        return this.listOfSOG;
    }

    public void setListOfSOG(List<Double> listOfSOG) {
        this.listOfSOG = listOfSOG;
    }

}
