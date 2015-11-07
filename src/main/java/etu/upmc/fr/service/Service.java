package etu.upmc.fr.service;

import etu.upmc.fr.account.Account;
import etu.upmc.fr.address.Address;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(min = 5, max = 100)
    private String title;

    @NotBlank
    @Length(min = 10)
    private String description;

    @ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Tag> tags;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "service")
    private List<State> states;

    @ManyToOne
    private Account requestor;

    @ManyToMany(targetEntity = Account.class)
    private Set<Account> offerors;

    @ManyToOne
    private Account contractor;

    @ManyToOne
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date biddingDeadline;

    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceDeadline;

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

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
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

    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    public void setBiddingDeadline(Date biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
    }

    public Date getServiceDeadline() {
        return serviceDeadline;
    }

    public void setServiceDeadline(Date serviceDeadline) {
        this.serviceDeadline = serviceDeadline;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
