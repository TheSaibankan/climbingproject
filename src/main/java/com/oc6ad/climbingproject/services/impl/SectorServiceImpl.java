package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Sector;
import com.oc6ad.climbingproject.repositories.SectorRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.SectorService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImpl extends AbstractService<Sector, Long> implements SectorService {

    private final SectorRepo sectorRepo;

    public SectorServiceImpl(SectorRepo sectorRepo) {
        this.sectorRepo = sectorRepo;
    }

    @Override
    protected CrudRepository<Sector, Long> getRepository() {
        return sectorRepo;
    }
}
