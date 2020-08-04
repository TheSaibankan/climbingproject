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
import java.util.Set;

@Service
@Transactional
public class RouteServiceImpl extends AbstractService<Route, Long> implements RouteService {

    private final RouteRepo routeRepo;

    public RouteServiceImpl(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }
    @Autowired
    private SectorService sectorService;

    /**
     * Delete all routes via the sector's ID
     * @param sectorId
     */
    @Override
    public void deleteAllBySectorId(Long sectorId){
        routeRepo.deleteAllBySector_Id(sectorId);
    }

    /**
     * Delete all routes via the spot's ID
     * @param spotId
     */
    @Override
    public void deleteAllBySectorSpotId(Long spotId){
        routeRepo.deleteAllBySector_ClimbingSpot_Id(spotId);
    }

    /**
     * Add and save a new route
     * @param sectorId
     * @param route
     */
    @Override
    public void addNewRoute(Long sectorId, Route route){
        route.setSector(sectorService.findById(sectorId).get());
        save(route);
    }

    /**
     *Retrieve the spot via the route's ID
     * @param routeId
     * @return ClimbingSpot
     */
    @Override
    public ClimbingSpot retrieveClimbingSpot(Long routeId){
        return routeRepo.findById(routeId).get().getSector().getClimbingSpot();
    }

    /**
     * Retrieve all routes via the sector's ID
     * @param id
     * @return Set<Route>
     */
    @Override
    public Set<Route> findAllRoutesBySectorId(Long id){
        return routeRepo.findAllBySector_Id(id);
    }

    @Override
    protected CrudRepository<Route, Long> getRepository() {
        return this.routeRepo;
    }
}
