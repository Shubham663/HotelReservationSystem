package bridgelabz.hotelReservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(0));
		assertTrue("Returned hotel is not the cheapest ", list.equals(hotelChain.bookCheapest(0)));
	}

	@Test
	public void cheapestHotelTest_ReturnsNull() {
		HotelChain hChain = new HotelChain();
		assertNull("No hotels exist inside the hotel chain ", hChain.findCheapestHotel(1, 2, 0));
	}

	@Test
	public void cheapestHotelDateRange_ReturnsTrue() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(0));
		list.add(hotelChain.getHotelsList().get(1));
		assertTrue("returned hotels are not cheapest", list.equals(hotelChain.bookCheapest(0)));
	}
	
	@Test
	public void cheapestHotelDateRange_ReturnsFalse() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(0));
		assertFalse("returned hotels are not cheapest", list.equals(hotelChain.bookCheapest(0)));
	}

	@Test
	public void cheapestHotel_HighestRating_DateRange_ReturnsTrue() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(1));
		assertTrue("returned hotels are not cheapest with max rating", list.equals(hotelChain.bookCheapestWithHighestRating(0)));
	}

	@Test
	public void cheapestHotel_HighestRating_DateRange_ReturnsFalse() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(0));
		assertFalse("returned hotels might be cheapest with max rating", list.equals(hotelChain.bookCheapestWithHighestRating(0)));
	}
	
	@Test
	public void bestRatingHotel_ReturnsTrue() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(2));
		assertTrue("hotels returned are not all of max rating hotels",list.equals(hotelChain.bestRatingHotel()));
	}
	
	@Test
	public void bestRatingHotel_ReturnsFalse() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(1));
		assertFalse("hotels returned might all be of max rating hotels",list.equals(hotelChain.bestRatingHotel()));
	}

	@Test
	public void cheapestBestRatingHotelRewardCustomer_ReturnsTrue() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(2));
		assertTrue("cheapest hotels with rating not returned", list.equals(hotelChain.bookCheapestWithHighestRating(1)));
	}

	@Test
	public void cheapestBestRatingHotelRewardCustomer_ReturnsFalse() {
		List<Hotel> list = new ArrayList<>();
		list.add(hotelChain.getHotelsList().get(1));
		assertFalse("cheapest hotels with rating not returned", list.equals(hotelChain.bookCheapestWithHighestRating(1)));
	}
}
