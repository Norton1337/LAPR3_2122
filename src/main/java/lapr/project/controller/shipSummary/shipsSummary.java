/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller.shipSummary;

import java.util.ArrayList;
import java.util.List;
import lapr.project.BSTFolder.BST;
import lapr.project.model.Ships.Ship;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.controller.DataToBstController;

/**
 *
 * @author gonca
 */
public class shipsSummary {

    private Ship ship;
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
    
    protected DataToBstController bstController = new DataToBstController(); 
    private List<shipsSummary> summary;
    private 
    
    public shipsSummary(Ship ship) {
        this.ship = ship;
//        this.startDataTime = startDataTime;
//        this.endDataTime = endDataTime;
//        this.totalTimeTravelled = totalTimeTravelled;
//        this.totalMovements = totalMovements;
//        this.MaxSOG = MaxSOG;
//        this.meanSOG = meanSOG;
//        this.MaxCOG = MaxCOG;
//        this.MeanCOG = MeanCOG;
//        this.departure = departure;
//        this.arrival = arrival;
//        this.travelledDistance = travelledDistance;
//        this.deltaDistance = deltaDistance;
    }
    
    
    
    public DataToBstController getBstController() {
        return bstController;
    }
    
    
    public List<shipsSummary> getShipSummary(String code){
        if(code == null){
            return null;
        }
        
        return this.summary;
    }
}
