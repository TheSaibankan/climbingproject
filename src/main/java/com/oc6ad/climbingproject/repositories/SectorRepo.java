package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepo extends CrudRepository<Sector, Long> {
}
