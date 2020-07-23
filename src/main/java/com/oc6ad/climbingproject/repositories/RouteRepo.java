package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RouteRepo extends CrudRepository<Route, Long> {
    Set<Route> findAllBySector_ClimbingSpot_Id(Long id);
}
