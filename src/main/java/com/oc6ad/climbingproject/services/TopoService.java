package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.Topo;

import java.util.Set;

public interface TopoService extends CrudService<Topo, Long>{
    void addNewTopo(Topo topo);

    Set<Topo> getToposByCurrentUser();
}
