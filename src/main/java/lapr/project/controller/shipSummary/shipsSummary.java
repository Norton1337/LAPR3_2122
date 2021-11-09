/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller.shipSummary;

import java.util.ArrayList;
import java.util.List;
import lapr.project.BSTFolder.AVL;
import lapr.project.BSTFolder.BST;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.controller.DataToBstController;

/**
 *
 * @author gonca
 */
public class shipsSummary {

    private BST shipBst;
    protected DataToBstController bstController = new DataToBstController();
    private List<shipsSummary> summary;
    
    public shipsSummary() {
        this.shipBst = bstController.getShipBst();
        this.summary = new ArrayList<>();
    }
    
    public 
}
