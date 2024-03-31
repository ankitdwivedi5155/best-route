package src.main.java.com.example.bestroute.model;

public class DeliveryExecutive {
    public String getName() {
        return name;
    }

    public GeoLocation getCurrentLocation() {
        return currentLocation;
    }

    private final String name;
    private final GeoLocation currentLocation;

    public DeliveryExecutive(String name, GeoLocation currentLocation) {
        this.name = name;
        this.currentLocation = currentLocation;
    }
}
