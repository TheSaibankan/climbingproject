package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.TopoService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TopoServiceImpl extends AbstractService<Topo, Long> implements TopoService {

    private final TopoRepo topoRepo;
    private final UserAccountService userAccountService;

    public TopoServiceImpl(TopoRepo topoRepo, UserAccountService userAccountService) {
        this.topoRepo = topoRepo;
        this.userAccountService = userAccountService;
    }

    @Override
    public void addNewTopo(Topo topo){
        topo.setOwnerId(userAccountService.getCurrentUserAccount().getId());
        save(topo);
    }

    @Override
    public Set<Topo> getToposByCurrentUser(){
      Long idCurrentUser = userAccountService.getCurrentUserAccount().getId();
      return topoRepo.findByOwnerId(idCurrentUser);
    }

    @Override
    protected CrudRepository<Topo, Long> getRepository() {
        return this.topoRepo;
    }
}
