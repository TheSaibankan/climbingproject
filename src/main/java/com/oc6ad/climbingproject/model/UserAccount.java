package com.oc6ad.climbingproject.model;

import javax.persistence.*;
import java.util.*;

/**
 * User account entity
 */
@Entity(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private boolean isAdmin;

    @OneToMany
    @JoinColumn(name="owner_id")
    private Set<Topo> topos = new HashSet<>();

    @OneToMany
    @JoinColumn(name="user_account_id")
    private Set<ClimbingSpot> climbingSpots = new HashSet<>();

    @OneToMany
    @JoinColumn(name="user_account_id")
    private Set<Comment> comments = new HashSet<>();

    public UserAccount() {

    }

    public UserAccount(Long id, String firstName, String lastName, String email, String login, String password, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<ClimbingSpot> getClimbingSpots() {
        return climbingSpots;
    }

    public void setClimbingSpots(Set<ClimbingSpot> climbingSpots) {
        this.climbingSpots = climbingSpots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAccount setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Topo> getTopos() {
        return topos;
    }

    public void setTopos(Set<Topo> topos) {
        this.topos = topos;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getRole() {
        return isAdmin ? "ADMIN": "USER";
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        return Objects.equals(login, that.login) && Objects.equals(email, that.email);

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
