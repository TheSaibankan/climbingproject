package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimbingSpotRepo extends CrudRepository<ClimbingSpot, Long> {

    @Query("select c from ClimbingSpot c " +
            "left join fetch c.comments com " +
            "left join fetch c.sectors s " +
            "where c.id = :id")
    ClimbingSpot findByUID(@Param("id") Long id);
}
