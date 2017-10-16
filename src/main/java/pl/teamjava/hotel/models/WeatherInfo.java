package pl.teamjava.hotel.models;

public class WeatherInfo {
    private double temp;
    private int pressure;
    private String city;

    public WeatherInfo(double temp, int pressure, String city) {
        this.temp = temp;
        this.pressure = pressure;
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getCity() {
        return city;
    }

    public void setCityname(String city) {
        this.city = city;
    }

}
