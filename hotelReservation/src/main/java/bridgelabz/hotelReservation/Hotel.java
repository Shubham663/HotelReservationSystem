package bridgelabz.hotelReservation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
			+ "\nweek days price for regular customer" + priceRegularCustomer);
	}
	
}

class HotelChain{
	List<Hotel> hotelsList = new ArrayList<Hotel>();
	public boolean addHotel() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the name for the Hotel");
		String name = sc.nextLine();
		System.out.println("Enter the price for regular customer");
		try {
			int priceRegularCustomer = sc.nextInt();
			sc.nextLine();				//catches next line character
			Hotel hotel = new Hotel(name, priceRegularCustomer);
			this.hotelsList.add(hotel);
			System.out.println("Hotel added succesfully");
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Value for price not correct. Error " + e.getMessage());
			return false;
		}catch(Exception exception) {
			System.out.println("Unexpected exception " +exception.getMessage());
			return false;
		}
	}
	@Override
	public String toString() {
		return this.hotelsList.toString();
	}
}