package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends CrudRepository<Route, Long> {
}
