/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.controller.helper_classes.ShipSummary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author gonca
 */
class ShipSummaryTest {

    private ShipSummary s = new ShipSummary();

    @Test
    void getVesselNameTest() {
        s.setVesselName("VARAMO");
        assertEquals("VARAMO", s.getVesselName());
    }

    @Test
    void setVesselNameTest() {
        s.setVesselName("SPAR ARIES");
        assertEquals("SPAR ARIES", s.getVesselName());
    }

    @Test
    void getStartDateTimeTest() {
        s.setStartDateTime("31/12/2003 00:01");
        assertEquals("31/12/2003 00:01", s.getStartDateTime());
    }

    @Test
    void setStartDateTimeTest() {
        s.setStartDateTime("31/12/2003 00:01");
        assertEquals("31/12/2003 00:01", s.getStartDateTime());
    }

    @Test
    void getEndDateTimeTest() {
        s.setEndDateTime("31/12/2003 23:59");
        assertEquals("31/12/2003 23:59", s.getEndDateTime());
    }

    @Test
    void setEndDateTimeTest() {
        s.setEndDateTime("31/12/2003 23:59");
        assertEquals("31/12/2003 23:59", s.getEndDateTime());
    }

    @Test
    void getTotalTimeTravelledTest() {
        s.setTotalTimeTravelled("Days:0\tHours:5\tMinutes:3");
        assertEquals("Days:0\tHours:5\tMinutes:3", s.getTotalTimeTravelled());
    }

    @Test
    void setTotalTimeTravelledTest() {
        s.setTotalTimeTravelled("Days:0\tHours:5\tMinutes:3");
        assertEquals("Days:0\tHours:5\tMinutes:3", s.getTotalTimeTravelled());
    }

    @Test
    void getTotalMovementsTest() {
        s.setTotalMovements(3);
        assertEquals(3, s.getTotalMovements());
    }

    @Test
    void setTotalMovementsTest() {
        s.setTotalMovements(5);
        assertEquals(5, s.getTotalMovements());
    }

    @Test
    void getMaxSOGTest() {
        s.setMaxSOG(12.5);
        assertEquals(12.5, s.getMaxSOG());
    }

    @Test
    void setMaxSOGTest() {
        s.setMaxSOG(12.5);
        assertEquals(12.5, s.getMaxSOG());
    }

    @Test
    void getMeanSOGTest() {
        s.setMeanSOG(11.45);
        assertEquals(11.45, s.getMeanSOG());
    }

    @Test
    void setMeanSOGTest() {
        s.setMeanSOG(11.45);
        assertEquals(11.45, s.getMeanSOG());
    }

    @Test
    void getMaxCOGTest() {
        s.setMaxCOG(13.5);
        assertEquals(13.5, s.getMaxCOG());
    }

    @Test
    void setMaxCOGTest() {
        s.setMaxCOG(13.5);
        assertEquals(13.5, s.getMaxCOG());
    }

    @Test
    void getMeanCOGTest() {
        s.setMeanCOG(17.5);
        assertEquals(17.5, s.getMeanCOG());
    }

    @Test
    void setMeanCOGTest() {
        s.setMeanCOG(17.5);
        assertEquals(17.5, s.getMeanCOG());
    }

    @Test
    void getDepartureTest() {
        s.setDeparture("20.48627, -31.22163");
        assertEquals("20.48627, -31.22163", s.getDeparture());
    }

    @Test
    void setDepartureTest() {
        s.setDeparture("20.48627, -31.22163");
        assertEquals("20.48627, -31.22163", s.getDeparture());
    }

    @Test
    void getArrivalTest() {
        s.setArrival("30.48630, -40.22140");
        assertEquals("30.48630, -40.22140", s.getArrival());
    }

    @Test
    void setArrivalTest() {
        s.setArrival("30.48630, -40.22140");
        assertEquals("30.48630, -40.22140", s.getArrival());
    }

    @Test
    void getTravelledDistanceTest() {
        s.setTravelledDistance(497.7);
        assertEquals(497.7, s.getTravelledDistance());
    }

    @Test
    void setTravelledDistanceTest() {
        s.setTravelledDistance(497.7);
        assertEquals(497.7, s.getTravelledDistance());
    }

    @Test
    void getDeltaDistanceTest() {
        s.setDeltaDistance(430.80);
        assertEquals(430.80, s.getDeltaDistance());
    }

    @Test
    void setDeltaDistanceTest() {
        s.setDeltaDistance(430.80);
        assertEquals(430.80, s.getDeltaDistance());
    }

    @Test
    void toStringTest() {
        s.setVesselName("SPAR ARIES");
        s.setStartDateTime("31/12/2003 00:01");
        s.setEndDateTime("31/12/2003 23:59");
        s.setTotalTimeTravelled("Days:0\tHours:5\tMinutes:3");
        s.setTotalMovements(3);
        s.setMaxSOG(12.5);
        s.setMeanSOG(11.45);
        s.setMaxCOG(13.5);
        s.setMeanCOG(17.5);
        s.setDeparture("20.48627, -31.22163");
        s.setArrival("30.48630, -40.22140");
        s.setTravelledDistance(497.7);
        s.setDeltaDistance(430.80);

        String string = "ShipsSummary:\n"
                + "Vessel Name:SPAR ARIES\n"
                + "Start Data Time:31/12/2003 00:01\n"
                + "End Data Time:31/12/2003 23:59\n"
                + "Total Time Travelled:Days:0	Hours:5	Minutes:3\n"
                + "Total Movements:3\n"
                + "Max SOG:12.5\n"
                + "Mean SOG:11.45\n"
                + "Max COG:13.5\n"
                + "Mean COG:17.5\n"
                + "Departure:20.48627, -31.22163\n"
                + "Arrival:30.48630, -40.22140\n"
                + "Travelled Distance=497.7\n"
                + "Delta Distance=430.8";
        assertEquals(string, s.toString());


    }

}
