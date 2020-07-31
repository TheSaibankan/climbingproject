package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Sector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SectorRepo extends CrudRepository<Sector, Long> {

    /**
     * Is used to delete all sectors linked to a certain spot
     * @param id
     */
    void deleteAllByClimbingSpot_Id(Long id);
}
