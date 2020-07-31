package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Sector;

public interface SectorService extends CrudService<Sector, Long>{

    void addNewSector(Long spotId, Sector sector);

    void updateSector(Long spotId, Long sectorId, Sector sector);

    void deleteAllBySectorId(Long sectorId);

    void deleteAllBySpotId(Long spotId);

    ClimbingSpot retrieveClimbingSpot(Long sectorId);
}
