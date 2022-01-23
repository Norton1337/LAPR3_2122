package lapr.project.cargoShipUserStories;
import java.util.ArrayList;
import java.util.List;

public class CenterOfMass {
    private final List<Shapes> boatShapes = new ArrayList<>();
    
    public CenterOfMass(List<Shapes> boatShapes){
        this.boatShapes.addAll(boatShapes);
    }

    public Coords calculateCentroid(List<Shapes> shapes){
        List<Coords> centroids = new ArrayList<>();
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

     
        
        return new Coords(volumeX/totalVolume, 
                          volumeY/totalVolume,
                          volumeZ/totalVolume);
        


    }


    public List<Shapes> addContainers(double length, double width, double height, double remainingAmount, double floorHeight, Coords centerOfMass){
        double x=centerOfMass.getX();
        double z=centerOfMass.getZ();
        
        ArrayList<Shapes> newContainers = new ArrayList<>();
        double maxHeight=0;
        for (Shapes shapes : boatShapes) {
            maxHeight= Math.max(maxHeight,shapes.getP1().getY());
            maxHeight= Math.max(maxHeight,shapes.getP2().getY());
            maxHeight= Math.max(maxHeight,shapes.getP3().getY());
        }
        double widthOfShip = boatShapes.get(0).getWidth();

        double maxLength = 0;
        for (Shapes shapes : boatShapes) {
            maxLength= Math.max(maxLength,shapes.getP1().getX());
            maxLength= Math.max(maxLength,shapes.getP2().getX());
            maxLength= Math.max(maxLength,shapes.getP3().getX());
        }
       
        if(length<width){
            double temp = length;
            length=width;
            width=temp;
        }

        double orientation = Math.floor(widthOfShip/width);
        double amountInMiddle=0;

        if((remainingAmount-1)%4!=0 && (remainingAmount%2!=0))
            amountInMiddle=3;
           
        else if(((remainingAmount-1)%2==0 || remainingAmount == 1)&& (remainingAmount%2!=0))
            amountInMiddle=1;

        double separationOfX = length/2;
        double separationOfZ = width/2;

        double displacementX=0;
        double displacementY=0;
        double displacementZ=0;
        int placedAmount=0;
        while(placedAmount<remainingAmount){
            if(placedAmount==0 && amountInMiddle==1){
                Coords p1 = new Coords(x-separationOfX, floorHeight, z-separationOfZ);
                Coords p2 = new Coords(x-separationOfX, floorHeight, z-separationOfZ);
                Coords p3 = new Coords(x+separationOfX, floorHeight+height, z-separationOfZ);
                Shapes newContainer = new Shapes(p1, p2, p3, width, false);
                if(!freeSpace(newContainer)){
                    newContainers.add(newContainer);
                    placedAmount++;
                }
            }else if(placedAmount<3 && amountInMiddle==3 && orientation>3){
                int displacement=0;
                if(placedAmount==1)
                    displacement=1;
                else if (placedAmount==2)
                    displacement=-1;
                Coords p1 = new Coords(x-separationOfX , floorHeight+displacementY, z-separationOfZ + (displacement * separationOfZ));
                Coords p2 = new Coords(x-separationOfX, floorHeight+displacementY, z-separationOfZ + (displacement * separationOfZ));
                Coords p3 = new Coords(x+separationOfX , floorHeight+height+displacementY, z-separationOfZ + (displacement * separationOfZ));
                Shapes newContainer = new Shapes(p1, p2, p3, width, false);
                if(!freeSpace(newContainer)){
                    newContainers.add(newContainer);
                    placedAmount++;
                }
            }else{
                boolean placedContainer = false;
                while(!placedContainer)
                {
                    
                    Coords p1 = new Coords((x-(2*separationOfX)+displacementX), floorHeight+displacementY, (z-widthOfShip/2)+displacementZ);
                    Coords p2 = new Coords((x-(2*separationOfX)+displacementX), floorHeight+displacementY, (z-widthOfShip/2)+displacementZ);
                    Coords p3 = new Coords((x-separationOfX)+displacementX, floorHeight+height+displacementY, (z-widthOfShip/2)+displacementZ);
    
                    Coords n1 = new Coords(((x+(2*separationOfX))-displacementX), floorHeight+displacementY, ((z+widthOfShip/2)-2*separationOfZ)-displacementZ);
                    Coords n2 = new Coords((x+(2*separationOfX)-displacementX), floorHeight+displacementY, ((z+widthOfShip/2)-2*separationOfZ)-displacementZ);
                    Coords n3 = new Coords((x+separationOfX)-displacementX, floorHeight+height+displacementY, ((z+widthOfShip/2)-2*separationOfZ)-displacementZ);
                    Shapes newContainer = new Shapes(p1, p2, p3, width, false);
                    Shapes newContainer2 = new Shapes(n1, n2, n3, width, false);
    
    
                    if(!freeSpace(newContainer) && !freeSpace(newContainer2)){
                        newContainers.add(newContainer);
                        newContainers.add(newContainer2);
                        placedContainer=true;
                        placedAmount+=2;
                    }
                    
                    if((z-widthOfShip/2)+displacementZ + width >= widthOfShip){
                        displacementZ=0;
                        displacementX+=length;
                        if((x-(2*separationOfX)+displacementX>=maxLength)){
                            displacementX=0;
                            displacementY+=height;
                            if(floorHeight+height+displacementY>=maxHeight){
                                return newContainers;
                            }
                        }
                        
                    }else
                        displacementZ+=width;

    
                }
               
            }
            
        }

        return newContainers;

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
