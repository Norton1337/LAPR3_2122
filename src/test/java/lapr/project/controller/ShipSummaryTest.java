/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.controller.HelperClasses.ShipSummary;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gonca
 */
public class ShipSummaryTest {
    
    private ShipSummary s = new ShipSummary();
    
    
    @Test 
    void getVesselNameTest(){
        s.setVesselName("VARAMO");
        assertEquals("VARAMO", s.getVesselName());
    }
    
    @Test
    void setVesselNameTest(){
        s.setVesselName("SPAR ARIES");
        assertEquals("SPAR ARIES", s.getVesselName());
    }
}
