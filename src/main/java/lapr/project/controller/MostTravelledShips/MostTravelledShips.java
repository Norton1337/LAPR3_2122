package lapr.project.controller.MostTravelledShips;

import java.util.ArrayList;
import java.util.List;

import lapr.project.controller.ShipController;
import lapr.project.controller.ShipPositionDataController;
import lapr.project.model.Ships.Ship;

public class MostTravelledShips {

    private int pos;
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();
    private double totalDistances;
    private double totalSOG;
    private int amountSOG;
    private TopShips topShips = new TopShips(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public MostTravelledShips(){
        this.pos=0;
        this.totalDistances=-1;
        this.totalSOG=-1;
    }

    public TopShips getTopNShips(ShipController shipController, ShipPositionDataController posList, int topN){

        if(getPos()+1==posList.getShipData().size()){
            return orderLists(new TopShips(listOfShips,listOfDistances,listOfSOG), 0, topN, 0, 0);
        }
        System.out.println(listOfShips.size()+" / "+shipController.getAllShips().size());
        listOfDistances.add(getTotalPerShip2(posList));
        listOfShips.add(shipController.getAllShips().get(listOfShips.size()));
        listOfSOG.add(totalSOG/amountSOG);
        totalDistances=0;
        totalSOG=0;
        amountSOG=0;

        return getTopNShips(shipController, posList, topN);
    }

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


    public double getTotalPerShip2(ShipPositionDataController posList){
            
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        boolean test = true;
        while(test){

            if(getPos()+1==posList.getShipData().size()){
                test=false;
                break;
            }
                

            if(posList.getShipData().get(getPos()).getShipId()!=posList.getShipData().get(getPos()+1).getShipId()){
                setPos(pos+1);
                test=false;
                break;
            }

            totalDistances += calculator.calculate(posList.getShipData().get(getPos()).getCoordinates().split("/")[0], posList.getShipData().get(getPos()).getCoordinates().split("/")[1],
                                     posList.getShipData().get(getPos()+1).getCoordinates().split("/")[0], posList.getShipData().get(getPos()+1).getCoordinates().split("/")[1]);
            
            
            totalSOG += posList.getShipData().get(getPos()).getSog();
            amountSOG++;
            setPos(pos+1);

        }

        return totalDistances;
    }
    
    public double getTotalPerShip(ShipPositionDataController posList){
        
        totalSOG += posList.getShipData().get(getPos()).getSog();
        amountSOG++;
        if(getPos()+1==posList.getShipData().size())
            return totalDistances;
        

        if(posList.getShipData().get(getPos()).getShipId()!=posList.getShipData().get(getPos()+1).getShipId()){
            setPos(pos+1);
            return totalDistances;

        }
        
        
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        
        
        totalDistances += calculator.calculate(posList.getShipData().get(getPos()).getCoordinates().split("/")[0], posList.getShipData().get(getPos()).getCoordinates().split("/")[1],
                                     posList.getShipData().get(getPos()+1).getCoordinates().split("/")[0], posList.getShipData().get(getPos()+1).getCoordinates().split("/")[1]);
        setPos(pos+1);
        return getTotalPerShip(posList);
    }


    private int getPos() {
        return this.pos;
    }

    private void setPos(int pos) {
        this.pos = pos;
    }

}
