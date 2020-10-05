package bridgelabz.hotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Hotel {
	private String name;
	private int weekDayPrice;
	private int weekEndPrice;
	private int rating;

	/**
	 * @param name
	 * @param weekDayPrice
	 * @param weekEndPrice
	 */
	public Hotel(String name, int weekDayPrice, int weekEndPrice) {
		super();
		this.name = name;
		this.weekDayPrice = weekDayPrice;
		this.weekEndPrice = weekEndPrice;
	}

	
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
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
	 * @return the weekDayPrice
	 */
	public int getWeekDayPrice() {
		return weekDayPrice;
	}

	/**
	 * @param weekDayPrice the weekDayPrice to set
	 */
	public void setWeekDayPrice(int weekDayPrice) {
		this.weekDayPrice = weekDayPrice;
	}

	/**
	 * @return the weekEndPrice
	 */
	public int getWeekEndPrice() {
		return weekEndPrice;
	}

	/**
	 * @param weekEndPrice the weekEndPrice to set
	 */
	public void setWeekEndPrice(int weekEndPrice) {
		this.weekEndPrice = weekEndPrice;
	}

	@Override
	public String toString() {
		return ("\n\nName of hotel : " + name + "\nweekend price : " + weekEndPrice + "\nweekday price : "
				+ weekDayPrice);
	}

}

class HotelChain {
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

	public Hotel findCheapestHotel(int weekdays, int weekends) {
		if (hotelsList.size() == 0)
			return null;
		Hotel returnedHotel = hotelsList.get(0);
		for (Hotel hotel : hotelsList) {
			returnedHotel = returnedHotel.getWeekDayPrice() * weekdays
					+ returnedHotel.getWeekEndPrice() * weekends < hotel.getWeekDayPrice() * weekdays
							+ hotel.getWeekEndPrice() * weekends ? returnedHotel : hotel;
		}
		return returnedHotel;
	}

	public boolean bookCheapest() {
		Scanner sc = new Scanner(System.in);
		int weekdays = 0, weekends = 0;
		Date date1 = null;
		Date date2 = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		System.out.println("Enter the start day of your stay");
		String start = sc.nextLine();
		System.out.println("Enter the end day of your stay");
		String end = sc.nextLine();
		try {
			date1 = dateFormat.parse(start);
			date2 = dateFormat.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(date1);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(date2);
		for (Date date = startCalendar.getTime(); startCalendar.before(endCalendar); startCalendar.add(Calendar.DATE,
				1), date = startCalendar.getTime()) {
			if ((date.getDay() + 1 == Calendar.SATURDAY) || (date.getDay() + 1 == Calendar.SUNDAY)) { // or sunday
				weekends++;
			} else {
				weekdays++;
			}
		}
//		long difference_In_Time = date2.getTime() - date1.getTime();
//		long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time)% 365;
		Hotel hotel = this.findCheapestHotel(weekdays, weekends);
		if (hotel == null)
			return false;
		System.out.println("Hotel : " + hotel.getName() + "\nTotal Rates : "
				+ (hotel.getWeekDayPrice() * weekdays + hotel.getWeekEndPrice() * weekends));
		return true;
	}
}