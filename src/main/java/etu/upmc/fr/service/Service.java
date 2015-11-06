package etu.upmc.fr.service;

import etu.upmc.fr.account.Account;
import etu.upmc.fr.address.Address;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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

    @ManyToOne
    private Account requestor;

    @ManyToMany(targetEntity = Account.class)
    private Set<Account> offerors;

    @ManyToOne
    private Account contractor;

    @ManyToOne
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date proposalExpiration;

    public Service() {
        setOfferors(new HashSet<>());
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

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Account getRequestor() {
        return requestor;
    }

    public void setRequestor(Account requestor) {
        this.requestor = requestor;
    }

    public Set<Account> getOfferors() {
        return offerors;
    }

    public void setOfferors(Set<Account> offerors) {
        this.offerors = offerors;
    }

    public Account getContractor() {
        return contractor;
    }

    public void setContractor(Account contractor) {
        this.contractor = contractor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getProposalExpiration() {
        return proposalExpiration;
    }

    public void setProposalExpiration(Date proposalExpiration) {
        this.proposalExpiration = proposalExpiration;
    }
}
