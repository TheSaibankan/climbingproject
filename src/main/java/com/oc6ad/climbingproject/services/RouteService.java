package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Route;

import java.util.Set;

public interface RouteService extends CrudService<Route, Long> {
    void deleteAllBySectorId(Long sectorId);

    void deleteAllBySectorSpotId(Long spotId);

    void addNewRoute(Long sectorId, Route route);

    ClimbingSpot retrieveClimbingSpot(Long routeId);

    Set<Route> findAllRoutesBySectorId(Long id);
}
