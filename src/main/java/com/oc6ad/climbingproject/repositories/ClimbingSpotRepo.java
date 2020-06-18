package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimbingSpotRepo extends CrudRepository<ClimbingSpot, Long> {
}
