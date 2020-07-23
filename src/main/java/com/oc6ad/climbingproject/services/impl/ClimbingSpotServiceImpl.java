package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ClimbingSpotServiceImpl extends AbstractService<ClimbingSpot, Long> implements ClimbingSpotService {

    private final ClimbingSpotRepo climbingSpotRepo;
    private final UserAccountService userAccountService;

    public ClimbingSpotServiceImpl(ClimbingSpotRepo climbingSpotRepo, UserAccountService userAccountService) {
        this.climbingSpotRepo = climbingSpotRepo;
        this.userAccountService = userAccountService;
    }

    @Override
    public Iterable<ClimbingSpot> findAllSpots(){
        return climbingSpotRepo.findAll();
    }

    @Override
    public void addNewSpot(ClimbingSpot climbingSpot){
        climbingSpot.setUserAccount(userAccountService.getCurrentUserAccount());
        save(climbingSpot);
    }

    @Override
    public Optional<ClimbingSpot> findById(Long var1) {
        return Optional.ofNullable(climbingSpotRepo.findByUID(var1));
    }

    @Override
    protected CrudRepository<ClimbingSpot, Long> getRepository() {
        return this.climbingSpotRepo;
    }
}
