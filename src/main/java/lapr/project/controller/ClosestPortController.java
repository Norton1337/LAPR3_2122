package lapr.project.controller;

import lapr.project.controller.helper_classes.KMTravelledCalculator;
import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.model.locals.Locals;
import lapr.project.model.ship_position_data.ShipPositonData;

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
    public Locals getPort(DataToBstController shipsList, DataToKDTreeController portsList, String callSign, String dateTime){
        
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
            Locals closestPort = portsList.getPortsTree().getAllElements().get(0);
            for (Locals ports : portsList.getPortsTree().getAllElements()) {
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
