package etu.upmc.fr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp = "^[^0-9].*$")
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(targetEntity = Service.class, mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Service> services;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void addService(Service s) {
        this.services.add(s);
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ( ! (o instanceof Tag)) {
            return false;
        }

        return name.equals(((Tag)o).getName());
    }
}
