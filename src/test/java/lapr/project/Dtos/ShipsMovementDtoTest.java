package lapr.project.Dtos;

import org.junit.jupiter.api.Test;
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

        assertEquals(shipsMovementDto.getMMSI(), "210950875");
        shipsMovementDto.setMMSI(initialMMSI);
    }

    @Test
    void getTravelledDistanceTest(){
        assertEquals(shipsMovementDto.getTravelledDistance(), "23054");
    }

    @Test
    void setTravelledDistanceTest(){
        String inicialTravelledDistance = shipsMovementDto.getTravelledDistance();
        shipsMovementDto.setTravelledDistance("23006");

        assertEquals(shipsMovementDto.getTravelledDistance(), "23006");
        shipsMovementDto.setTravelledDistance(inicialTravelledDistance);
    }

    @Test
    void getDeltaDistanceTest(){
        assertEquals(shipsMovementDto.getDeltaDistance(), "450");
    }
    @Test
    void setDeltaDistanceTest(){
        String initialDeltaDistance = shipsMovementDto.getDeltaDistance();
        shipsMovementDto.setDeltaDistance("940");

        assertEquals(shipsMovementDto.getDeltaDistance(), "940");
        shipsMovementDto.setDeltaDistance(initialDeltaDistance);
    }

    @Test
    void getNumberOfMovementsTest(){
        assertEquals(shipsMovementDto.getNumberOfMovements(), "7");
    }
    @Test
    void setNumberOfMovementsTest(){
        String initialNumberOfMovements = shipsMovementDto.getNumberOfMovements();
        shipsMovementDto.setNumberOfMovements("24");

        assertEquals(shipsMovementDto.getNumberOfMovements(), "24");
        shipsMovementDto.setNumberOfMovements(initialNumberOfMovements);
    }

}