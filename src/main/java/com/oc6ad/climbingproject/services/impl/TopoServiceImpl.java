package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.TopoService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TopoServiceImpl extends AbstractService<Topo, Long> implements TopoService {

    private final TopoRepo topoRepo;
    private final UserAccountService userAccountService;
    private final UserAccountRepo userAccountRepo;

    public TopoServiceImpl(TopoRepo topoRepo, UserAccountService userAccountService, UserAccountRepo userAccountRepo) {
        this.topoRepo = topoRepo;
        this.userAccountService = userAccountService;
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public void addNewTopo(Topo topo){
        topo.setOwnerId(userAccountService.getCurrentUserAccount().getId());
        topo.setReceiverId(Long.valueOf(0));
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

    @Override
    public void askForTopo(Long id) {
        Topo topo = topoRepo.findById(id).get();
        UserAccount currentUser = userAccountService.getCurrentUserAccount();
        topo.setReceiverId(currentUser.getId());
        topo.setHasBeenRequested(true);
        topoRepo.save(topo);
    }

    @Override
    public void declineRequestTopo(Long id) {
        Topo topo = topoRepo.findById(id).get();
        topo.setReceiverId(Long.valueOf(0));
        topo.setHasBeenRequested(false);
        topoRepo.save(topo);
    }

    @Override
    public void acceptRequestTopo(Long id) {
       Topo topo = topoRepo.findById(id).get();
       topo.setAvailable(false);
       topoRepo.save(topo);
    }

    @Override
    public UserAccount getUserRequesting(Long id) {
        return userAccountRepo.findById(topoRepo.findById(id).get().getReceiverId()).get();
    }
}
