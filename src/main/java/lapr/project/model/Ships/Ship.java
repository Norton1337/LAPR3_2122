package lapr.project.model.Ships;

import java.util.List;

public class Ship {

    private int id;
    private String MMSI;
    private String shipName;
    private String IMO;
    private int callSign;
    private int vesselType;
    private int length;
    private int width;
    private int draft;
    private int cargoAxis;
    private String transceiverClass;
    private double loadCapacity;
    private Generator generator;
    private final List<ShipPositonData> positionData;

    public Ship(int id, String MMSI, String shipName, String IMO, int callSign,
                int vesselType, int length, int width, int draft, int cargoAxis,
                String transceiverClass, double loadCapacity, Generator generator, List<ShipPositonData> positionData) {

        if(!isMMSIValid(MMSI)){

        }

        this.id = id;
        this.MMSI = MMSI;
        this.shipName = shipName;
        this.IMO = IMO;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.draft = draft;
        this.cargoAxis = cargoAxis;
        this.transceiverClass = transceiverClass;
        this.loadCapacity = loadCapacity;
        this.generator = generator;
        this.positionData = positionData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        if (!isMMSIValid(MMSI)) {

        }
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

    public int getCallSign() {
        return callSign;
    }

    public void setCallSign(int callSign) {
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

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public int getCargoAxis() {
        return cargoAxis;
    }

    public void setCargoAxis(int cargoAxis) {
        this.cargoAxis = cargoAxis;
    }

    public String getTransceiverClass() {
        return transceiverClass;
    }

    public void setTransceiverClass(String transceiverClass) {
        this.transceiverClass = transceiverClass;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }


    public static boolean isMMSIValid(String mmsi) {
        if(mmsi.length() != 9){
            throw new IllegalArgumentException("Not a valid MMSI.");
        }
        return true;
    }

    public static boolean isIMOValid(String imo) {
        if(imo.length() != 7){
            throw new IllegalArgumentException("Not a valid IMO.");
        }
        return true;
    }
}
