package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Sector;
import com.oc6ad.climbingproject.repositories.SectorRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import com.oc6ad.climbingproject.services.RouteService;
import com.oc6ad.climbingproject.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class SectorServiceImpl extends AbstractService<Sector, Long> implements SectorService {

    private final SectorRepo sectorRepo;

    public SectorServiceImpl(SectorRepo sectorRepo) {
        this.sectorRepo = sectorRepo;
    }

    @Autowired
    private ClimbingSpotService climbingSpotService;
    @Autowired
    private RouteService routeService;

    /**
     * Add and save a new sector
     * @param spotId
     * @param sector
     */
    @Override
    public void addNewSector(Long spotId, Sector sector){
        sector.setClimbingSpot(climbingSpotService.findById(spotId).get());
        save(sector);
    }

    /**
     * Update a sector
     * @param spotId
     * @param sectorId
     * @param sector
     */
    @Override
    public void updateSector(Long spotId, Long sectorId, Sector sector){
        sector.setId(sectorId);
        sector.setClimbingSpot(climbingSpotService.findById(spotId).get());
        sector.setRoutes(routeService.findAllRoutesBySectorId(sectorId));
        save(sector);
    }

    /**
     * Delete routes linked to a certain sector, then delete the sector
     * @param sectorId
     */
    @Override
    public void deleteAllBySectorId(Long sectorId){
        routeService.deleteAllBySectorId(sectorId);
        sectorRepo.deleteById(sectorId);
    }

    /**
     * Delete all sectors linked to a certain spot
     * @param spotId
     */
    @Override
    public void deleteAllBySpotId(Long spotId){
        sectorRepo.deleteAllByClimbingSpot_Id(spotId);
    }

    /**
     * Retrieve a spot via the sector's ID
     * @param sectorId
     * @return ClimbingSpot
     */
    @Override
    public ClimbingSpot retrieveClimbingSpot(Long sectorId){
        return sectorRepo.findById(sectorId).get().getClimbingSpot();
    }

    /**
     * Find all sectors via the spot's ID
     * @param id
     * @return Set<Sector>
     */
    @Override
    public Set<Sector> findAllByClimbingSpotId(Long id){
        return sectorRepo.findAllByClimbingSpot_Id(id);
    }


    @Override
    protected CrudRepository<Sector, Long> getRepository() {
        return sectorRepo;
    }
}
