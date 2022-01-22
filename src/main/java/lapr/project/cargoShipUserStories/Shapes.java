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
            throw new IllegalArgumentException("All 3 coordinates can't have the same X coordinate");
        
        double height=0;
        if(p1.getY() != p2.getY())
            height = Math.abs(p1.getY() - p2.getY());
        else if(p1.getY() != p3.getY())
            height = Math.abs(p1.getY() - p3.getY());
        else
            throw new IllegalArgumentException("All 3 coordinates can't have the same Y coordinate");

        if(triangle)
            this.volume=((length*height)/2) * width;
        else
            this.volume = length*height*width;
    }


    public boolean intersects(Shapes other){
        boolean isIntersecting=true;

        double thisMaxX = Math.max(this.p1.getX(), this.p2.getX());
        thisMaxX = Math.max(thisMaxX, this.p3.getX());

        double thisMaxY = Math.max(this.p1.getY(), this.p2.getY());
        thisMaxY = Math.max(thisMaxY, this.p3.getY());

        double thisMaxZ = Math.max(this.p1.getZ(), this.p2.getZ());
        thisMaxZ = Math.max(thisMaxZ, this.p3.getZ())+other.getWidth();

        double otherMaxX = Math.max(other.p1.getX(), other.p2.getX());
        otherMaxX = Math.max(otherMaxX, other.p3.getX());

        double otherMaxY = Math.max(other.p1.getY(), other.p2.getY());
        otherMaxY = Math.max(otherMaxY, other.p3.getY());

        double otherMaxZ = Math.max(other.p1.getZ(), other.p2.getZ());
        otherMaxZ = Math.max(otherMaxZ, other.p3.getZ())+other.getWidth();


        double thisMinX = Math.min(this.p1.getX(), this.p2.getX());
        thisMinX = Math.min(thisMinX, this.p3.getX());

        double thisMinY = Math.min(this.p1.getY(), this.p2.getY());
        thisMinY = Math.min(thisMinY, this.p3.getY());

        double thisMinZ = Math.min(this.p1.getZ(), this.p2.getZ());
        thisMinZ = Math.min(thisMinZ, this.p3.getZ());

        double otherMinX = Math.min(other.p1.getX(), other.p2.getX());
        otherMinX = Math.min(otherMinX, other.p3.getX());

        double otherMinY = Math.min(other.p1.getY(), other.p2.getY());
        otherMinY = Math.min(otherMinY, other.p3.getY());

        double otherMinZ = Math.min(other.p1.getZ(), other.p2.getZ());
        otherMinZ = Math.min(otherMinZ, other.p3.getZ());


        if(thisMaxX <= otherMinX)
            isIntersecting = false;

        
            
        else if(otherMaxX <= thisMinX)
            isIntersecting = false;


            
        else if(thisMaxY <= otherMinY)
            isIntersecting = false;

        
            
        else if(otherMaxY <= thisMinY)
            isIntersecting = false;

        
            
        else if(thisMaxZ <= otherMinZ)
            isIntersecting = false;

    
            
        else if(otherMaxZ <= thisMinZ)
            isIntersecting = false;

        
            
    
        return isIntersecting;
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
