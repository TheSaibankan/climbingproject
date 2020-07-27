package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.Route;

import java.util.Set;

public interface RouteService extends CrudService<Route, Long> {
    void deleteAllBySectorId(Long sectorId);

    void addNewRoute(Long sectorId, Route route);
}
