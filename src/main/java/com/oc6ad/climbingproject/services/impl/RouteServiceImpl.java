package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Route;
import com.oc6ad.climbingproject.repositories.RouteRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.RouteService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class RouteServiceImpl extends AbstractService<Route, Long> implements RouteService {

    private final RouteRepo routeRepo;

    public RouteServiceImpl(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    @Override
    public Set<Route> findAllBySectorClimbingSpotId(Long id){
        return routeRepo.findAllBySector_ClimbingSpot_Id(id);
    }

    @Override
    protected CrudRepository<Route, Long> getRepository() {
        return this.routeRepo;
    }
}
