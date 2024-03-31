package src.main.java.com.lucidity.bestroute.model;

public class Order {
    private final GeoLocation consumer;
    private final Restaurant restaurant;

    public int getPreparationTime() {
        return preparationTime;
    }

    private final int preparationTime;

    public Order(GeoLocation consumer, Restaurant restaurant, int preparationTime) {
        this.consumer = consumer;
        this.restaurant = restaurant;
        this.preparationTime = preparationTime;
    }

    public GeoLocation getConsumer() {
        return consumer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
