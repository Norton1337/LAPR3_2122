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
public class ShipSummaryController {
    
    
    private ShipAndData shipAndData;
    private List<ShipsSummary> summaryList;

    public ShipSummaryController(ShipAndData shipAndData) {
        if (this.shipAndData != null) {
            this.shipAndData = shipAndData;
        }
    }
    
    public ShipsSummary getShipSummary(){
        ShipsSummary shipSummary = new ShipsSummary();
        shipSummary.setVesselName(this.shipAndData.getShip().getShipName());
        shipSummary.setStartDataTime(getShipPositionData().get(0).getBaseDateTime());
        return shipSummary;
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

    @Override
    public String toString() {
        return "shipsSummary{" + "vesselName=" + vesselName + ", startDataTime=" + startDataTime + ", endDataTime=" + endDataTime + ", totalTimeTravelled=" + totalTimeTravelled + ", totalMovements=" + totalMovements + ", MaxSOG=" + MaxSOG + ", meanSOG=" + meanSOG + ", MaxCOG=" + MaxCOG + ", MeanCOG=" + MeanCOG + ", departure=" + departure + ", arrival=" + arrival + ", travelledDistance=" + travelledDistance + ", deltaDistance=" + deltaDistance + '}';
    }
    
    
}
