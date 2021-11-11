/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller.shipSummary;

import java.util.List;

import lapr.project.model.Ships.Ship;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.controller.DataToBstController;
import lapr.project.model.ShipPositionData.ShipPositonData;

import static lapr.project.utils.Utils.orderedByTime;

/**
 *
 * @author gonca
 */
public class shipsSummary {

    private Ship ship;
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
    private ShipAndData shipAndData;
    protected DataToBstController bstController = new DataToBstController();
    private List<shipsSummary> summary;

    public shipsSummary(ShipAndData shipAndData) {
        if (this.shipAndData != null) {
            this.shipAndData = shipAndData;
        }
        this.ship = shipAndData.getShip();
        this.vesselName = this.ship.getShipName();
        this.startDataTime = getStartDateTime();
        this.endDataTime = getEndDateTime();
//        this.totalTimeTravel  led = totalTimeTravelled;
        this.totalMovements = getTotalMovements();
        this.MaxSOG = getMaxSOG();
//        this.meanSOG = meanSOG;
//        this.MaxCOG = MaxCOG;
//        this.MeanCOG = MeanCOG;
//        this.departure = departure;
//        this.arrival = arrival;
//        this.travelledDistance = travelledDistance;
//        this.deltaDistance = deltaDistance;
    }

    public List<ShipPositonData> getShipPositionData() {
        return orderedByTime(this.shipAndData.getShipPositonData());
    }

    public String getStartDateTime() {

        return getShipPositionData().get(0).getBaseDateTime();

    }

    public String getEndDateTime() {

        return getShipPositionData().get((getShipPositionData().size() - 1)).getBaseDateTime();

    }

    public String getTotalTime() {

        return null;
    }

    public int getTotalMovements() {
        return (getShipPositionData().size() - 1);
    }

    public double getMaxSOG() {
        double max = getShipPositionData().get(0).getSog();
        
        for (int i = 1; i < getShipPositionData().size(); i++) {
            if (getShipPositionData().get(i).getSog() > max) {
                max = getShipPositionData().get(i).getSog();
            }
        }
        return max;
    }
    
    public double getMeanSOG() {
        double max = getShipPositionData().get(0).getSog();
        
        for (int i = 1; i < getShipPositionData().size(); i++) {
            if (getShipPositionData().get(i).getSog() > max) {
                max = getShipPositionData().get(i).getSog();
            }
        }
        return max;
    }

    public DataToBstController getBstController() {
        return bstController;
    }

    public List<shipsSummary> getShipSummary(String code) {
        if (code == null) {
            return null;
        }

        return this.summary;
    }

    @Override
    public String toString() {
        return "shipsSummary{" + "vesselName=" + vesselName + ", startDataTime=" + startDataTime + ", endDataTime=" + endDataTime + ", totalTimeTravelled=" + totalTimeTravelled + ", totalMovements=" + totalMovements + ", MaxSOG=" + MaxSOG + ", meanSOG=" + meanSOG + ", MaxCOG=" + MaxCOG + ", MeanCOG=" + MeanCOG + ", departure=" + departure + ", arrival=" + arrival + ", travelledDistance=" + travelledDistance + ", deltaDistance=" + deltaDistance + '}';
    }
    
    
}
