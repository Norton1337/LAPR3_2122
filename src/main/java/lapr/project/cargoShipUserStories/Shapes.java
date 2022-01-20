package lapr.project.cargoShipUserStories;

public class Shapes{
    private Coords p1;
    private Coords p2;
    private Coords p3;
    private double width;
    private boolean triangle;

    private double volume;

    
    
    public Shapes(Coords p1, Coords p2, Coords p3, double width, boolean triangle){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.width=width;
        this.triangle=triangle;

        double length=0;
        if(p1.getX() != p2.getX())
            length = Math.abs(p1.getX() - p2.getX());
        else if(p1.getX() != p3.getX())
            length = Math.abs(p1.getX() - p3.getX());
        else
            System.err.println("ERROR ERROR");
        
        double height=0;
        if(p1.getY() != p2.getY())
            height = Math.abs(p1.getY() - p2.getY());
        else if(p1.getY() != p3.getY())
            height = Math.abs(p1.getY() - p3.getY());
        else
            System.err.println("ERROR ERROR");

        if(triangle)
            this.volume=((length*height)/2) * width;
        else
            this.volume = length*height*width;
    }


    public Coords getP1() {
        return this.p1;
    }

    public void setP1(Coords p1) {
        this.p1 = p1;
    }

    public Coords getP2() {
        return this.p2;
    }

    public void setP2(Coords p2) {
        this.p2 = p2;
    }

    public Coords getP3() {
        return this.p3;
    }

    public void setP3(Coords p3) {
        this.p3 = p3;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isTriangle() {
        return this.triangle;
    }

    public void setIsTriangle(boolean triangle) {
        this.triangle = triangle;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

}
