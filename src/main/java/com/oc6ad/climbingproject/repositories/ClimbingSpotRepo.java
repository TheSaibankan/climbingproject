package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimbingSpotRepo extends CrudRepository<ClimbingSpot, Long> {

    @Query("select c from ClimbingSpot c " +
            "left join fetch c.comments com " +
            "left join fetch c.sectors s " +
            "where c.id = :id")
    ClimbingSpot findByUID(@Param("id") Long id);


    @Query(value="SELECT * FROM climbing_spot c WHERE c.name LIKE %:search% " +
            "OR c.location LIKE %:search% " +
            "OR c.cotation LIKE %:search%", nativeQuery=true)
    List<ClimbingSpot> findBySearch(@Param("search") String search);
}
