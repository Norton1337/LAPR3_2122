package lapr.project.model.containers;

public class Container {

    private int id;
    private int containerIdentification;
    private int containerNumber;
    private int checkDigit;
    private String ISOCODE;
    private double maxWeightWithContainer;
    private double weightContainer;
    private double maxWeightToBePacked;
    private double maxVolToBePacked;
    private String certificates;
    private String repairRecomendations;
    private String containerPosition;
    private boolean isRefrigerated;
    private int shipID;

    public Container(int id, int containerIdentification, int containerNumber, int checkDigit, String ISOCODE,
                     double maxWeightWithContainer, double weightContainer, double maxWeightToBePacked,
                     double maxVolToBePacked, String certificates, String repairRecomendations,
                     String containerPosition, boolean isRefrigerated) {


        this.id = id;
        this.containerIdentification = containerIdentification;
        this.containerNumber = containerNumber;
        this.checkDigit = checkDigit;
        this.ISOCODE = ISOCODE;
        this.maxWeightWithContainer = maxWeightWithContainer;
        this.weightContainer = weightContainer;
        this.maxWeightToBePacked = maxWeightToBePacked;
        this.maxVolToBePacked = maxVolToBePacked;
        this.certificates = certificates;
        this.repairRecomendations = repairRecomendations;
        this.containerPosition = containerPosition;
        this.isRefrigerated = isRefrigerated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContainerIdentification() {
        return containerIdentification;
    }

    public void setContainerIdentification(int containerIdentification) {
        this.containerIdentification = containerIdentification;
    }

    public int getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(int containerNumber) {
        this.containerNumber = containerNumber;
    }

    public int getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(int checkDigit) {
        this.checkDigit = checkDigit;
    }

    public String getISOCODE() {
        return ISOCODE;
    }

    public void setISOCODE(String ISOCODE) {
        this.ISOCODE = ISOCODE;
    }

    public void setMaxWeightWithContainer(double maxWeightWithContainer) {
        this.maxWeightWithContainer = maxWeightWithContainer;
    }

    public void setWeightContainer(double weightContainer) {
        this.weightContainer = weightContainer;
    }

    public void setMaxWeightToBePacked(double maxWeightToBePacked) {
        this.maxWeightToBePacked = maxWeightToBePacked;
    }

    public void setMaxVolToBePacked(double maxVolToBePacked) {
        this.maxVolToBePacked = maxVolToBePacked;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getRepairRecomendations() {
        return repairRecomendations;
    }

    public void setRepairRecomendations(String repairRecomendations) {
        this.repairRecomendations = repairRecomendations;
    }

    public double getMaxWeightWithContainer() {
        return maxWeightWithContainer;
    }

    public double getWeightContainer() {
        return weightContainer;
    }

    public double getMaxWeightToBePacked() {
        return maxWeightToBePacked;
    }

    public double getMaxVolToBePacked() {
        return maxVolToBePacked;
    }

    public String getContainerPosition() {
        return containerPosition;
    }

    public void setContainerPosition(String containerPosition) {
        this.containerPosition = containerPosition;
    }

    public boolean isRefrigerated() {
        return isRefrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        isRefrigerated = refrigerated;
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }
}
