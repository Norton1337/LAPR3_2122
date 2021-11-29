package lapr.project.Dtos;

import org.junit.jupiter.api.Test;

import lapr.project.dtos.ShipsMovementDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipsMovementDtoTest {

    private ShipsMovementDto shipsMovementDto = new ShipsMovementDto("210950000", "23054", "450", "7");


    @Test
    void getMMSITest(){
        assertEquals("210950000",shipsMovementDto.getMMSI() );
    }
    @Test
    void setMMSITest(){
        String initialMMSI = shipsMovementDto.getMMSI();
        shipsMovementDto.setMMSI("210950875");

        assertEquals("210950875", shipsMovementDto.getMMSI());
        shipsMovementDto.setMMSI(initialMMSI);
    }

    @Test
    void getTravelledDistanceTest(){
        assertEquals("23054", shipsMovementDto.getTravelledDistance());
    }

    @Test
    void setTravelledDistanceTest(){
        String inicialTravelledDistance = shipsMovementDto.getTravelledDistance();
        shipsMovementDto.setTravelledDistance("23006");

        assertEquals("23006", shipsMovementDto.getTravelledDistance());
        shipsMovementDto.setTravelledDistance(inicialTravelledDistance);
    }

    @Test
    void getDeltaDistanceTest(){
        assertEquals("450", shipsMovementDto.getDeltaDistance());
    }
    @Test
    void setDeltaDistanceTest(){
        String initialDeltaDistance = shipsMovementDto.getDeltaDistance();
        shipsMovementDto.setDeltaDistance("940");

        assertEquals("940", shipsMovementDto.getDeltaDistance());
        shipsMovementDto.setDeltaDistance(initialDeltaDistance);
    }

    @Test
    void getNumberOfMovementsTest(){
        assertEquals("7", shipsMovementDto.getNumberOfMovements());
    }
    @Test
    void setNumberOfMovementsTest(){
        String initialNumberOfMovements = shipsMovementDto.getNumberOfMovements();
        shipsMovementDto.setNumberOfMovements("24");

        assertEquals("24", shipsMovementDto.getNumberOfMovements());
        shipsMovementDto.setNumberOfMovements(initialNumberOfMovements);
    }

    @Test
    void compareToTest(){
        assertEquals(0, shipsMovementDto.compareTo(shipsMovementDto));
    }

    @Test
    void toStringTest(){
        String string = "ShipsMovementDto{" +
                        "MMSI='" + shipsMovementDto.getMMSI() + '\'' +
                        ", travelledDistance='" + shipsMovementDto.getTravelledDistance() + '\'' +
                        ", deltaDistance='" + shipsMovementDto.getDeltaDistance() + '\'' +
                        ", numberOfMovements='" + shipsMovementDto.getNumberOfMovements() + '\'' +
                        '}';
        assertEquals(string, shipsMovementDto.toString());
    }

}