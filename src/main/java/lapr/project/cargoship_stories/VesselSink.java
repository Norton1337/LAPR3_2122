package lapr.project.cargoship_stories;

import java.util.ArrayList;
import java.util.List;

import lapr.project.controller.helper_classes.KMTravelledCalculator;

public class VesselSink {
    private static final double WATER_DENSITY = 1000.0;
    public double getHeightDifference(double boatDensity, List<Shapes> withoutContainers, List<Shapes> withContainers){
        double boatVolume = getTotalVolume(withoutContainers);
        double boatMass = boatDensity * boatVolume;

        double massWithContainers = getMassPlaced(withContainers.size()-withoutContainers.size(),500.0) + boatMass;       
        
        double heightUnderWaterBoat = getHeightUnderwater(boatMass, withoutContainers);
        
        double heightUnderWaterWithContainers = getHeightUnderwater(massWithContainers, withContainers);
        
        return heightUnderWaterWithContainers-heightUnderWaterBoat;

    }

    public double getHeightUnderwater(double mass, List<Shapes> shapes){
      
        double volumeDispalced = mass/WATER_DENSITY;
        double maxWidth = 0;
        for (Shapes shape : shapes) {
            maxWidth= Math.max(maxWidth,shape.getWidth());
        }

        
        double maxLength = 0;
        for (Shapes shape : shapes) {
            maxLength= Math.max(maxLength,shape.getP1().getX());
            maxLength= Math.max(maxLength,shape.getP2().getX());
            maxLength= Math.max(maxLength,shape.getP3().getX());
        }
        
        return volumeDispalced/(maxWidth * maxLength);
    }


    public double getPressureExerted(List<Shapes> shapes, double density){
        List<Shapes> surfaceAreaShapes = new ArrayList<>();

        for (Shapes shape : shapes) {
            if(shape.getP1().getY()==0 || shape.getP2().getY()==0 || shape.getP3().getY()==0)
                surfaceAreaShapes.add(shape);

        }

        double volume = getTotalVolume(shapes);
        double mass = density * volume;
        double heightUnderwater = getHeightUnderwater(mass, shapes);

        double surfaceArea=0;
        for (Shapes shape : surfaceAreaShapes) {
            double maxY=0;
            double maxX = 0;
            
            maxY=Math.max(maxY, shape.getP1().getY());
            maxY=Math.max(maxY, shape.getP2().getY());
            maxY=Math.max(maxY, shape.getP3().getY());
            maxX=Math.max(maxX, shape.getP1().getX());
            maxX=Math.max(maxX, shape.getP2().getX());
            maxX=Math.max(maxX, shape.getP3().getX());


            double minY=shape.getP1().getY();
            double minX=shape.getP1().getX();
            minY=Math.min(minY, shape.getP2().getY());
            minY=Math.min(minY, shape.getP3().getY());
            minX=Math.min(minX, shape.getP2().getX());
            minX=Math.min(minX, shape.getP3().getX());
            

            if(shape.isTriangle()){

                
                KMTravelledCalculator calculator = new KMTravelledCalculator();
                double x = calculator.convertToRadians(maxX-minX);
                double y = calculator.convertToRadians(maxY-minY);
                double angle = Math.atan(x/y);

                double sideDown = Math.tan(angle) * heightUnderwater;
                surfaceArea += sideDown*shape.getWidth();

        
            }else{

                surfaceArea += (maxX-minX)*shape.getWidth();
            }
           

        }


        
        return (mass*9.8)/surfaceArea;
    }

    public double getTotalVolume(List<Shapes> shapes){
        double volume = 0;
        for (Shapes shape : shapes) 
            volume += shape.getVolume();
        return volume;
    }

    public double getMassPlaced(int amount, double weight){

        return amount*weight;
    }

}
