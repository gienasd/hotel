package pl.teamjava.hotel.models;

import java.sql.Date;

public class WeatherModel {

    private String city;
    private float temp;
    private Date date;

    public WeatherModel(String city, float temp, Date date) {
        this.city = city;
        this.temp = temp;
        this.date = date;
    }

    public WeatherModel(WeatherInfo info){
        this.city = info.getCity();
        this.temp = (float) info.getTemp();
        this.date = new Date(0);
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
