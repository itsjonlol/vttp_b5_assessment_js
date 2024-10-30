package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public List<Record> listRecord;
    public List<Record> filteredList;
    
    public void readCSV(String fileName) throws IOException {
        Reader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader);
        
        br.readLine(); // to omit the first row i.e. the header
        String line = "";
        listRecord  = new ArrayList<>(); // to store a list of individual entry records
        while ((line = br.readLine())!= null) {
        
            String[] tokens = line.split(","); // split the columns by comma
            int casual = Integer.parseInt(tokens[8]);
            int registered = Integer.parseInt(tokens[9]);
            String season = tokens[0];
            String month = tokens[1];
            String holiday = tokens[2];
            String weekday = tokens[3];
            String weather = tokens[4];
            Record record = new Record();

            int totalCyclist = casual + registered; // find the total number of cyclists
            
            record.setSeason(season);
            record.setMonth(month);
            record.setHoliday(holiday);
            record.setDay(weekday);
            record.setWeather(weather);
            record.setCyclists(totalCyclist);

            listRecord.add(record);

            
        }
        


        filteredList = listRecord.stream()
        .sorted(Comparator.comparing(Record::getCyclists,Comparator.reverseOrder()))
        .limit(5)
        .collect(Collectors.toList()); // get a filtered list in descending number of total number of cyclists
            // top 5 list
        
        
        


    }

    public List<Record> getListRecord() {
        return listRecord;
    }

    public List<Record> getFilteredList() {
        return filteredList;
    }
    
    
}
