package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.PlaceModel;

import java.util.List;

public interface PlaceDao {
    List<String> getAllPlaceNames(PlaceModel model);
    List<String> getPlaceNames(PlaceModel model);
    List<String> getFreePlace(PlaceModel model);
    List<String> getCheapApartment(PlaceModel model);
    List<String> getCheapRoom(PlaceModel model);
    List<PlaceModel> gettAllPlaceData(PlaceModel model);
    List<String> getAnimalsPlace(PlaceModel model);
    List<String> getWiFiPlace(PlaceModel model);
    List<String> getSpaPlace(PlaceModel model);
    List<String> getPoolPlace(PlaceModel model);
    String getCityName(PlaceModel model);
    String getRegionName(PlaceModel model);
}