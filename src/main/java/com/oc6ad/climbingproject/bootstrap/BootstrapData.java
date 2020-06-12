package com.oc6ad.climbingproject.bootstrap;


import com.oc6ad.climbingproject.model.ClimbingSite;
import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.ClimbingSiteRepo;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserAccountRepo userAccountRepo;
    private final TopoRepo topoRepo;
    private final ClimbingSiteRepo climbingSiteRepo;

    public BootstrapData(UserAccountRepo userAccountRepo, TopoRepo topoRepo, ClimbingSiteRepo climbingSiteRepo) {
        this.userAccountRepo = userAccountRepo;
        this.topoRepo = topoRepo;
        this.climbingSiteRepo = climbingSiteRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        UserAccount antoine = new UserAccount("Antoine", "Domergue",
                "adomergue@hotmail.fr", "AdminLog", "12345", true);

        UserAccount sebastien = new UserAccount("Sébastien", "Luna", "sebastionluna@gmail.com", "lunluna", "6789", false);

        Topo everest = new Topo("Best Climbing Techniques",
                "The best climbing methods you can find !", "Arizona, USA"
        ,"28/02/2008", true);

        ClimbingSite climbingExample = new ClimbingSite();
        climbingExample.setName("Example");
        climbingExample.setLocation("TownExample");
        climbingExample.setOfficial(true);
        climbingExample.setPopularity(5.0);

        ClimbingSite climbingExample2 = new ClimbingSite();
        climbingExample2.setName("Example2");
        climbingExample2.setLocation("TownExample2");
        climbingExample2.setOfficial(true);
        climbingExample2.setPopularity(5.0);

        userAccountRepo.save(antoine);
        userAccountRepo.save(sebastien);
        topoRepo.save(everest);
        climbingSiteRepo.save(climbingExample);
        climbingSiteRepo.save(climbingExample2);

        antoine.getTopos().add(everest);
        everest.getUserAccounts().add(antoine);

        climbingExample.setUserAccount(antoine);
        antoine.getClimbingSites().add(climbingExample);

        climbingExample2.setUserAccount(antoine);
        antoine.getClimbingSites().add(climbingExample2);

        userAccountRepo.save(antoine);
        userAccountRepo.save(sebastien);
        topoRepo.save(everest);
        climbingSiteRepo.save(climbingExample);
        climbingSiteRepo.save(climbingExample2);

        System.out.println("--- Statistiques et état de la base de données ---");
        System.out.println("Nombre de topos : " + topoRepo.count());
        System.out.println("Nombre d'utilisateurs : " + userAccountRepo.count());
        System.out.println("Nombre de sites d'escalade : " + climbingSiteRepo.count());
        System.out.println("Nombre de sites créés par l'admin : " + antoine.getClimbingSites().size());

    }
}
