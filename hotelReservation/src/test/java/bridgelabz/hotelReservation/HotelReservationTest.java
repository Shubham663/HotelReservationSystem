package bridgelabz.hotelReservation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for HotelReservation
 */
public class HotelReservationTest 
{
	HotelChain hotelChain;
    /**
     * Rigorous Test :-)
     */
	
	@Before
	public void init() {
		hotelChain = new HotelChain();
	}
	
    @Test
    public void addHotelTest(){
    	Hotel hotel = new Hotel("Lakewood", 100,80);
    	assertTrue( "Execution terminated", hotelChain.addHotel(hotel) );
    }
    
    @Test
    public void cheapestHotelTest_returnsHotel() {
    	Hotel LakeWood = new Hotel("LakeWood", 110,90);
    	Hotel BridgeWood = new Hotel("BridgeWood",150,50);
    	Hotel RidgeWood = new Hotel("RidgeWood",220,150);
    	hotelChain.addHotel(LakeWood);
    	hotelChain.addHotel(BridgeWood);
    	hotelChain.addHotel(RidgeWood);
    	assertTrue("No hotels exist inside the hotel chain ",hotelChain.bookCheapest());
    }
    
    @Test
    public void cheapestHotelTest_returnsNull() {
    	assertNull("No hotels exist inside the hotel chain ",hotelChain.findCheapestHotel());
    }
}
