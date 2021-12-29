package lapr.project.model.containers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Container {

    private String id;
    private int containerNumber;
    private int checkDigit;
    private String ISOCODE;
    private double containerGross;
    private double containerTare;
    private double containerPayload;
    private double containerVolume;
    private String certificates;
    private String repairRecommendations;
    private String containerType;

    public Container(int containerNumber, int checkDigit, String ISOCODE, double containerGross,
                     double containerTare, double containerPayload, double containerVolume,
                     String certificates, String repairRecomendations, String containerType) {
        this.containerNumber = containerNumber;
        this.checkDigit = checkDigit;
        this.ISOCODE = ISOCODE;
        this.containerGross = containerGross;
        this.containerTare = containerTare;
        this.containerPayload = containerPayload;
        this.containerVolume = containerVolume;
        this.certificates = certificates;
        this.repairRecommendations = repairRecomendations;
        this.containerType = containerType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getContainerGross() {
        return containerGross;
    }

    public void setContainerGross(double containerGross) {
        this.containerGross = containerGross;
    }

    public double getContainerTare() {
        return containerTare;
    }

    public void setContainerTare(double containerTare) {
        this.containerTare = containerTare;
    }

    public double getContainerPayload() {
        return containerPayload;
    }

    public void setContainerPayload(double containerPayload) {
        this.containerPayload = containerPayload;
    }

    public double getContainerVolume() {
        return containerVolume;
    }

    public void setContainerVolume(double containerVolume) {
        this.containerVolume = containerVolume;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getRepairRecommendations() {
        return repairRecommendations;
    }

    public void setRepairRecommendations(String repairRecommendations) {
        this.repairRecommendations = repairRecommendations;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }


}
