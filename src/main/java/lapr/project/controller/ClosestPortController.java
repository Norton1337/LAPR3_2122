package lapr.project.controller;

import lapr.project.controller.HelperClasses.KMTravelledCalculator;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;
import lapr.project.model.ShipPositionData.ShipPositonData;

/**
 * @author Paulo Norton
 */
public class ClosestPortController {
    
    private final KMTravelledCalculator calculator = new KMTravelledCalculator();

    /**
     * Gets the port that is closest to the given ship at certain time of its journey.
     * @param shipsList All the ships and their positional messages
     * @param portsList All the ports
     * @param callSign Callsign of desired ship
     * @param dateTime Date and time of desired positional message
     * @return the closest port if there is a positional message with DateTime, null otherwise
     */
    public PortsAndWarehouses getPort(DataToBstController shipsList, DataToKDTreeController portsList, String callSign, String dateTime){
        
        ShipAndData shipAndData = shipsList.getShipDataByCallSign(callSign);
        ShipPositonData positionData = null;
        for (ShipPositonData shipPositonData : shipAndData.getShipPositonData()) {
            if(dateTime.equals(shipPositonData.getBaseDateTime())){
                positionData = shipPositonData;
                break;
            }
        }
        
        if(positionData != null){
            double shortestDistance = calculator.calculate(positionData.getCoordinates().split("/")[0], positionData.getCoordinates().split("/")[1], portsList.getPortsTree().getAllElements().get(0).getCoordinates().split(",")[0], portsList.getPortsTree().getAllElements().get(0).getCoordinates().split(",")[1]);    
            PortsAndWarehouses closestPort = portsList.getPortsTree().getAllElements().get(0);
            for (PortsAndWarehouses ports : portsList.getPortsTree().getAllElements()) {
                double distance = calculator.calculate(positionData.getCoordinates().split("/")[0], positionData.getCoordinates().split("/")[1], ports.getCoordinates().split(",")[0], ports.getCoordinates().split(",")[1]);
                if(distance<shortestDistance){
                    shortestDistance=distance;
                    closestPort = ports;
                } 
            }
            return closestPort;
            
        }
        return null;

    }

}
