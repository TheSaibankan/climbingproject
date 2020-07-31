package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Comment;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.repositories.CommentRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import com.oc6ad.climbingproject.services.CommentService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment, Long> implements CommentService {

    private final CommentRepo commentRepo;
    private final UserAccountService userAccountService;
    private final ClimbingSpotService climbingSpotService;

    public CommentServiceImpl(CommentRepo commentRepo, UserAccountService userAccountService, ClimbingSpotService climbingSpotService) {
        this.commentRepo = commentRepo;
        this.userAccountService = userAccountService;
        this.climbingSpotService = climbingSpotService;
    }

    /**
     * Add and save a new comment
     * @param idClimbingSpot
     * @param comment
     */
    @Override
    public void addNewComment(Long idClimbingSpot, Comment comment){
        comment.setUserAccount(userAccountService.getCurrentUserAccount());
        comment.setClimbingSpot(climbingSpotService.findById(idClimbingSpot).get());
        save(comment);
    }

    /**
     * Find all comments linked to a certain spot
     * @param id
     * @return Set<Comment>
     */
    @Override
    public Set<Comment> findAllBySpot(Long id){
       return commentRepo.findAllByClimbingSpot_Id(id);
    }


    @Override
    public CrudRepository<Comment, Long> getRepository() {
        return this.commentRepo;
    }
}
