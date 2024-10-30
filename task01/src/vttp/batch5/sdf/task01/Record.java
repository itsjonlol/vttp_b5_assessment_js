package vttp.batch5.sdf.task01;

public class Record {
    private String season;
    private String month;
    private String holiday;
    private String day;
    private String weather;
    private int cyclists;

    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getHoliday() {
        return holiday;
    }
    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public int getCyclists() {
        return cyclists;
    }
    public void setCyclists(int cyclists) {
        this.cyclists = cyclists;
    }
    @Override
    public String toString() {
        return "Record [season=" + season + ", month=" + month + ", holiday=" + holiday + ", day=" + day + ", weather="
                + weather + ", cyclists=" + cyclists + "]";
    }

    

    
    


    
    
}
