package com.oc6ad.climbingproject.repositories;

import com.oc6ad.climbingproject.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
}
