package lapr.project.cargoShipUserStories;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CenterOfMass {
    private ArrayList<Shapes> boatShapes = new ArrayList<>();

    public CenterOfMass(ArrayList<Shapes> boatShapes){
        this.boatShapes=boatShapes;
    }

    public Coords calculateCentroid(ArrayList<Shapes> shapes){
        ArrayList<Coords> centroids = new ArrayList<>();
        double volumeX = 0;
        double volumeY = 0;
        double volumeZ = 0;
        double totalVolume = 0;
        for (Shapes shape : shapes) {
            double lowestX=0;
            double lowestY=0;
            double lowestZ=0;

            lowestX = Math.min(shape.getP1().getX(), shape.getP2().getX());
            lowestX = Math.min(lowestX, shape.getP3().getX());

            lowestY = Math.min(shape.getP1().getY(), shape.getP2().getY());
            lowestY = Math.min(lowestY, shape.getP3().getY());

            lowestZ = Math.min(shape.getP1().getZ(), shape.getP2().getZ());
            lowestZ = Math.min(lowestZ, shape.getP3().getZ());

            double medianX = 0;
            double medianY = 0;
            double medianZ = 0;
            if(shape.isTriangle()){
                medianX = ((shape.getP1().getX() + shape.getP2().getX() + shape.getP3().getX())/3)+lowestX;
                medianY = ((shape.getP1().getY() + shape.getP2().getY() + shape.getP3().getY())/3)+lowestY;
                medianZ = (shape.getWidth()/2)+lowestZ;
                centroids.add(new Coords(medianX, medianY, medianZ));
                
            }
            else{
                medianX = shape.getP1().getX();
                if(shape.getP2().getX()!=medianX)
                    medianX -= shape.getP2().getX();
                else
                    medianX -= shape.getP3().getX();
                    
                medianX = (Math.abs(medianX)/2) +lowestX;

                medianY = shape.getP1().getY();
                if(shape.getP2().getY()!=medianY)
                    medianY -= shape.getP2().getY();
                else
                    medianY -= shape.getP3().getY();
                    
                medianY = (Math.abs(medianY)/2) +lowestY;

                medianZ = (shape.getWidth()/2)+lowestZ;
                centroids.add(new Coords(medianX, medianY, medianZ));
                
              
            }
            
            volumeX += medianX*shape.getVolume();
            volumeY += medianY*shape.getVolume();
            volumeZ += medianZ*shape.getVolume();

            totalVolume +=shape.getVolume();


        }

        final DecimalFormat df = new DecimalFormat("0.00");
        
        return new Coords(  Double.parseDouble(df.format(volumeX/totalVolume)), 
                            Double.parseDouble(df.format(volumeY/totalVolume)),
                            Double.parseDouble(df.format(volumeZ/totalVolume)));
        


    }


    public ArrayList<Shapes> addContainers(double length, double width, double height, int remainingAmount, Coords centerOfMass){
        double x=centerOfMass.getX();
        double y=centerOfMass.getY();
        double z=centerOfMass.getZ();

        ArrayList<Shapes> newContainers = new ArrayList<>();
        double maxHeight=0;
        for (Shapes shapes : boatShapes) {
            maxHeight= Math.max(maxHeight,shapes.getP1().getY());
            maxHeight= Math.max(maxHeight,shapes.getP2().getY());
            maxHeight= Math.max(maxHeight,shapes.getP3().getY());
        }
        double widthOfShip = boatShapes.get(0).getWidth()-10;

        double maxLength = 0;
        for (Shapes shapes : boatShapes) {
            maxLength= Math.max(maxLength,shapes.getP1().getX());
            maxLength= Math.max(maxLength,shapes.getP2().getX());
            maxLength= Math.max(maxLength,shapes.getP3().getX());
        }

        System.out.println("Max Length: "+maxLength);

        double orientation = Math.max(Math.floor(widthOfShip/width), Math.floor(widthOfShip/length));
        
        System.out.println("only " + (orientation) + " containers fit ");
       
        if(length<width){
            double temp = length;
            length=width;
            width=temp;
        }
       
        Coords p1 = new Coords(0, 0, 0);
        Coords p2 = new Coords(1, 0, 0);
        Coords p3 = new Coords(1, 1, 0);
        Shapes newContainer = new Shapes(p1, p2, p3, 1, false);
        newContainers.add(newContainer);
        compareCentroids(newContainers);


        return newContainers;

    }

    public boolean compareCentroids(ArrayList<Shapes> newShapes){

        System.out.println(calculateCentroid(boatShapes)+"\n");
        boatShapes.addAll(newShapes);
        System.out.println(calculateCentroid(boatShapes));
        return true;
    }

    

    public boolean freeSpace(Shapes container){
        boolean doesIntersect = false;
        for (Shapes shapes : boatShapes) {
            doesIntersect = shapes.intersects(container);
            if(doesIntersect)
                break;
        }
        return doesIntersect;
    }
}
