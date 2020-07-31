package com.oc6ad.climbingproject.repositories;


import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopoRepo extends CrudRepository<Topo, Long> {
    /**
     * Used to retrieve all topos via the owner's ID
     * @param idCurrentUser
     * @return Set<Topo>
     */
    Set<Topo> findByOwnerId(Long idCurrentUser);

    /**
     * Displays only topos that can be exchanged
     * @return Set<Topo>
     */
    Set<Topo> findAllByIsAvailableTrue();
}
