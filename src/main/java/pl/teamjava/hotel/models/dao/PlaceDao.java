package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.PlaceModel;

import java.util.List;

public interface PlaceDao {
    List<String> getAllPlaceNames(PlaceModel model);
    List<String> getPlaceNames(PlaceModel model); //lista hoteli wg nazwy miasta
    List<String> getFreePlace(PlaceModel model); //lista hoteli wg wolnych miejsc na namiot
    List<String> getCheapApartment(PlaceModel model); //lista hoteli wg ceny apartamentu
    List<String> getCheapRoom(PlaceModel model); //lista hoteli wg ceny pokoju
    List<String> getOpinionHotel(); //lista hoteli wg oceny gości
    List<PlaceModel> gettAllPlaceData(PlaceModel model);
    List<String> getAnimalsPlace(PlaceModel model);
    List<String> getWiFiPlace(PlaceModel model);
    List<String> getSpaPlace(PlaceModel model);
    List<String> getAquaparkHotels();
    List<String> getPoolPlace(PlaceModel model);
    String getCityName(PlaceModel model);
    String getRegionName(PlaceModel model);
}