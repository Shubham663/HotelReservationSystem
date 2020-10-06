package bridgelabz.hotelReservation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for HotelReservation
 */
public class HotelReservationTest {
	HotelChain hotelChain;

	/**
	 * Rigorous Test :-)
	 */

	@Before
	public void init() {
		hotelChain = new HotelChain();
		Hotel LakeWood = new Hotel("LakeWood", 110, 90, 80, 80);
		Hotel BridgeWood = new Hotel("BridgeWood", 150, 50, 110, 50);
		Hotel RidgeWood = new Hotel("RidgeWood", 220, 150, 100, 40);
		LakeWood.setRating(3);
		BridgeWood.setRating(4);
		RidgeWood.setRating(5);
		hotelChain.addHotel(LakeWood);
		hotelChain.addHotel(BridgeWood);
		hotelChain.addHotel(RidgeWood);
	}

	@Test
	public void addHotelTest() {
		Hotel hotel = new Hotel("Lakewood", 100, 80, 80, 80);
		assertTrue("Execution terminated", hotelChain.addHotel(hotel));
	}

	@Test
	public void cheapestHotelTest_ReturnsTrue() {
		assertNotNull("No hotels exist inside the hotel chain ", hotelChain.bookCheapest(0));
	}

	@Test
	public void cheapestHotelTest_ReturnsNull() {
		HotelChain hChain = new HotelChain();
		assertNull("No hotels exist inside the hotel chain ", hChain.findCheapestHotel(1, 2, 0));
	}

	@Test
	public void cheapestHotelDateRange_ReturnsTrue() {
		assertNotNull("no hotels exist in the hotel chain", hotelChain.bookCheapest(0));
	}

	@Test
	public void cheapestHotel_HighestRating_DateRange_ReturnsTrue() {
		assertTrue("no hotels exist in the hotel chain", hotelChain.bookCheapestWithHighestRating(0));
	}

	@Test
	public void bestRatingHotel_ReturnsTrue() {
		assertTrue("no hotels exist in the hotel chain", hotelChain.bestRatingHotel());
	}

	@Test
	public void cheapestBestRatingHotel_ReturnsTrue() {
		assertTrue("no hotels exist in the hotel chain", hotelChain.bookCheapestWithHighestRating(0));
	}

	@Test
	public void cheapestBestRatingHotel_RewardCustomer_ReturnsTrue() {
		assertTrue("no hotels exist in the hotel chain", hotelChain.bookCheapestWithHighestRating(1));
	}
}
