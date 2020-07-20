package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Comment;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.repositories.CommentRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.CommentService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractService<Comment, Long> implements CommentService {

    private final CommentRepo commentRepo;
    private final UserAccountService userAccountService;
    private final ClimbingSpotRepo climbingSpotRepo;

    public CommentServiceImpl(CommentRepo commentRepo, UserAccountService userAccountService, ClimbingSpotRepo climbingSpotRepo) {
        this.commentRepo = commentRepo;
        this.userAccountService = userAccountService;
        this.climbingSpotRepo = climbingSpotRepo;
    }

    @Override
    public void addNewComment(Long idClimbingSpot, Comment comment){
        comment.setUserAccount(userAccountService.getCurrentUserAccount());
        comment.setClimbingSpot(climbingSpotRepo.findById(idClimbingSpot).get());
        save(comment);
    }

    @Override
    public boolean isCommentDeletable(Long idComment){
        if (userAccountService.isUserConnected()){
            UserAccount currentUser = userAccountService.getCurrentUserAccount();
            return currentUser.isAdmin() || commentRepo.findById(idComment).get().getUserAccount() == currentUser;
        } else {
            return false;
        }
    }

    @Override
    public CrudRepository<Comment, Long> getRepository() {
        return this.commentRepo;
    }
}
