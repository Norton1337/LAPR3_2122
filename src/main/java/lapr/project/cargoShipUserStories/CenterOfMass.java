package lapr.project.cargoShipUserStories;

import java.util.ArrayList;

public class CenterOfMass {
    private ArrayList<Shapes> boatShapes = new ArrayList<>();

    public CenterOfMass(ArrayList<Shapes> boatShapes){
        this.boatShapes=boatShapes;
    }

    public Coords calculateCentroid(){
        ArrayList<Coords> centroids = new ArrayList<>();
        double volumeX = 0;
        double volumeY = 0;
        double volumeZ = 0;
        double totalVolume = 0;
        for (Shapes shape : boatShapes) {
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

        Coords centerOfMass = new Coords(volumeX/totalVolume, volumeY/totalVolume, volumeZ/totalVolume);
        System.out.println("Center of mass: \n" + centerOfMass.toString());
        return centerOfMass;
        


    }

}
