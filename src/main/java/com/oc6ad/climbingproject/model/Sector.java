package com.oc6ad.climbingproject.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Sector entity
 */
@Entity(name = "sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private ClimbingSpot climbingSpot;

    @OneToMany
    @JoinColumn(name="sector_id")
    private Set<Route> routes = new HashSet<>();

    public Sector(){

    }

    public Sector(Long id, String name, ClimbingSpot climbingSpot, Set<Route> routes) {
        this.id = id;
        this.name = name;
        this.climbingSpot = climbingSpot;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
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

    public ClimbingSpot getClimbingSpot() {
        return climbingSpot;
    }

    public void setClimbingSpot(ClimbingSpot climbingSpot) {
        this.climbingSpot = climbingSpot;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", climbingSpot=" + climbingSpot +
                ", routes=" + routes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sector sector = (Sector) o;
        return Objects.equals(name, sector.name) &&
                Objects.equals(climbingSpot, sector.climbingSpot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, climbingSpot);
    }
}
