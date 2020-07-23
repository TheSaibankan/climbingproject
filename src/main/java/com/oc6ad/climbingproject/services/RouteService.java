package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.Route;

import java.util.Set;

public interface RouteService extends CrudService<Route, Long> {
    Set<Route> findAllBySectorClimbingSpotId(Long id);
}
