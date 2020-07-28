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

    @Override
    public void addNewSector(Long spotId, Sector sector){
        sector.setClimbingSpot(climbingSpotService.findById(spotId).get());
        save(sector);
    }

    @Override
    public void updateSector(Long spotId, Long sectorId, Sector sector){
        sector.setId(sectorId);
        sector.setClimbingSpot(climbingSpotService.findById(spotId).get());
        save(sector);
    }

    @Override
    public void deleteAllBySectorId(Long spotId){
        routeService.deleteAllBySectorId(spotId);
        sectorRepo.deleteById(spotId);
    }

    @Override
    public void deleteAllBySpotId(Long spotId){
        sectorRepo.deleteAllByClimbingSpot_Id(spotId);
    }

    @Override
    public ClimbingSpot retrieveClimbingSpot(Long sectorId){
        return sectorRepo.findById(sectorId).get().getClimbingSpot();
    }


    @Override
    protected CrudRepository<Sector, Long> getRepository() {
        return sectorRepo;
    }
}
