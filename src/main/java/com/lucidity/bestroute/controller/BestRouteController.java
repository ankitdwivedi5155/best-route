package src.main.java.com.lucidity.bestroute.controller;

import src.main.java.com.lucidity.bestroute.service.BestRouteService;

public class BestRouteController {

    private final BestRouteService bestRouteService;

    public BestRouteController(){
        bestRouteService = new BestRouteService();
    }

    public void start(){
        bestRouteService.findBestRoute();
    }
}
