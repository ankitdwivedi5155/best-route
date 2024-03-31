package src.main.java.com.example.bestroute.model;

public class GeoLocation {
    private final String name;
    private final double latitude;
    private final double longitude;

    public GeoLocation(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
