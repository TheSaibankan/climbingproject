package com.oc6ad.climbingproject.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Topo entity
 */
@Entity(name = "topos")
public class Topo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String location;
    private String releaseDate;
    private boolean isAvailable;

    @ManyToOne
    private UserAccount owner;

    @OneToOne
    @Nullable
    private UserAccount receiver;

    private boolean hasBeenRequested;
    private boolean hasBeenAccepted;

    public Topo() {

    }

    public Topo(String name, String description, String location, String releaseDate, boolean isAvailable, UserAccount ownerId, UserAccount receiverId, boolean hasBeenRequested, boolean hasBeenAccepted) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
        this.owner = owner;
        this.receiver = receiver;
        this.hasBeenRequested = hasBeenRequested;
        this.hasBeenAccepted = hasBeenAccepted;
    }

    public boolean isHasBeenAccepted() {
        return hasBeenAccepted;
    }

    public void setHasBeenAccepted(boolean hasBeenAccepted) {
        this.hasBeenAccepted = hasBeenAccepted;
    }

    public boolean isHasBeenRequested() {
        return hasBeenRequested;
    }

    public void setHasBeenRequested(boolean hasBeenRequested) {
        this.hasBeenRequested = hasBeenRequested;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    @Nullable
    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(@Nullable UserAccount receiver) {
        this.receiver = receiver;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Topo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", isAvailable=" + isAvailable +
                ", ownerId=" + owner +
                ", receiverId=" + receiver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topo topo = (Topo) o;
        return Objects.equals(name, topo.name) &&
                Objects.equals(releaseDate, topo.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate);
    }
}
