/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller.shipSummary;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonçalo Ramalho
 */
public class ShipSummary {

    private String vesselName;
    private String startDataTime;
    private String endDataTime;
    private String totalTimeTravelled;
    private int totalMovements;
    private double MaxSOG;
    private double meanSOG;
    private double MaxCOG;
    private double MeanCOG;
    private String departure;
    private String arrival;
    private double travelledDistance;
    private double deltaDistance;

    public ShipSummary() {
        this.vesselName = null;
        this.startDataTime = null;
        this.endDataTime = null;
        this.totalTimeTravelled = null;
        this.totalMovements = -1;
        this.MaxSOG = -1;
        this.meanSOG = -1;
        this.MaxCOG = -1;
        this.MeanCOG = -1;
        this.departure = null;
        this.arrival = null;
        this.travelledDistance = -1;
        this.deltaDistance = -1;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getStartDataTime() {
        return startDataTime;
    }

    public void setStartDataTime(String startDataTime) {
        this.startDataTime = startDataTime;
    }

    public String getEndDataTime() {
        return endDataTime;
    }

    public void setEndDataTime(String endDataTime) {
        this.endDataTime = endDataTime;
    }

    public String getTotalTimeTravelled() {
        return totalTimeTravelled;
    }

    public void setTotalTimeTravelled(String totalTimeTravelled) {
        this.totalTimeTravelled = totalTimeTravelled;
    }

    public int getTotalMovements() {
        return totalMovements;
    }

    public void setTotalMovements(int totalMovements) {
        this.totalMovements = totalMovements;
    }

    public double getMaxSOG() {
        return MaxSOG;
    }

    public void setMaxSOG(double MaxSOG) {
        this.MaxSOG = MaxSOG;
    }

    public double getMeanSOG() {
        return meanSOG;
    }

    public void setMeanSOG(double meanSOG) {
        this.meanSOG = meanSOG;
    }

    public double getMaxCOG() {
        return MaxCOG;
    }

    public void setMaxCOG(double MaxCOG) {
        this.MaxCOG = MaxCOG;
    }

    public double getMeanCOG() {
        return MeanCOG;
    }

    public void setMeanCOG(double MeanCOG) {
        this.MeanCOG = MeanCOG;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public double getDeltaDistance() {
        return deltaDistance;
    }

    public void setDeltaDistance(double deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public String toString() {
        return "ShipsSummary:" + "\nVessel Name:" + getVesselName()+ "\nStart Data Time:" + getStartDataTime() + "\nEnd Data Time:" + getEndDataTime()
                + "\nTotal Time Travelled:" + getTotalTimeTravelled() + "\nTotal Movements:" + getTotalMovements() + "\nMax SOG:" + getMaxSOG() + "\nMean SOG:" + getMeanSOG() + "\nMax COG:" + getMaxCOG()
                + "\nMean COG:" + getMeanCOG() + "\nDeparture:" + getDeparture() + "\nArrival:" + getArrival() + "e\nTravelled Distance=" + getTravelledDistance() + "\nDelta Distance=" + getDeltaDistance();
    }
}
