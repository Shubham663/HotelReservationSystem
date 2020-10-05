package bridgelabz.hotelReservation;

import java.nio.file.StandardWatchEventKinds;
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
	private int rewardCustomerWeekDayPrice;
	private int rewardCustomerWeekEndPrice;
	/**
	 * @return the rewardCustomerWeekDayPrice
	 */
	public int getRewardCustomerWeekDayPrice() {
		return rewardCustomerWeekDayPrice;
	}

	/**
	 * @param rewardCustomerWeekDayPrice the rewardCustomerWeekDayPrice to set
	 */
	public void setRewardCustomerWeekDayPrice(int rewardCustomerWeekDayPrice) {
		this.rewardCustomerWeekDayPrice = rewardCustomerWeekDayPrice;
	}

	/**
	 * @return the rewardCustomerWeekEndPrice
	 */
	public int getRewardCustomerWeekEndPrice() {
		return rewardCustomerWeekEndPrice;
	}

	/**
	 * @param rewardCustomerWeekEndPrice the rewardCustomerWeekEndPrice to set
	 */
	public void setRewardCustomerWeekEndPrice(int rewardCustomerWeekEndPrice) {
		this.rewardCustomerWeekEndPrice = rewardCustomerWeekEndPrice;
	}

	private int rating;

	/**
	 * @param name
	 * @param weekDayPrice
	 * @param weekEndPrice
	 */
	public Hotel(String name, int weekDayPrice, int weekEndPrice,int rewardCustomerWeekDayPrice, int rewardCustomerWeekEndPrice) {
		super();
		this.name = name;
		this.weekDayPrice = weekDayPrice;
		this.weekEndPrice = weekEndPrice;
		this.rewardCustomerWeekDayPrice = rewardCustomerWeekDayPrice;
		this.rewardCustomerWeekEndPrice = rewardCustomerWeekEndPrice;
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
				+ weekDayPrice + "\nreward customer's weekday price " + rewardCustomerWeekDayPrice
				+ "\n rewrd customer's weekend price " + rewardCustomerWeekEndPrice);
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

	public List<Hotel> findCheapestHotel(int weekdays, int weekends,int k) {
		if (hotelsList.size() == 0)
			return null;
		int minPrice;
		List<Hotel> minTotalPrice = new ArrayList<>();
		if(k == 0) {
			minPrice = (Integer)hotelsList.stream().map(t-> t.getWeekDayPrice()*weekdays+ t.getWeekEndPrice()*weekends).min(new CustomComparator()).get();
			for (Hotel hotel : hotelsList) {
				if(hotel.getWeekDayPrice() * weekdays + hotel.getWeekEndPrice() * weekends == minPrice) {
					minTotalPrice.add(hotel);
				}
			}
		}
		else {
			minPrice = (Integer)hotelsList.stream().map(t-> t.getRewardCustomerWeekDayPrice()*weekdays+ t.getRewardCustomerWeekEndPrice()*weekends).min(new CustomComparator()).get();
			for (Hotel hotel : hotelsList) {
				if(hotel.getRewardCustomerWeekDayPrice()* weekdays + hotel.getRewardCustomerWeekEndPrice() * weekends == minPrice) {
					minTotalPrice.add(hotel);
				}
			}
		}		
		return minTotalPrice;
	}
	
	public List<Integer> getWeekDaysAndWeekEnds(){
		List<Integer> returnedList = new ArrayList();
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
			return null;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(date1);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(date2);
		for (Date date = startCalendar.getTime(); startCalendar.before(endCalendar); startCalendar.add(Calendar.DATE,
				1), date = startCalendar.getTime()) {
			if ((date.getDay() + 1 == Calendar.SATURDAY) || (date.getDay() + 1 == Calendar.SUNDAY)) {
				weekends++;
			} 
			else {
				weekdays++;
			}
		}
		returnedList.add(weekdays);
		returnedList.add(weekends);
		return returnedList;
	}

	public List<Hotel> bookCheapest(int k) {
		List<Integer> weekDaysAndWeekEnds = getWeekDaysAndWeekEnds();
		if(weekDaysAndWeekEnds == null)
			return null;
		int weekdays = weekDaysAndWeekEnds.get(0);
		int weekends = weekDaysAndWeekEnds.get(1);
		List<Hotel> listOfHotelMinPrice = this.findCheapestHotel(weekdays, weekends,k);
		return listOfHotelMinPrice;
	}
	
	public boolean bookCheapestWithHighestRating(int k) {
		List<Integer> weekDaysAndWeekEnds = getWeekDaysAndWeekEnds();
		if(weekDaysAndWeekEnds == null)
			return false;
		int weekdays = weekDaysAndWeekEnds.get(0);
		int weekends = weekDaysAndWeekEnds.get(1);
		List<Hotel> listOfHotelMinPrice = this.findCheapestHotel(weekdays, weekends,k);
		if(listOfHotelMinPrice == null)
			return false;
		List<Hotel> minPriceHighestRating = new ArrayList();
		Integer max = listOfHotelMinPrice.stream().map(t -> t.getRating()).max(Integer::compare).get();
		for(Hotel hotel : listOfHotelMinPrice) {
			if(hotel.getRating() == max)
				minPriceHighestRating.add(hotel);
		}
		for(Hotel hotel : minPriceHighestRating)
			System.out.println(hotel.getName() + ", rating " + hotel.getRating() + " and Total Rates " + 
					(weekdays*hotel.getRewardCustomerWeekDayPrice() + weekends*hotel.getRewardCustomerWeekEndPrice()));
		return true;
	}
	
	public boolean bestRatingHotel() {
		if(hotelsList == null)
			return false;
		int max = this.hotelsList.stream().map(m -> m.getRating()).max(new CustomComparator()).get();
		Hotel minPriceHighestRating = null;
		for(Hotel hotel : this.hotelsList)
			if(hotel.getRating() == max) {
				minPriceHighestRating = hotel;
				break;
			}
		List<Integer> weekDaysAndWeekEnds = getWeekDaysAndWeekEnds();
		if(weekDaysAndWeekEnds == null)
			return false;
		int weekdays = weekDaysAndWeekEnds.get(0);
		int weekends = weekDaysAndWeekEnds.get(1);
		System.out.println("Hotel Name : " + minPriceHighestRating.getName()
							+ "\nPrice : " + (weekdays*minPriceHighestRating.getWeekDayPrice() + weekends*minPriceHighestRating.getWeekEndPrice()));
		return true;
	}
}