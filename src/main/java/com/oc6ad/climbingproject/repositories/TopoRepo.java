package com.oc6ad.climbingproject.repositories;


import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopoRepo extends CrudRepository<Topo, Long> {
    Set<Topo> findByOwnerId(Long idCurrentUser);
    Set<Topo> findAllByIsAvailableTrue();
}
