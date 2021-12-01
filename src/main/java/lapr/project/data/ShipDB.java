package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.utils.Utils.*;


public class ShipDB extends DataHandler implements IShipsDB {

    // TODO implement DB
    private List<Ship> allShips = new ArrayList<>();

    @Override
    public List<Ship> getAllShips() {
        return null;
    }

    @Override
    public Ship getShip(String id) {
        return null;
    }
    
    
    
    @Override
    public boolean addShip(Ship ship) {
        return false;
    }

    @Override
    public boolean updateShip(Ship ship) {
        return false;
    }

    @Override
    public boolean removeShip(String id) {
        return false;
    }

    @Override
    public Ship getShipByMMSI(String id) {
        return null;
    }

    public void importShips(String file){
        //todo add generators
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) !=null){


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("MMSI")){

                    if(allShips.isEmpty()){
                        Ship newShip = new Ship(list.get(0), list.get(7), list.get(8), list.get(9), toInt(list.get(10)),
                        toInt(list.get(11)), toInt(list.get(12)), toDouble(list.get(13)), toDouble(list.get(14)));
                        newShip.setId(randomUUID());
                        allShips.add(newShip);
                    }else{
                        if(!allShips.get(allShips.size()-1).getMMSI().equals(list.get(0))){
                            Ship newShip = new Ship(list.get(0), list.get(7), list.get(8), list.get(9), toInt(list.get(10)),
                            toInt(list.get(11)), toInt(list.get(12)), toDouble(list.get(13)), toDouble(list.get(14)));
                            newShip.setId(randomUUID());
                            allShips.add(newShip);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
