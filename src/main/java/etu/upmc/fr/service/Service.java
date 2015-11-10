package etu.upmc.fr.service;

import etu.upmc.fr.account.Account;
import etu.upmc.fr.address.Address;
import etu.upmc.fr.annotations.MyDateTime;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<State> states;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account requestor;

    @ManyToMany(targetEntity = Account.class, fetch = FetchType.EAGER)
    private Set<Account> offerors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account contractor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @MyDateTime
    private Date biddingDeadline;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @MyDateTime
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

    public void addOfferor(Account offeror) throws Exception {
        if (offeror.equals(requestor)) {
            throw new Exception("Contractor cannot be the same account as requestor");
        }

        State current = states.get(states.size() - 1);

        if ( ! current.getCode().equals(State.BIDDING)) {
            throw new Exception("Bidding phase has expired");
        }

        this.offerors.add(offeror);
    }

    public Account getContractor() {
        return contractor;
    }

    public void setContractor(Account contractor) throws Exception {
        if (contractor.equals(requestor)) {
            throw new Exception("Contractor cannot be the same account as requestor");
        }

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
