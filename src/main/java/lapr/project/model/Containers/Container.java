package lapr.project.model.Containers;

public class Container {

    private int id;
    private int containerIdentification;
    private int containerNumber;
    private int checkDigit;
    private String ISOCODE;
    private float maxWeightWithContainer;
    private float weightContainer;
    private float maxWeightToBePacked;
    private float maxVolToBePacked;
    private String certificates;
    private String repairRecomendations;
    private String containerPosition;
    private boolean isRefrigerated;


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

    public float getMaxWeightWithContainer() {
        return maxWeightWithContainer;
    }

    public void setMaxWeightWithContainer(float maxWeightWithContainer) {
        this.maxWeightWithContainer = maxWeightWithContainer;
    }

    public float getWeightContainer() {
        return weightContainer;
    }

    public void setWeightContainer(float weightContainer) {
        this.weightContainer = weightContainer;
    }

    public float getMaxWeightToBePacked() {
        return maxWeightToBePacked;
    }

    public void setMaxWeightToBePacked(float maxWeightToBePacked) {
        this.maxWeightToBePacked = maxWeightToBePacked;
    }

    public float getMaxVolToBePacked() {
        return maxVolToBePacked;
    }

    public void setMaxVolToBePacked(float maxVolToBePacked) {
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
}
