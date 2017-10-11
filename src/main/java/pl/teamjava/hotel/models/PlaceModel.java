package pl.teamjava.hotel.models;

public class PlaceModel {
    private String name;
    private String city;
    private String region;
    private String accessCode;
    private String category;
    private boolean isThereWiFi;
    private boolean isThereSwimmingPool;
    private boolean isThereSpa;
    private boolean canIHaveAPet;

    public PlaceModel(String name, String resultSetString, String setString) {
        this.name = name;
        this.city = city;
        this.region = region;
        this.accessCode = accessCode;
        this.category = category;
        this.isThereWiFi = isThereWiFi;
        this.isThereSwimmingPool = isThereSwimmingPool;
        this.isThereSpa = isThereSpa;
        this.canIHaveAPet = canIHaveAPet;
    }

    public PlaceModel(String name, String city, String region, String category, boolean isThereWiFi,
                      boolean isThereSwimmingPool, boolean isThereSpa, boolean canIHaveAPet) {
        this.name = name;
        this.city = city;
        this.region = region;
        this.category = category;
        this.isThereWiFi = isThereWiFi;
        this.isThereSwimmingPool = isThereSwimmingPool;
        this.isThereSpa = isThereSpa;
        this.canIHaveAPet = canIHaveAPet;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessCode() {
        return accessCode;
    }
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isThereWiFi() {
        return isThereWiFi;
    }
    public void setThereWiFi(boolean thereWiFi) {
        isThereWiFi = thereWiFi;
    }

    public boolean isThereSwimmingPool() {
        return isThereSwimmingPool;
    }
    public void setThereSwimmingPool(boolean thereSwimmingPool) {
        isThereSwimmingPool = thereSwimmingPool;
    }

    public boolean isThereSpa() {
        return isThereSpa;
    }
    public void setThereSpa(boolean thereSpa) {
        isThereSpa = thereSpa;
    }

    public boolean canIHaveAPet() {
        return canIHaveAPet;
    }
    public void setCanIHaveAPet(boolean canIHaveAPet) {
        this.canIHaveAPet = canIHaveAPet;
    }
}
