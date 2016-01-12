package etu.upmc.fr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by daniele on 12/01/16.
 */
@Entity
@Table(name = "tracking")

public class Tracking {
    @Id
    @GeneratedValue
    private Long id;

    private String stolenCookies;

    public Tracking()
    {

    }

    public Long getId() {
        return id;
    }

    public String getStolenCookies() {
        return stolenCookies;
    }

    public void setStolenCookies(String name) {
        this.stolenCookies = name;
    }

}
