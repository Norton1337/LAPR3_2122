/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller.HelperClasses;

/**
 *
 * @author Gonçalo Ramalho
 */
public class ShipSummary {

    private String vesselName;
    private String startDateTime;
    private String endDateTime;
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
        this.startDateTime = null;
        this.endDateTime = null;
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
    
    /***
     * 
     * @return String with vesselName
     */
    public String getVesselName() {
        return vesselName;
    }

    /***
     * 
     * @param vesselName receives a String with the vesselName to set
     */
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    /***
     * 
     * @return a String with the startDateTime of the ship route
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /***
     * 
     * @param startDataTime receives a string with the time to set
     */
    public void setStartDateTime(String startDataTime) {
        this.startDateTime = startDataTime;
    }

    /***
     * 
     * @return a String with the EndDateTime of the ship route 
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /***
     * 
     * @param endDataTime receives a string with the time to set
     */
    public void setEndDateTime(String endDataTime) {
        this.endDateTime = endDataTime;
    }

    /***
     * 
     * @return a String with the total time travelled by the ship
     */
    public String getTotalTimeTravelled() {
        return totalTimeTravelled;
    }

    /***
     * 
     * @param totalTimeTravelled receives a string with the time to set
     */
    public void setTotalTimeTravelled(String totalTimeTravelled) {
        this.totalTimeTravelled = totalTimeTravelled;
    }

    /***
     * 
     * @return the total number of the ship movements
     */
    public int getTotalMovements() {
        return totalMovements;
    }

    /***
     * 
     * @param totalMovements receives a number to set the totalMovements
     */
    public void setTotalMovements(int totalMovements) {
        this.totalMovements = totalMovements;
    }

    /***
     * 
     * @return the value of the max SOG in the ship route
     */
    public double getMaxSOG() {
        return MaxSOG;
    }

    /***
     * 
     * @param MaxSOG receives a value to set the MaxSOG
     */
    public void setMaxSOG(double MaxSOG) {
        this.MaxSOG = MaxSOG;
    }

    /***
     * 
     * @return the value of the mean SOG in the ship route
     */
    public double getMeanSOG() {
        return meanSOG;
    }

    /***
     * 
     * @param meanSOG receives a value to set the meanSOG
     */
    public void setMeanSOG(double meanSOG) {
        this.meanSOG = meanSOG;
    }

    /***
     * 
     * @return the value of the MaxCOG in the ship route
     */
    public double getMaxCOG() {
        return MaxCOG;
    }

    /***
     * 
     * @param MaxCOG receives a value to set the MaxCOG
     */
    public void setMaxCOG(double MaxCOG) {
        this.MaxCOG = MaxCOG;
    }

    /***
     * 
     * @return the value of the meanCOG in the ship route
     */
    public double getMeanCOG() {
        return MeanCOG;
    }

    /***
     * 
     * @param MeanCOG receives a value to set the meanCOG
     */
    public void setMeanCOG(double MeanCOG) {
        this.MeanCOG = MeanCOG;
    }

    /***
     * 
     * @return a String with the departure coordinates
     */
    public String getDeparture() {
        return departure;
    }

    /***
     * 
     * @param departure receives a string to set the departure coordinates
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /***
     * 
     * @return a String with the arrival coordinates
     */
    public String getArrival() {
        return arrival;
    }

    /***
     * 
     * @param arrival receives a string to set the arrival coordinates
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /***
     * 
     * @return the value of the total travelled distance in the ship route
     */
    public double getTravelledDistance() {
        return travelledDistance;
    }

    /***
     * 
     * @param travelledDistance receives a value to set the travelled distance
     */
    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    /***
     * 
     * @return the value of the distance between arrival and departure
     */
    public double getDeltaDistance() {
        return deltaDistance;
    }

    /***
     * 
     * @param deltaDistance receives a value to set the deltaDistance
     */
    public void setDeltaDistance(double deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    @Override
    public String toString() {
        return "ShipsSummary:" + "\nVessel Name:" + getVesselName()+ "\nStart Data Time:" + getStartDateTime() + "\nEnd Data Time:" + getEndDateTime()
                + "\nTotal Time Travelled:" + getTotalTimeTravelled() + "\nTotal Movements:" + getTotalMovements() + "\nMax SOG:" + getMaxSOG() + "\nMean SOG:" + getMeanSOG() + "\nMax COG:" + getMaxCOG()
                + "\nMean COG:" + getMeanCOG() + "\nDeparture:" + getDeparture() + "\nArrival:" + getArrival() + "\nTravelled Distance=" + getTravelledDistance() + "\nDelta Distance=" + getDeltaDistance();
    }
}
