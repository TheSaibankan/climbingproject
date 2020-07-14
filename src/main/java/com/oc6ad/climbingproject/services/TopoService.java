package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;

import java.util.Set;

public interface TopoService extends CrudService<Topo, Long>{
    void addNewTopo(Topo topo);

    Set<Topo> getToposByCurrentUser();

    void askForTopo(Long id);

    void declineRequestTopo(Long id);

    void acceptRequestTopo(Long id);

    UserAccount getUserRequesting(Long id);
}
