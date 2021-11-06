package lapr.project.model.Ships;

public class Ship {

    private int id;
    private String MMSI;
    private String shipName;
    private String IMO;
    private String callSign;
    private int vesselType;
    private int length;
    private int width;
    private double draft;
    private String transceiverClass;
    private double loadCapacity;
    private Generator generator;

    //private final List<ShipPositonData> positionData;
    //private final List<Container> containers;


    public Ship(int id, String MMSI, String shipName, String IMO, String callSign,
                int vesselType, int length, int width, double draft,
                String transceiverClass, double loadCapacity, Generator generator) {

        // VALIDATIONS
        if(!isMMSIValid(MMSI)){
            throw new IllegalArgumentException("Not a valid MMSI.");
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
        this.transceiverClass = transceiverClass;
        this.loadCapacity = loadCapacity;
        this.generator = generator;
        //this.positionData = positionData;
        //this.containers = containers;
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

    public double getDraft() {
        return draft;
    }

    public void setDraft(double draft) {
        this.draft = draft;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
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

}
