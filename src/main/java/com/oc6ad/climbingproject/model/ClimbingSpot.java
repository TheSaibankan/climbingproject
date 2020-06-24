package com.oc6ad.climbingproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ClimbingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String location;
    private boolean isOfficial;
    private String cotation;

    @ManyToOne
    private UserAccount userAccount;

    @OneToMany
    private Set<Sector> sectors = new HashSet<>();

    public ClimbingSpot() {
    }

    public ClimbingSpot(Long id, String name, String location, boolean isOfficial, String cotation, UserAccount userAccount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isOfficial = isOfficial;
        this.cotation = cotation;
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    @Override
    public String toString() {
        return "ClimbingSpot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", isOfficial=" + isOfficial +
                ", cotation='" + cotation + '\'' +
                ", userAccount=" + userAccount +
                ", sectors=" + sectors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClimbingSpot that = (ClimbingSpot) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}
