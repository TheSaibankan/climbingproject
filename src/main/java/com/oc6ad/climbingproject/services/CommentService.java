package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.Comment;

import java.util.Set;

public interface CommentService extends CrudService<Comment, Long> {

    void addNewComment(Long idClimbingSpot, Comment comment);
}
