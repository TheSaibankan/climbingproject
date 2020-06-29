package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Route;
import com.oc6ad.climbingproject.repositories.RouteRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.RouteService;
import org.springframework.data.repository.CrudRepository;

public class RouteServiceImpl extends AbstractService<Route, Long> implements RouteService {

    private final RouteRepo routeRepo;

    public RouteServiceImpl(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    @Override
    protected CrudRepository<Route, Long> getRepository() {
        return this.routeRepo;
    }
}
