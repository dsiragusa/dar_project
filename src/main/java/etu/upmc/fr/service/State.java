package etu.upmc.fr.service;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @ManyToOne
    private Service service;

    public State() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
