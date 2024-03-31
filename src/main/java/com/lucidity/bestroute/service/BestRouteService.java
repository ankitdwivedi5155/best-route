package src.main.java.com.lucidity.bestroute.service;

import src.main.java.com.lucidity.bestroute.model.*;

import java.util.ArrayList;
import java.util.List;

public class BestRouteService {

    public BestRouteService(){

    }
    private static final double AVG_SPEED_KMH = 20;

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int earthRadius = 6371; // Radius of the Earth in kilometers
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    private  double calculateTravelTime(double distanceKm) {
        double timeHours = distanceKm / AVG_SPEED_KMH;
        return timeHours * 60; // Convert hours to minutes
    }

    private double calculateDeliveryTime(DeliveryExecutive executive, Restaurant restaurant, GeoLocation customerLocation) {
        double travelDistance = haversineDistance(executive.getCurrentLocation().getLatitude(), executive.getCurrentLocation().getLongitude(),
                restaurant.getLocation().getLatitude(), restaurant.getLocation().getLongitude());
        double travelTime = calculateTravelTime(travelDistance);
        double preparationTime = restaurant.getPreparationTime();
        double deliveryDistance = haversineDistance(restaurant.getLocation().getLatitude(), restaurant.getLocation().getLongitude(),
                customerLocation.getLatitude(), customerLocation.getLongitude());
        double deliveryTime = calculateTravelTime(deliveryDistance);
        return travelTime + preparationTime + deliveryTime;
    }


    public List<Restaurant> findOptimalRoute(DeliveryExecutive executive, List<Restaurant> restaurants, List<Customer> customers) {
        List<Restaurant> optimalRoute = new ArrayList<>();
        double minTotalDeliveryTime = Double.MAX_VALUE;

        for (Restaurant startRestaurant : restaurants) {
            List<Restaurant> currentRoute = new ArrayList<>();
            currentRoute.add(startRestaurant);
            double totalDeliveryTime = 0;

            for (Customer customer : customers) {
                double minDeliveryTime = Double.MAX_VALUE;
                Restaurant nextRestaurant = null;

                for (Restaurant restaurant : restaurants) {
                    if (!currentRoute.contains(restaurant)) {
                        double deliveryTime = calculateDeliveryTime(executive, restaurant, customer.getLocation());
                        if (deliveryTime < minDeliveryTime) {
                            minDeliveryTime = deliveryTime;
                            nextRestaurant = restaurant;
                        }
                    }
                }

                if (nextRestaurant != null) {
                    currentRoute.add(nextRestaurant);
                    totalDeliveryTime += minDeliveryTime;
                }
            }

            if (totalDeliveryTime < minTotalDeliveryTime) {
                minTotalDeliveryTime = totalDeliveryTime;
                optimalRoute = currentRoute;
            }
        }

        return optimalRoute;
    }
}
