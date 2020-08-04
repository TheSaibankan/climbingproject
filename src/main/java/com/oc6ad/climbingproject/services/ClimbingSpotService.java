package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Sector;

import java.util.List;

public interface ClimbingSpotService extends CrudService<ClimbingSpot, Long> {


    Iterable<ClimbingSpot> findAllSpots();

    void addNewSpot(ClimbingSpot climbingSpot);

    void deleteSpot(Long spotId);

    List<ClimbingSpot> findBySearch(String search);

    void updateSpot(ClimbingSpot climbingSpot, Long spotId);
}
