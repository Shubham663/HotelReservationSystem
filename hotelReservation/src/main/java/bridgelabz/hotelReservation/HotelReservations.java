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
    	Hotel LakeWood = new Hotel("LakeWood", 110,90,80,80);
    	Hotel BridgeWood = new Hotel("BridgeWood",150,50,110,50);
    	Hotel RidgeWood = new Hotel("RidgeWood",220,150,100,40);
    	LakeWood.setRating(3);
    	BridgeWood.setRating(4);
    	RidgeWood.setRating(5);
    	HotelChain hotelChain = new HotelChain();
    	hotelChain.addHotel(LakeWood);
    	hotelChain.addHotel(BridgeWood);
    	hotelChain.addHotel(RidgeWood);
    	
    	hotelChain.bookCheapest(0);
    }
}
