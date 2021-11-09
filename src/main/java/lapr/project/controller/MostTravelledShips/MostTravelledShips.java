package lapr.project.controller.MostTravelledShips;

import java.util.ArrayList;
import java.util.List;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;

import lapr.project.model.Ships.Ship;

public class MostTravelledShips {

    
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();
    private double totalDistances;
    private double totalSOG;
    private int amountSOG;
    private TopShips topShips = new TopShips(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  
    public MostTravelledShips(){

        this.totalDistances=-1;
        this.totalSOG=-1;
    }

    /**
     * 
     * @param shipList
     * @param topN
     * @return
     */
    public TopShips getTopNShips(List <ShipAndData> shipList, int topN){
        
        if(listOfShips.size()==shipList.size()){
            return orderLists(new TopShips(listOfShips,listOfDistances,listOfSOG), 0, topN, 0, 0);
        }

        listOfDistances.add(getTotalPerShip(shipList.get(listOfShips.size()).getShipPositonData()));
        listOfShips.add(shipList.get(listOfShips.size()).getShip());
        listOfSOG.add(totalSOG/amountSOG);
        totalDistances=0;
        totalSOG=0;
        amountSOG=0;

        return getTopNShips(shipList, topN);
    }

    /**
     * 
     * @param ts
     * @param position
     * @param topN
     * @param largestDistance
     * @param lDPosition
     * @return
     */
    public TopShips orderLists(TopShips ts, int position, int topN, double largestDistance, int lDPosition){
        
        if(topShips.getListOfShip().size()==topN || topShips.getListOfShip().size()==ts.getListOfShip().size()){
            return topShips;
        }
            

        if(ts.getListOfDistances().get(position)>largestDistance && !topShips.getListOfShip().contains(ts.getListOfShip().get(position))){
            largestDistance=ts.getListOfDistances().get(position);
            lDPosition=position;
        }


        if(position==ts.getListOfDistances().size()-1){
            if(lDPosition==-1)
                return topShips;

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
     * 
     * @param posList
     * @return
     */
    public double getTotalPerShip(List<ShipPositonData> posList){
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        totalDistances=0;
        for (int i = 0; i < posList.size()-1; i++) {
            
            totalDistances += calculator.calculate(posList.get(i).getCoordinates().split("/")[0], posList.get(i).getCoordinates().split("/")[1],
                                     posList.get(i+1).getCoordinates().split("/")[0], posList.get(i+1).getCoordinates().split("/")[1]);

            totalSOG += posList.get(i).getSog();
            amountSOG++;
        }


        return totalDistances;
    }
    
    


}
