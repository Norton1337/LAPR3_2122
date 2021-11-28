package lapr.project.controller;

import lapr.project.controller.HelperClasses.KMTravelledCalculator;
import lapr.project.controller.HelperClasses.TopShips;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Utils.convertCoordinates;

/**
 * @author Paulo Norton
 */
public class MostTravelledShipsController {

    
    private final List<Ship> listOfShips = new ArrayList<>();
    private final List<Double> listOfDistances = new ArrayList<>();
    private final List<Double> listOfSOG = new ArrayList<>();
    private double totalDistances;
    private double totalSOG;
    private int amountSOG;
    private final TopShips topShips = new TopShips(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  
    /**
     * Constructor that sets the total distance and total average speed to -1
     */
    public MostTravelledShipsController(){

        this.totalDistances=-1;
        this.totalSOG=-1;
    }

    /**
     * Adds the total distance travelled by ships to a list of distances,
     * adds all ships to a list of ships, and adds the average SOG of a ship to a list.
     * These lists will then be ordered by distance travelled and then returned
     * @param shipList a list with all the ships and their position messages.
     * @param topN the amount of elements to return
     * @return 3 lists ordered by kilometers with topN amount of ships 
     */
    public TopShips getTopNShips(List <ShipAndData> shipList, int topN){
        TemporalPositionalMessagesController tpmc = new TemporalPositionalMessagesController();
        shipList = tpmc.getAllShipsAndData(shipList);

        while(listOfShips.size()!=shipList.size()){

            listOfDistances.add(getTotalPerShip(shipList.get(listOfShips.size()).getShipPositonData()));
            listOfShips.add(shipList.get(listOfShips.size()).getShip());
            listOfSOG.add(totalSOG/amountSOG);
            totalDistances=0;
            totalSOG=0;
            amountSOG=0;
        }

        return orderLists(new TopShips(listOfShips,listOfDistances,listOfSOG), 0, topN, 0, 0);
    }
    /**
     * This will create a new list with all the ship and all the positional messages that occured between 2 dates
     * @param shipList a list with all the ships and their position messages.
     * @param topN the amount of elements to return
     * @param initialDate the first date
     * @param finalDate the last date
     * @return the list with only positional messages that happened betwenn the dates
     * @throws ParseException in case the date format is not recognized
     */
    public TopShips getTopNShips(List <ShipAndData> shipList, int topN, String initialDate, String finalDate) throws ParseException{
        TemporalPositionalMessagesController tpm = new TemporalPositionalMessagesController();
        List <ShipAndData> newShipList = new ArrayList<>();
        
        for (int i = 0; i < shipList.size(); i++) {
            ShipAndData newSAD = new ShipAndData();

            newSAD.setShip(shipList.get(i).getShip());
            newSAD.setShipPositonData(tpm.getAllPositionalDataInPeriod(shipList.get(i), initialDate, finalDate));
            newShipList.add(newSAD);

        }

        return getTopNShips(newShipList, topN);
    }

    /**
     * This will create a list with ships whose vesselType is the same and their positional messages.
     * @param shipList a list with all the ships and their position messages.
     * @param topN the amount of elements to return
     * @param initialDate the first date
     * @param finalDate the last date
     * @param vesselType the type of a vessel
     * @return a new list with only vessels of the same type
     * @throws ParseException in case the date format is not recognized
     */
    public TopShips getTopNShips(List <ShipAndData> shipList, int topN, String initialDate, String finalDate, int vesselType) throws ParseException{
        List <ShipAndData> newShipList = new ArrayList<>();


        for (int i = 0; i < shipList.size(); i++) {
            if(shipList.get(i).getShip().getVesselType()==vesselType){
                newShipList.add(shipList.get(i));
            }
        }

        return getTopNShips(newShipList, topN, initialDate, finalDate);
    }

    /**
     * Orders all the lists by most kilometers travelled, all the information in a position
     * belongs to the same ship. Will then return the topN ships ordered.
     * @param ts a list with all the ships and their position messages.
     * @param position the current position in the list
     * @param topN the amount of elements to return
     * @param largestDistance the last largest number in the list to be read
     * @param lDPosition the position of the largest distance in the list
     * @return the topN ships orderes by distance travelled
     */
    public TopShips orderLists(TopShips ts, int position, int topN, double largestDistance, int lDPosition){
  
        if(topShips.getListOfShip().size()>=topN || topShips.getListOfShip().size()>=ts.getListOfShip().size()){

            return topShips;
        }

        if(ts.getListOfDistances().get(position)>largestDistance && !topShips.getListOfShip().contains(ts.getListOfShip().get(position))){
            
            largestDistance=ts.getListOfDistances().get(position);
            lDPosition=position;
        }


        if(position==ts.getListOfDistances().size()-1){
            

            List<Double> loDistances = topShips.getListOfDistances();
            loDistances.add(ts.getListOfDistances().get(lDPosition));
            

            List<Double> loSOG = topShips.getListOfSOG();
            loSOG.add(ts.getListOfSOG().get(lDPosition));

            List<Ship> loShips = topShips.getListOfShip();
            loShips.add(ts.getListOfShip().get(lDPosition));
            
            return orderLists(ts, 0, topN, -1, -1);
        }


        
        return orderLists(ts, position+1, topN, largestDistance, lDPosition);
    }

    /**
     * Adds all the distances travelled between each positional message and returns the total.
     * @param posList a list containing a set of position messages belonging to a single ship
     * @return the total amount of distance the ship travelled
     */
    public double getTotalPerShip(List<ShipPositonData> posList){
        
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        totalDistances=0;
        for (int i = 0; i < posList.size()-1; i++) {
            Double newNumber = calculator.calculate(posList.get(i).getCoordinates().split("/")[0], posList.get(i).getCoordinates().split("/")[1],
                                posList.get(i+1).getCoordinates().split("/")[0], posList.get(i+1).getCoordinates().split("/")[1]);
            
            if(newNumber.isNaN()){
                newNumber=0.0;
            }
            totalDistances += newNumber;

            
            totalSOG += posList.get(i).getSog();
            amountSOG++;
        }

        return totalDistances;
    }

    public double getDeltaDistance(List<ShipPositonData> list) {

        String[] coords = convertCoordinates(list.get(0).getCoordinates());
        String [] coordsFinal = convertCoordinates(list.get(list.size()-1).getCoordinates());

        KMTravelledCalculator convertDistance = new KMTravelledCalculator();

        return convertDistance.calculate(coordsFinal[0], coordsFinal[1], coords[0], coords[1]);

    }

}
