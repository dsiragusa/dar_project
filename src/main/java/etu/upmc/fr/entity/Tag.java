package etu.upmc.fr.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(targetEntity = Service.class, mappedBy = "tags")
    private Set<Service> services;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
