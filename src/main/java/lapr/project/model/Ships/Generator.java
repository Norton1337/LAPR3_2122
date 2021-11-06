package lapr.project.model.Ships;

public class Generator {

    int id;
    private int numberOfGenerator;
    private double generatorsOutput;
    private int shipId;

    public Generator(int id, int numberOfGenerator, double generatorsOutput) {
        this.id = id;
        this.numberOfGenerator = numberOfGenerator;
        this.generatorsOutput = generatorsOutput;
    }

    public Generator(int id, int numberOfGenerator, double generatorsOutput, int shipId) {
        this.id = id;
        this.numberOfGenerator = numberOfGenerator;
        this.generatorsOutput = generatorsOutput;
        this.shipId = shipId;
    }

    public int getNumberOfGenerator() {
        return numberOfGenerator;
    }

    public void setNumberOfGenerator(int numberOfGenerator) {
        this.numberOfGenerator = numberOfGenerator;
    }

    public double getGeneratorsOutput() {
        return generatorsOutput;
    }

    public void setGeneratorsOutput(double generatorsOutput) {
        this.generatorsOutput = generatorsOutput;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
