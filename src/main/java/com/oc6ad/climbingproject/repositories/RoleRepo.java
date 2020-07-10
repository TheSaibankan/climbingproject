package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
}
