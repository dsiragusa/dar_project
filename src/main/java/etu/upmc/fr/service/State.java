package etu.upmc.fr.service;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "state")
public class State {
    public static final Integer BIDDING = 0,
                                VALIDATING = 1,
                                CONTRACT = 2;

    @Id
    @GeneratedValue
    private Long id;

    private Integer code;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
