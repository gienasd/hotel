package pl.teamjava.hotel.models.services;

import javafx.application.Platform;
import org.json.JSONObject;
import pl.teamjava.hotel.models.Config;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.WeatherInfo;
import pl.teamjava.hotel.models.dao.WeatherDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService instance = new WeatherService();

    public static WeatherService getService(){
        return instance;
    }

    //private List<IWeatherOberver> observer = new ArrayList<>();
    private ExecutorService executorService;
    private WeatherService(){
        executorService = Executors.newSingleThreadExecutor();
    }

    public void makeRequest(String city){
        Runnable runnable = () -> readJsonData(Utils.makeHttpRequest(Config.APP_BASE_URL + city + "&appid=" + Config.APP_ID),city);
        
        executorService.execute(runnable);
    }

    private void readJsonData(String json, String cityname) {
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");

        double temp = main.getDouble("temp");
        System.out.println("Temperatura: " + temp);

        int pressure = main.getInt("pressure");
        System.out.println("Ciśnienie: " + pressure);

        System.out.println("Widoczność: " + root.getInt("visibility"));

      //  observer.forEach(s -> {
      //      Platform.runLater(() -> s.onWeatherUpdate(new WeatherInfo(temp,pressure,cityname)));
      //  });
    }

   // public void registerObserver(iWeatherObserver observer){
  //      this.observer.add(observer);
   // }
}
