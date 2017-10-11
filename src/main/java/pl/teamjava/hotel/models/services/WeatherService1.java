package pl.teamjava.hotel.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import pl.teamjava.hotel.models.IWeatherOberver;

public class WeatherService1 {
    private static WeatherService1 ourInstance = new WeatherService1();

    public static WeatherService1 getService() {
        return ourInstance;
    }

    private List<IWeatherOberver> observer = new ArrayList<>();
    private ExecutorService executorService;


    private WeatherService1() {
    }
}
