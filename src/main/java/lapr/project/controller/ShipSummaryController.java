/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.controller.HelperClasses.ShipSummary;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.utils.Utils;

import static lapr.project.utils.Utils.orderedByTime;

/**
 *
 * @author gonca
 */
public class ShipSummaryController {

    private ShipAndData shipAndData;
    private MostTravelledShipsController mostTravelledShips;
    private List<ShipPositonData> listPositionData;

    public ShipSummaryController(ShipAndData shipAndData) {
        this.shipAndData = shipAndData;
        this.listPositionData = getShipPositionDataOrderedByTime(shipAndData.getShipPositonData());
    }

    public ShipSummary getShipSummary() throws ParseException {
        ShipSummary shipSummary = new ShipSummary();
        shipSummary.setVesselName(this.shipAndData.getShip().getShipName());
        shipSummary.setStartDateTime(this.listPositionData.get(0).getBaseDateTime());
        shipSummary.setTotalTimeTravelled(getTotalTime());
        shipSummary.setTotalMovements(getTotalMovements());
        shipSummary.setMaxSOG(getMaxSOG());
        shipSummary.setMeanSOG(getMeanSOG());
        shipSummary.setMaxCOG(getMaxCOG());
        shipSummary.setMeanCOG(getMeanCOG());
        shipSummary.setDeparture(getDeparture());
        shipSummary.setArrival(getArrival());
        shipSummary.setTravelledDistance(getTravelledDistance());
        shipSummary.setDeltaDistance(getDeltaDistance());
        return shipSummary;
    }

    /**
     * *
     * Returns a list wiht the ship and its PositionData
     *
     * @param list
     * @return returns the list
     */
    public List<ShipPositonData> getShipPositionDataOrderedByTime(List<ShipPositonData> list) {
        if (list == null) {
            return null;
        }

        return orderedByTime(list);
    }

    /**
     * *
     * Returns the start date time of the ship route
     *
     * @return the start date time of the ship route
     */
    public String getStartDateTime() {
        return this.listPositionData.get(0).getBaseDateTime();
    }

    /**
     * *
     * Returns the end date time of the ship route
     *
     * @return the end date time of the ship route
     */
    public String getEndDateTime() {

        return this.listPositionData.get((this.listPositionData.size() - 1)).getBaseDateTime();

    }

    /**
     * *
     * Returns the total time travelled by the ship.
     *
     * @return the total time travelled by the ship.
     * @throws ParseException
     */
    public String getTotalTime() throws ParseException {
        Date startDate = Utils.convertDate(this.listPositionData.get(0).getBaseDateTime());
        Date endDate = Utils.convertDate(this.listPositionData.get(this.listPositionData.size() - 1).getBaseDateTime());
        if (startDate == null || endDate == null) {
            return null;
        }
        long diff = endDate.getTime() - startDate.getTime();
        TimeUnit time = TimeUnit.HOURS;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        String totalTime = String.valueOf(difference);
        return totalTime;
    }

    /**
     * *
     * Returns the number of totalMovements of the ship.
     *
     * @return the number of totalMovements of the ship.
     */
    public int getTotalMovements() {
        return (this.listPositionData.size() - 1);
    }

    /**
     * *
     * Returns the Max SOG of the ship during its route
     *
     * @return Returns the Max SOG
     */
    public double getMaxSOG() {
        double max = this.listPositionData.get(0).getSog();

        for (int i = 1; i < this.listPositionData.size(); i++) {
            if (this.listPositionData.get(i).getSog() > max) {
                max = this.listPositionData.get(i).getSog();
            }
        }
        return max;
    }

    /**
     * *
     * Returns the mean SOG of the ship in its route
     *
     * @return the mean SOG
     */
    public double getMeanSOG() {
        double mean = 0;

        for (int i = 0; i < this.listPositionData.size(); i++) {
            mean += this.listPositionData.get(i).getSog();
        }
        return mean / this.listPositionData.size();
    }

    public double getMaxCOG() {
        double max = this.listPositionData.get(0).getCog();

        for (int i = 1; i < this.listPositionData.size(); i++) {
            if (this.listPositionData.get(i).getCog() > max) {
                max = this.listPositionData.get(i).getCog();
            }
        }
        return max;
    }

    /***
     * Returns the mean COG for the route of the ship
     * @return the mean COG
     */
    public double getMeanCOG() {
        double mean = 0;

        for (int i = 0; i < this.listPositionData.size(); i++) {
            mean += this.listPositionData.get(i).getCog();
        }
        return mean / this.listPositionData.size();
    }

    public String getDeparture() {
        return this.listPositionData.get(0).getCoordinates();
    }
    
    /***
     * Returns string with the arrival coordinates of the ship
     * @return string with arrival coordinates
     */
    public String getArrival() {
        return this.listPositionData.get(this.listPositionData.size() - 1).getCoordinates();
    }
    
    /***
     * Returns the KM travelled by the ship.
     * @return 
     */
    public double getTravelledDistance() {
        MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();
        return mostTravelledShips.getTotalPerShip(this.listPositionData);
    }

    /***
     * Returns the distance in KM between the Departure position and the Arrival position.
     * @return distance between Departure and Arrival
     */
    public double getDeltaDistance() {
        MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();
        return mostTravelledShips.getDeltaDistance(this.listPositionData);
    }
    
    @Override
    public String toString() {
        String string = null;
        try {
            string = "ShipsSummary:" + "\nVessel Name:" + this.shipAndData.getShip().getShipName() + "\nStart Data Time:" + getStartDateTime() + "\nEnd Data Time:" + getEndDateTime()
                    + "\nTotal Time Travelled:" + getTotalTime() + "\nTotal Movements:" + getTotalMovements() + "\nMax SOG:" + getMaxSOG() + "\nMean SOG:" + getMeanSOG() + "\nMax COG:" + getMaxCOG()
                    + "\nMean COG:" + getMeanCOG() + "\nDeparture:" + getDeparture() + "\nArrival:" + getArrival() + "e\nTravelled Distance:" + getTravelledDistance() + "\nDelta Distance:" + getDeltaDistance();
        } catch (ParseException ex) {
            Logger.getLogger(ShipSummaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return string;
    }
}
