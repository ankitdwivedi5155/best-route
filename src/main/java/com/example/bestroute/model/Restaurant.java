package src.main.java.com.example.bestroute.model;

public class Restaurant {
    private final String name;
    private final GeoLocation location;

    private final int preparationTime;

    public int getPreparationTime() {
        return preparationTime;
    }

    public Restaurant(String name, GeoLocation location, int preparationTime) {
        this.name = name;
        this.location = location;
        this.preparationTime = preparationTime;
    }

    public String getName() {
        return name;
    }

    public GeoLocation getLocation() {
        return location;
    }
}
