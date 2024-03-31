package src.main.java.com.lucidity.bestroute.service;

import src.main.java.com.lucidity.bestroute.model.Customer;
import src.main.java.com.lucidity.bestroute.model.DeliveryExecutive;
import src.main.java.com.lucidity.bestroute.model.GeoLocation;
import src.main.java.com.lucidity.bestroute.model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static src.main.java.com.lucidity.bestroute.service.BestRouteHelper.findOptimalRoute;

public class BestRouteService {

    public BestRouteService(){
    }

    public void findBestRoute() {
        // Defining geoLocations
        GeoLocation customer1Location = new GeoLocation("locationCustomer1", 12.9284, 77.6229);
        GeoLocation customer2Location = new GeoLocation("locationCustomer2", 12.9336, 77.6177);
        GeoLocation restaurant1Location = new GeoLocation("locationRestaurant1", 12.9279, 77.6271);
        GeoLocation restaurant2Location = new GeoLocation("locationRestaurant2", 12.9352, 77.6229);

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Customer1", customer1Location));
        customers.add(new Customer("Customer2", customer2Location));

        Map<String, Restaurant> restaurants = new HashMap<>();
        restaurants.put("Restaurant1", new Restaurant("Restaurant1", restaurant1Location, 10));
        restaurants.put("Restaurant2", new Restaurant("Restaurant1", restaurant2Location, 12));

        DeliveryExecutive aman = new DeliveryExecutive("Aman", new GeoLocation("Idle", 12.9295, 77.6309));

        List<Restaurant> optimalRoute = findOptimalRoute(aman, new ArrayList<>(restaurants.values()), customers);

        System.out.println("Optimal route for Aman:");
        for (Restaurant restaurant : optimalRoute) {
            System.out.println("Deliver to " + restaurant.getName() + " (Preparation Time: " + restaurant.getPreparationTime() + " minutes)");
        }
    }

}

