package lapr.project.model.Ships;

public class Generator {

    private String id;
    private int numberOfGenerator;
    private double generatorsOutput;
    private String shipId;

    public Generator(int numberOfGenerator, double generatorsOutput, String shipId) {
        this.numberOfGenerator = numberOfGenerator;
        this.generatorsOutput = generatorsOutput;
        this.shipId = shipId;
    }

    public Generator(int numberOfGenerator, double generatorsOutput) {
        this.numberOfGenerator = numberOfGenerator;
        this.generatorsOutput = generatorsOutput;
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

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Generator{" +
                "id=" + id +
                ", numberOfGenerator=" + numberOfGenerator +
                ", generatorsOutput=" + generatorsOutput +
                ", shipId=" + shipId +
                '}';
    }
}
