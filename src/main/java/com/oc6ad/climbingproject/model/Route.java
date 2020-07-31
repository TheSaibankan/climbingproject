package com.oc6ad.climbingproject.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Route entity
 */
@Entity(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer pitchQuantity;

    @ManyToOne
    private Sector sector;

    public Route() {

    }

    public Route(Long id, String name, Integer pitchQuantity, Sector sector) {
        this.id = id;
        this.name = name;
        this.pitchQuantity = pitchQuantity;
        this.sector = sector;
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

    public Integer getPitchQuantity() {
        return pitchQuantity;
    }

    public void setPitchQuantity(Integer pitchQuantity) {
        this.pitchQuantity = pitchQuantity;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pitchQuantity=" + pitchQuantity +
                ", sector=" + sector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(name, route.name) &&
                Objects.equals(sector, route.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sector);
    }
}
