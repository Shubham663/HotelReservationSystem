package bridgelabz.hotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Hotel{
	private String name;
	private int priceRegularCustomer;
	
	
	
	/**
	 * @param name
	 * @param priceRegularCustomer
	 */
	public Hotel(String name, int priceRegularCustomer) {
		super();
		this.name = name;
		this.priceRegularCustomer = priceRegularCustomer;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the priceRegularCustomer
	 */
	public int getPriceRegularCustomer() {
		return priceRegularCustomer;
	}
	/**
	 * @param priceRegularCustomer the priceRegularCustomer to set
	 */
	public void setPriceRegularCustomer(int priceRegularCustomer) {
		this.priceRegularCustomer = priceRegularCustomer;
	}
	
	@Override
	public String toString() {
	return ("\n\nName of hotel :" + name
			+ "\nprice for regular customer " + priceRegularCustomer);
	}
	
}

class HotelChain{
	List<Hotel> hotelsList = new ArrayList<Hotel>();
	public boolean addHotel(Hotel hotel) {
		this.hotelsList.add(hotel);
//		System.out.println("Hotel added succesfully");
		return true;
	}
	@Override
	public String toString() {
		return this.hotelsList.toString();
	}
	
	public Hotel findCheapestHotel() {
		if(hotelsList.size() == 0)
			return null;
		Hotel returnedHotel = hotelsList.get(0);
		for(Hotel hotel : hotelsList) {
			returnedHotel = returnedHotel.getPriceRegularCustomer() < hotel.getPriceRegularCustomer()? returnedHotel:hotel;
		}
		return returnedHotel;	
	}
	
	public boolean bookCheapest() {
		Scanner sc= new Scanner(System.in);
		Date date1=null;
		Date date2=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		System.out.println("Enter the start day of your stay");
		String start = sc.nextLine();
		System.out.println("Enter the end day of your stay");
		String end = sc.nextLine();
		try {
		    date1 = dateFormat.parse(start);
		    date2 = dateFormat.parse(end);
		}catch(ParseException e) {
		    e.printStackTrace();
		    return false;
		}
		long difference_In_Time = date2.getTime() - date1.getTime();
		long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time)% 365;
		Hotel hotel = this.findCheapestHotel();
		System.out.println("Hotel : " + hotel.getName()
				+ "\nTotal Rates : " +hotel.getPriceRegularCustomer()*difference_In_Days);
		return true;
	}
}