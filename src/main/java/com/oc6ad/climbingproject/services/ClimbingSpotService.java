package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.ClimbingSpot;

public interface ClimbingSpotService extends CrudService<ClimbingSpot, Long> {


    Iterable<ClimbingSpot> findAllSpots();

    void addNewSpot(ClimbingSpot climbingSpot);
}
