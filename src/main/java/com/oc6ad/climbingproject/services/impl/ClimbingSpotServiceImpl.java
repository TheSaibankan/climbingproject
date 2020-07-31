package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    @Autowired
    private SectorService sectorService;
    @Autowired
    private RouteService routeService;

    /**
     * Returns all spots
     * @return Iterable<ClimbingSpot>
     */
    @Override
    public Iterable<ClimbingSpot> findAllSpots(){
        return climbingSpotRepo.findAll();
    }

    /**
     * Adds and save a new spot
     * @param climbingSpot
     */
    @Override
    public void addNewSpot(ClimbingSpot climbingSpot){
        climbingSpot.setUserAccount(userAccountService.getCurrentUserAccount());
        save(climbingSpot);
    }

    /**
     * Delete a spot via the ID
     * @param spotId
     */
    @Override
    public void deleteSpot(Long spotId){
        routeService.deleteAllBySectorSpotId(spotId);
        sectorService.deleteAllBySpotId(spotId);
        deleteById(spotId);
    }

    /**
     * Use the search bar to retrieve a spot via its name, location or quotation
     * @param search
     * @return List<ClimbingSpot>
     */
    @Override
    public List<ClimbingSpot> findBySearch(String search){
        return climbingSpotRepo.findBySearch(search.toLowerCase());
    }

    /**
     * findById method now uses custom request via findByUID
     * @param var1
     * @return Optional<ClimbingSpot>
     */
    @Override
    public Optional<ClimbingSpot> findById(Long var1) {
        return Optional.ofNullable(climbingSpotRepo.findByUID(var1));
    }

    /**
     * Grants access to CRUD methods without calling the repository
     * @return CrudRepository<ClimbingSpot, Long>
     */
    @Override
    protected CrudRepository<ClimbingSpot, Long> getRepository() {
        return this.climbingSpotRepo;
    }
}
