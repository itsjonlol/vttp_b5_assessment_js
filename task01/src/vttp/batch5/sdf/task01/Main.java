package vttp.batch5.sdf.task01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static vttp.batch5.sdf.task01.Utilities.toMonth;
import static vttp.batch5.sdf.task01.Utilities.toSeason;
import static vttp.batch5.sdf.task01.Utilities.toWeekday;
import static vttp.batch5.sdf.task01.WeatherUtility.toWeather;
// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		Helper helper = new Helper();
		String fileName = "task01/day.csv";
		helper.readCSV(fileName);
		List<Record> filteredList = new ArrayList<>();
		
		filteredList = helper.getFilteredList(); // this is the filtered top 5 list
		filteredList.forEach(System.out::println);

		Record oneRecord = filteredList.get(0);

        // System.out.println(oneRecord.getMonth());
		
		// System.out.println(toSeason(Integer.parseInt(oneRecord.getSeason())));

		for (int i = 0; i<filteredList.size(); i++) {
			String placeholder = "";
			if (i==0) {
				placeholder = "first";

			} else if (i == 1) {
				placeholder = "second";
			} else if (i == 2) {
				placeholder = "third";
			} else if (i == 3) {
				placeholder = "fourth";
			} else if (i == 4) {
				placeholder = "fifth";
			}
			String holidayPlaceHolder = "";
			String holiday = filteredList.get(i).getHoliday();
			if (holiday.equals("0")) {
				holidayPlaceHolder = "not";
			}
			
			String season = toSeason(Integer.parseInt(filteredList.get(i).getSeason()));
			String month = toMonth(Integer.parseInt(filteredList.get(i).getMonth()));
			String day = toWeekday((Integer.parseInt(filteredList.get(i).getDay())));
			String totalCyclists = String.valueOf(filteredList.get(i).getCyclists());
			String weather = toWeather(Integer.parseInt(filteredList.get(i).getWeather()));
			//System.out.println(toSeason(Integer.parseInt(record.getSeason())));
			System.out.println("The " + placeholder + " highest recorded number of cyclists was in "+
			season + ", on a " + day + " in the month of " + month + ".");
			System.out.println("There was a total of " + totalCyclists + " cyclists. The weather was "
			+ weather + ". " );
			System.out.println(day + " was "
			+ holidayPlaceHolder + " a holiday" );
			System.out.println();
		}
		
		
	}
}
