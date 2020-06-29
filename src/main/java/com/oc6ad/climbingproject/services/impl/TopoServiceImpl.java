package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.TopoService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TopoServiceImpl extends AbstractService<Topo, Long> implements TopoService {

    private final TopoRepo topoRepo;

    public TopoServiceImpl(TopoRepo topoRepo) {
        this.topoRepo = topoRepo;
    }

    @Override
    protected CrudRepository<Topo, Long> getRepository() {
        return this.topoRepo;
    }
}
