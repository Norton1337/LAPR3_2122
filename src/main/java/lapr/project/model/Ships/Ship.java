package lapr.project.model.Ships;

import static lapr.project.utils.Utils.ToInt;

public class Ship{

    private String id;
    private String MMSI;
    private String shipName;
    private String IMO;
    private String callSign;
    private int vesselType;
    private int length;
    private int width;
    private double draft;
    private double loadCapacity;
    private double distanceTravelled;


    //private final List<ShipPositonData> positionData;
    //private final List<Container> containers;


    public Ship(String MMSI, String shipName, String IMO, String callSign,
                int vesselType, int length, int width, double draft, double loadCapacity) {

        // VALIDATIONS
        if(!isMMSIValid(MMSI)){
            throw new IllegalArgumentException("Not a valid MMSI.");
        }

        this.MMSI = MMSI;
        this.shipName = shipName;
        this.IMO = IMO;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.draft = draft;
        this.loadCapacity = loadCapacity;
        //this.positionData = positionData;
        //this.containers = containers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        if(!isMMSIValid(MMSI)){
            throw new IllegalArgumentException("Not a valid MMSI.");
        };
        this.MMSI = MMSI;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getIMO() {
        return IMO;
    }

    public void setIMO(String IMO) {
        if (!isIMOValid(IMO)) {
            throw new IllegalArgumentException("Not a valid IMO.");
        }
        this.IMO = IMO;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public int getVesselType() {
        return vesselType;
    }

    public void setVesselType(int vesselType) {
        this.vesselType = vesselType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public double getDraft() {
        return draft;
    }

    public void setDraft(double draft) {
        this.draft = draft;
    }

    public static boolean isMMSIValid(String mmsi) {
        if (mmsi.length() != 9) {
            return false;
        }
        return true;
    }

    public static boolean isIMOValid(String imo) {
        if (imo.length() != 7) {
            return false;
        }
        return true;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    /*
    public List<ShipPositonData> getPositionData() {
        return positionData;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public boolean addShipPositionData(ShipPositonData shipPositonData){
        return this.positionData.add(shipPositonData);
    }

    public boolean addContainers(Container container){
        return this.containers.add(container);
    }
     */

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", MMSI='" + MMSI + '\'' +
                ", shipName='" + shipName + '\'' +
                ", IMO='" + IMO + '\'' +
                ", callSign='" + callSign + '\'' +
                ", vesselType=" + vesselType +
                ", length=" + length +
                ", width=" + width +
                ", draft=" + draft +
                ", loadCapacity=" + loadCapacity +
                '}';
    }



}
