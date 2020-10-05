package bridgelabz.hotelReservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class HotelReservations 
{
	
    public static void main( String[] args ){
    	System.out.println("Welcome to Hotel Reservation Program");
    	Hotel LakeWood = new Hotel("LakeWood", 110,90);
    	Hotel BridgeWood = new Hotel("BridgeWood",150,50);
    	Hotel RidgeWood = new Hotel("RidgeWood",220,150);
    	HotelChain hotelChain = new HotelChain();
    	hotelChain.addHotel(LakeWood);
    	hotelChain.addHotel(BridgeWood);
    	hotelChain.addHotel(RidgeWood);
    	hotelChain.bookCheapest();
    }
}
