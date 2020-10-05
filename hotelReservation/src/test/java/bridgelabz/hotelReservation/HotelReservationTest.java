package bridgelabz.hotelReservation;

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
    	assertTrue( "Execution terminated", hotelChain.addHotel() );
    }
}
