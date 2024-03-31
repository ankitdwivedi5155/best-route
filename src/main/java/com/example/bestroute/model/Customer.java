package src.main.java.com.example.bestroute.model;

public class Customer {
    private final String name;
    private final GeoLocation location;

    public Customer(String name, GeoLocation location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public GeoLocation getLocation() {
        return location;
    }
}
