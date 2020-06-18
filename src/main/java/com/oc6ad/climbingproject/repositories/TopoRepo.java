package com.oc6ad.climbingproject.repositories;


import com.oc6ad.climbingproject.model.Topo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoRepo extends CrudRepository<Topo, Long> {
}
