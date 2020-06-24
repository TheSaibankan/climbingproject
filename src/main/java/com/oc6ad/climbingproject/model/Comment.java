package com.oc6ad.climbingproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne
    private UserAccount userAccount;

    @ManyToOne
    private ClimbingSpot climbingSpot;

    public Comment() {

    }

    public Comment(Long id, String content, UserAccount userAccount, ClimbingSpot climbingSpot) {
        this.id = id;
        this.content = content;
        this.userAccount = userAccount;
        this.climbingSpot = climbingSpot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public ClimbingSpot getClimbingSpot() {
        return climbingSpot;
    }

    public Comment setClimbingSpot(ClimbingSpot climbingSpot) {
        this.climbingSpot = climbingSpot;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userAccount=" + userAccount +
                ", climbingSpot=" + climbingSpot +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(content, comment.content) &&
                Objects.equals(userAccount, comment.userAccount) &&
                Objects.equals(climbingSpot, comment.climbingSpot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, userAccount, climbingSpot);
    }
}
