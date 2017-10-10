package pl.teamjava.hotel.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class WeatherService1 {
    private static WeatherService1 ourInstance = new WeatherService1();

    public static WeatherService1 getService() {
        return ourInstance;
    }

    private List<iWeatherObserver> observer = new ArrayList<>();
    private ExecutorService executorService;


    private WeatherService1() {
    }
}
