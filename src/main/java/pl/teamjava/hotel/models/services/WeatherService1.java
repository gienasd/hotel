package pl.teamjava.hotel.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import org.json.JSONObject;
import pl.teamjava.hotel.models.*;

public class WeatherService1 {
    private static WeatherService1 ourInstance = new WeatherService1();

    public static WeatherService1 getService() {
        return ourInstance;
    }

    private List<IWeatherOberver> observer = new ArrayList<>();
    private ExecutorService executorService;


    private WeatherService1() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public void makeRequest(String city){
        Runnable runnable = () -> readJsonData(Utils.makeHttpRequest(Config.APP_BASE_URL + city + "&appid=" + Config.APP_ID),city);

        executorService.execute(runnable);
    }

    private void readJsonData(String json, String cityname) {
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");

        double temp = main.getDouble("temp") - (273.15);

        int pressure = main.getInt("pressure");


        observer.forEach(s -> {
              Platform.runLater(() -> s.onWeatherUpdate(new WeatherInfo(temp,pressure,cityname)));
          });
    }

     public void registerObserver(IWeatherOberver observer){
          this.observer.add(observer);
     }
}
