package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Sector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SectorRepo extends CrudRepository<Sector, Long> {

    Set<Sector> findAllByClimbingSpot_Id(Long id);

    void deleteAllByClimbingSpot_Id(Long id);
}
