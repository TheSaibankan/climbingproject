package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    Set<Comment> findAllByClimbingSpot_Id(Long idClimbingSpot);
}
