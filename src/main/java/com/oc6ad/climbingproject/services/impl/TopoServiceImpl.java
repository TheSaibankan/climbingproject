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

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class TopoServiceImpl extends AbstractService<Topo, Long> implements TopoService {

    private final TopoRepo topoRepo;
    private final UserAccountService userAccountService;

    public TopoServiceImpl(TopoRepo topoRepo, UserAccountService userAccountService) {
        this.topoRepo = topoRepo;
        this.userAccountService = userAccountService;
    }

    /**
     * Add and save new topo
     * @param topo
     */
    @Override
    public void addNewTopo(Topo topo){
        topo.setOwner(userAccountService.getCurrentUserAccount());
        topo.setReceiver(null);
        save(topo);
    }

    /**
     * Return all topos via the owner's ID
     * @return Set<Topo>
     */
    @Override
    public Set<Topo> getToposByCurrentUser(){
      Long idCurrentUser = userAccountService.getCurrentUserAccount().getId();
      return topoRepo.findByOwnerId(idCurrentUser);
    }

    /**
     * Send a request to the topo's owner
     * @param id
     */
    @Override
    public void askForTopo(Long id) {
        Topo topo = topoRepo.findById(id).get();
        UserAccount currentUser = userAccountService.getCurrentUserAccount();
        topo.setReceiver(currentUser);
        topo.setHasBeenRequested(true);
        topoRepo.save(topo);
    }

    /**
     * Decline the request and reset topo's parameters
     * @param id
     */
    @Override
    public void declineRequestTopo(Long id) {
        Topo topo = topoRepo.findById(id).get();
        topo.setReceiver(null);
        topo.setHasBeenRequested(false);
        topoRepo.save(topo);
    }

    /**
     * Accept the request and set the topo as unavailable
     * @param id
     */
    @Override
    public void acceptRequestTopo(Long id) {
       Topo topo = topoRepo.findById(id).get();
       topo.setAvailable(false);
       topo.setHasBeenRequested(false);
       topo.setHasBeenAccepted(true);
       topoRepo.save(topo);
    }

    /**
     * Return the user who made the request
     * @param id
     * @return UserAccount
     */
    @Override
    public UserAccount getUserRequesting(Long id) {
        return topoRepo.findById(id).get().getReceiver();
    }

    /**
     * Indicate if a certain topo can be requested
     * @param id
     * @return boolean
     */
    @Override
    public boolean canBeRequested(Long id) {
        Topo topo = topoRepo.findById(id).get();
        UserAccount currentUser = userAccountService.getCurrentUserAccount();
        return topo.getReceiver() != currentUser && topo.getOwner() != currentUser;
    }

    @Override
    protected CrudRepository<Topo, Long> getRepository() {
        return this.topoRepo;
    }
}
