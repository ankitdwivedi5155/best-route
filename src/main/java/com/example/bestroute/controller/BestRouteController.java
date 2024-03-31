package src.main.java.com.example.bestroute.controller;

import src.main.java.com.example.bestroute.model.GeoLocation;
import src.main.java.com.example.bestroute.model.Restaurant;
import src.main.java.com.example.bestroute.model.Customer;
import src.main.java.com.example.bestroute.model.DeliveryExecutive;
import src.main.java.com.example.bestroute.service.BestRouteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestRouteController {

    private final BestRouteService bestRouteService;

    public BestRouteController(){
        bestRouteService = new BestRouteService();
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        // Input customers
        List<Customer> customers = new ArrayList<>();
        System.out.println("Enter number of customers:");
        int numCustomers = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 1; i <= numCustomers; i++) {
            System.out.println("Enter name of customer " + i + ":");
            String name = scanner.nextLine();
            System.out.println("Enter latitude and longitude of customer " + i + " (separated by space):");
            String[] locationInput = scanner.nextLine().split(" ");
            double latitude = Double.parseDouble(locationInput[0]);
            double longitude = Double.parseDouble(locationInput[1]);
            customers.add(new Customer(name, new GeoLocation(name, latitude, longitude)));
        }

        // Input restaurants
        List<Restaurant> restaurants = new ArrayList<>();
        System.out.println("Enter number of restaurants:");
        int numRestaurants = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 1; i <= numRestaurants; i++) {
            System.out.println("Enter name of restaurant " + i + ":");
            String name = scanner.nextLine();
            System.out.println("Enter latitude and longitude of restaurant " + i + " (separated by space):");
            String[] locationInput = scanner.nextLine().split(" ");
            double latitude = Double.parseDouble(locationInput[0]);
            double longitude = Double.parseDouble(locationInput[1]);
            System.out.println("Enter preparation time of restaurant " + i + " (in minutes):");
            int preparationTime = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            restaurants.add(new Restaurant(name, new GeoLocation(name, latitude, longitude), preparationTime));
        }

        // Input delivery executive
        System.out.println("Enter name of delivery executive:");
        String executiveName = scanner.nextLine();
        System.out.println("Enter latitude and longitude of delivery executive (separated by space):");
        String[] locationInput = scanner.nextLine().split(" ");
        double latitude = Double.parseDouble(locationInput[0]);
        double longitude = Double.parseDouble(locationInput[1]);
        DeliveryExecutive executive = new DeliveryExecutive(executiveName, new GeoLocation(executiveName, latitude, longitude));

        List<Restaurant> optimalRoute  = bestRouteService.findOptimalRoute(executive, restaurants, customers);


        // Display optimal route
        System.out.println("Optimal route for " + executive.getName() + ":");
        for (Restaurant restaurant : optimalRoute) {
            System.out.println("Deliver to " + restaurant.getName() + " (Preparation Time: " + restaurant.getPreparationTime() + " minutes)");
        }

        scanner.close();
    }
}
