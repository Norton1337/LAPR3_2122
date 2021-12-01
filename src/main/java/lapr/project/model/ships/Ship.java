package lapr.project.model.ships;

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
    private int generatorAmount;
    private float generatorsPower;
    private String transcieverClass;

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
    }

    public Ship(String MMSI, String shipName, String IMO, String callSign,
                int vesselType, int length, int width, double draft, double loadCapacity, String transcieverClass) {

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
        // this.generatorAmount = generatorAmount;
        // this.generatorsPower = generatorsPower;
        this.transcieverClass = transcieverClass;
        
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
        return mmsi.length() == 9;
    }

    public static boolean isIMOValid(String imo) {
        return imo.length() == 7;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public int getGeneratorAmount() {
        return this.generatorAmount;
    }

    public void setGeneratorAmount(int generatorAmount) {
        this.generatorAmount = generatorAmount;
    }

    public float getGeneratorsPower() {
        return this.generatorsPower;
    }

    public void setGeneratorsPower(float generatorsPower) {
        this.generatorsPower = generatorsPower;
    }

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
                ", transcieverClass=" +transcieverClass +
                '}';
    }



}
