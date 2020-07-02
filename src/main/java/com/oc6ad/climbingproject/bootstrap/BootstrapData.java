package com.oc6ad.climbingproject.bootstrap;


import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserAccountRepo userAccountRepo;
    private final TopoRepo topoRepo;
    private final ClimbingSpotRepo climbingSpotRepo;

    public BootstrapData(UserAccountRepo userAccountRepo, TopoRepo topoRepo, ClimbingSpotRepo climbingSpotRepo) {
        this.userAccountRepo = userAccountRepo;
        this.topoRepo = topoRepo;
        this.climbingSpotRepo = climbingSpotRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("--- Statistiques et état de la base de données ---");
        System.out.println("Nombre de topos : " + topoRepo.count());
        System.out.println("Nombre d'utilisateurs : " + userAccountRepo.count());
        System.out.println("Nombre de sites d'escalade : " + climbingSpotRepo.count());



    }
}
