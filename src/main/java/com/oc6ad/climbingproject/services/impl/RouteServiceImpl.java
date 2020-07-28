package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Route;
import com.oc6ad.climbingproject.repositories.RouteRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.RouteService;
import com.oc6ad.climbingproject.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RouteServiceImpl extends AbstractService<Route, Long> implements RouteService {

    private final RouteRepo routeRepo;

    public RouteServiceImpl(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    @Autowired
    private SectorService sectorService;

    @Override
    public void deleteAllBySectorId(Long sectorId){
        routeRepo.deleteAllBySector_Id(sectorId);
    }

    @Override
    public void deleteAllBySectorSpotId(Long spotId){
        routeRepo.deleteAllBySector_ClimbingSpot_Id(spotId);
    }

    @Override
    public void addNewRoute(Long sectorId, Route route){
        route.setSector(sectorService.findById(sectorId).get());
        save(route);
    }

    @Override
    public ClimbingSpot retrieveClimbingSpot(Long routeId){
        return routeRepo.findById(routeId).get().getSector().getClimbingSpot();
    }

    @Override
    protected CrudRepository<Route, Long> getRepository() {
        return this.routeRepo;
    }
}
