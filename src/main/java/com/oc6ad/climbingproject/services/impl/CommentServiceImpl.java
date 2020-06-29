package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Comment;
import com.oc6ad.climbingproject.repositories.CommentRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.CommentService;
import org.springframework.data.repository.CrudRepository;

public class CommentServiceImpl extends AbstractService<Comment, Long> implements CommentService {

    private final CommentRepo commentRepo;

    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    protected CrudRepository<Comment, Long> getRepository() {
        return this.commentRepo;
    }
}
