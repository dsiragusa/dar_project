package etu.upmc.fr.service;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Tag> tags;

    @OneToMany(mappedBy = "service")
    private Set<State> states;

    public Service() {
    }

    public Long getId() {
        return id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
