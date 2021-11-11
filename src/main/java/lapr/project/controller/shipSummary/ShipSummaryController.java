/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller.shipSummary;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Ships.Ship;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.controller.DataToBstController;
import lapr.project.controller.MostTravelledShips.KMTravelledCalculator;
import lapr.project.controller.MostTravelledShips.MostTravelledShips;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.utils.Utils;

import static lapr.project.utils.Utils.orderedByTime;

/**
 *
 * @author gonca
 */
public class ShipSummaryController {

    private ShipAndData shipAndData;
    private MostTravelledShips mostTravelledShips;

    public ShipSummaryController(ShipAndData shipAndData) {
        if (this.shipAndData != null) {
            this.shipAndData = shipAndData;
        }
    }

    public ShipSummary getShipSummary() throws ParseException {
        List<ShipPositonData> list = getShipPositionData();
        ShipSummary shipSummary = new ShipSummary();
        shipSummary.setVesselName(this.shipAndData.getShip().getShipName());
        shipSummary.setStartDataTime(list.get(0).getBaseDateTime());
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
     * @return returns the list
     */
    public List<ShipPositonData> getShipPositionData() {
        List<ShipPositonData> list = orderedByTime(this.shipAndData.getShipPositonData());
        if (list == null) {
            return null;
        }
        return list;
    }

    /**
     * *
     * Returns the start date time of the ship route
     *
     * @return the start date time of the ship route
     */
    public String getStartDateTime() {
        return getShipPositionData().get(0).getBaseDateTime();
    }

    /**
     * *
     * Returns the end date time of the ship route
     *
     * @return the end date time of the ship route
     */
    public String getEndDateTime() {

        return getShipPositionData().get((getShipPositionData().size() - 1)).getBaseDateTime();

    }

    /**
     * *
     * Returns the total time travelled by the ship.
     *
     * @return the total time travelled by the ship.
     * @throws ParseException
     */
    public String getTotalTime() throws ParseException {
        Date startDate = Utils.convertDate(getShipPositionData().get(0).getBaseDateTime());
        Date endDate = Utils.convertDate(getShipPositionData().get(getShipPositionData().size() - 1).getBaseDateTime());
        if (startDate == null || endDate == null) {
            return null;
        }
        long diff = endDate.getTime() - startDate.getTime();
        TimeUnit time = TimeUnit.DAYS;
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
        return (getShipPositionData().size() - 1);
    }

    /**
     * *
     * Returns the Max SOG of the ship during its route
     *
     * @return Returns the Max SOG
     */
    public double getMaxSOG() {
        double max = getShipPositionData().get(0).getSog();

        for (int i = 1; i < getShipPositionData().size(); i++) {
            if (getShipPositionData().get(i).getSog() > max) {
                max = getShipPositionData().get(i).getSog();
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

        for (int i = 0; i < getShipPositionData().size(); i++) {
            mean += getShipPositionData().get(i).getSog();
        }
        return mean / getShipPositionData().size();
    }

    public double getMaxCOG() {
        double max = getShipPositionData().get(0).getCog();

        for (int i = 1; i < getShipPositionData().size(); i++) {
            if (getShipPositionData().get(i).getCog() > max) {
                max = getShipPositionData().get(i).getCog();
            }
        }
        return max;
    }

    public double getMeanCOG() {
        double mean = 0;

        for (int i = 0; i < getShipPositionData().size(); i++) {
            mean += getShipPositionData().get(i).getCog();
        }
        return mean / getShipPositionData().size();
    }

    public String getDeparture() {
        return getShipPositionData().get(0).getCoordinates();
    }

    public String getArrival() {
        return getShipPositionData().get(getShipPositionData().size() - 1).getCoordinates();
    }

    public double getTravelledDistance() {
        MostTravelledShips mostTravelledShips = new MostTravelledShips();
        return mostTravelledShips.getTotalPerShip(getShipPositionData());
    }

    public double getDeltaDistance() {
        MostTravelledShips mostTravelledShips = new MostTravelledShips();
        return mostTravelledShips.getDeltaDistance(getShipPositionData());
    }

    @Override
    public String toString() {
        String string = null;
        try {
            string = "ShipsSummary:" + "\nVessel Name:" + this.shipAndData.getShip().getShipName() + "\nStart Data Time:" + getStartDateTime() + "\nEnd Data Time:" + getEndDateTime()
                    + "\nTotal Time Travelled:" + getTotalTime() + "\nTotal Movements:" + getTotalMovements() + "\nMax SOG:" + getMaxSOG() + "\nMean SOG:" + getMeanSOG() + "\nMax COG:" + getMaxCOG()
                    + "\nMean COG:" + getMeanCOG() + "\nDeparture:" + getDeparture() + "\nArrival:" + getArrival() + "e\nTravelled Distance=" + getTravelledDistance() + "\nDelta Distance=" + getDeltaDistance();
        } catch (ParseException ex) {
            Logger.getLogger(ShipSummaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return string;
    }
}
