package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import org.springframework.data.repository.CrudRepository;

public class ClimbingSpotServiceImpl extends AbstractService<ClimbingSpot, Long> implements ClimbingSpotService {

    private final ClimbingSpotRepo climbingSpotRepo;

    public ClimbingSpotServiceImpl(ClimbingSpotRepo climbingSpotRepo) {
        this.climbingSpotRepo = climbingSpotRepo;
    }

    @Override
    protected CrudRepository<ClimbingSpot, Long> getRepository() {
        return this.climbingSpotRepo;
    }
}
