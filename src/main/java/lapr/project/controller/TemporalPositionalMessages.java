package lapr.project.controller;

import static lapr.project.utils.Utils.convertDate;
import static lapr.project.utils.Utils.orderedByTime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;

/**
 * @author Paulo Norton
 */
public class TemporalPositionalMessages {

    
    /**
     * Orders all positional messages of a ship by time
     * @param shipAndData a ship and its positional messages
     * @return the ship and its positional messages now ordered
     */
    public ShipAndData getSingleShipsAndData(ShipAndData shipAndData){
        ShipAndData sad;
        sad = shipAndData;
        sad.setShipPositonData(orderedByTime(sad.getShipPositonData()));
        return sad;
    }
    
    /**
     * Orders all positional messages of all ships by time
     * @param shipAndaDataList A list of multiple ships and their positional messages
     * @return all the ships with their positional messages now orderes
     */
    public List<ShipAndData> getAllShipsAndData(List<ShipAndData> shipAndaDataList){
        List<ShipAndData> listOfSAD;
        listOfSAD=shipAndaDataList;
        for (int i = 0; i < listOfSAD.size(); i++) {
            listOfSAD.get(i).setShipPositonData(orderedByTime(listOfSAD.get(i).getShipPositonData()));
        }
        return listOfSAD;
    }

    /**
     * Adds to a list all the possitional messages that are between 2 dates
     * @param shipAndData the ship and its positional messages
     * @param firstDate the first date and time (EX: "31/12/2020 00:00")
     * @param secondDate the second date and time (EX: "31/12/2020 01:00")
     * @return all the positional messages between the 2 dates
     * @throws ParseException in case the date format is not recognized
     */
    public List<ShipPositonData> getAllPositionalDataInPeriod(ShipAndData shipAndData, String firstDate, String secondDate) throws ParseException{
        ShipAndData sad = getSingleShipsAndData(shipAndData);

        List<ShipPositonData> positionList= new ArrayList<>();
        int pos = 0;
        while(pos<sad.getShipPositonData().size()){
            
            if(convertDate(sad.getShipPositonData().get(pos).getBaseDateTime()).compareTo(convertDate(firstDate))>=0 && convertDate(sad.getShipPositonData().get(pos).getBaseDateTime()).compareTo(convertDate(secondDate))<=0 ){
                positionList.add(sad.getShipPositonData().get(pos));
            }
            pos++;
        }
        return positionList;
    }

    /**
     * Gets all the positional messages of a ship that occured in a date and time 
     * @param shipAndData a ship and its positional messages
     * @param date a date and time
     * @return all the positional messages that happened in the date specified
     * @throws ParseException in case the date format is not recognized
     */
    public List<ShipPositonData> getAllPositionalDataInDate(ShipAndData shipAndData, String date) throws ParseException{
        ShipAndData sad = getSingleShipsAndData(shipAndData);

        List<ShipPositonData> positionList= new ArrayList<>();
        int pos = 0;
        while(pos<sad.getShipPositonData().size()){
            
            if(convertDate(sad.getShipPositonData().get(pos).getBaseDateTime()).compareTo(convertDate(date))==0){
                positionList.add(sad.getShipPositonData().get(pos));
            }
            pos++;
        }
        return positionList;
    }


}
