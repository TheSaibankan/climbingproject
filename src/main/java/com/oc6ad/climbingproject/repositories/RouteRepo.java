package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RouteRepo extends CrudRepository<Route, Long> {
    /**
     * Is used to delete all routes based on the spot's ID
     * @param id
     */
    void deleteAllBySector_ClimbingSpot_Id(Long id);

    /**
     * Is used to delete all route based on the sector's ID
     * @param id
     */
    void deleteAllBySector_Id(Long id);
}
