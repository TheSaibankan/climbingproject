package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimbingSpotRepo extends CrudRepository<ClimbingSpot, Long> {

    /**
     * Custom request to minimize performance issues with database
     * @param id
     * @return
     */
    @Query("select c from ClimbingSpot c " +
            "left join fetch c.comments com " +
            "left join fetch c.sectors s " +
            "where c.id = :id")
    ClimbingSpot findByUID(@Param("id") Long id);

    /**
     * Search must be lowercase
     * @param search
     * @return
     */
    @Query(value="SELECT * FROM climbing_spot c WHERE lower(c.name) LIKE %:search% " +
            "OR lower(c.location) LIKE %:search% " +
            "OR lower(c.cotation) LIKE %:search%", nativeQuery=true)
    List<ClimbingSpot> findBySearch(@Param("search") String search);
}
